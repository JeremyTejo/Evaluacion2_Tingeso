package tingeso_mingeso.backendcuotasservice.service;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tingeso_mingeso.backendcuotasservice.entity.Notas;
import tingeso_mingeso.backendcuotasservice.model.Cuotas;
import tingeso_mingeso.backendcuotasservice.model.EstudianteEntity;
import tingeso_mingeso.backendcuotasservice.model.PlanillaPagos;
import tingeso_mingeso.backendcuotasservice.repository.NotasRepository;

import javax.persistence.EntityNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
public class NotasService {
    private final RestTemplate restTemplate;
    private final NotasRepository notasRepository;
    private static final String URL_SERVICIO_ESTUDIANTE = "http://localhost:8080/estudiantes/";
    private static final String URL_SERVICIO_CUOTAS = "http://localhost:8080/cuotas/";

    // Constantes para los valores base
    private static final int ARANCEL_BASE = 1500000;
    private static final int MATRICULA = 70000;
    private static final String URL_BASE_NOTAS = "http://localhost:8080/notas/"; // Asegúrate de que esta URL sea la correcta

    public NotasService(RestTemplate restTemplate, NotasRepository notasRepository) {
        this.restTemplate = restTemplate;
        this.notasRepository = notasRepository;
    }

    public List<Notas> procesarArchivoNotas(MultipartFile archivo) {
        List<Notas> notasProcesadas = new LinkedList<>();
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            lector.readLine(); // Omitir la primera línea (encabezado)

            String linea;
            while ((linea = lector.readLine()) != null) {
                try {
                    String[] datos = linea.split(";");
                    String rutEstudiante = datos[0];
                    LocalDate fechaExamen = LocalDate.parse(datos[1], formatoFecha);
                    int puntuacion = Integer.parseInt(datos[2]);

                    EstudianteEntity estudiante = restTemplate.getForObject(URL_SERVICIO_ESTUDIANTE + rutEstudiante, EstudianteEntity.class);
                    if (estudiante != null) {
                        Notas nota = new Notas(null, rutEstudiante, fechaExamen, puntuacion);
                        notasProcesadas.add(notasRepository.save(nota));
                    }
                } catch (Exception e) {
                    System.err.println("Error procesando la línea: " + linea);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer o procesar el archivo de notas");
            e.printStackTrace();
        }
        return notasProcesadas;
    }

    public PlanillaPagos calcularPlanillaPagos(String rut) {
        // Obtener datos del estudiante
        EstudianteEntity estudiante = obtenerDatosEstudiante(rut);
        if (estudiante == null) {
            throw new RuntimeException("Estudiante no encontrado");
        }

        // Obtener las cuotas del estudiante
        List<Cuotas> cuotasEstudiante = obtenerCuotasEstudiante(rut);

        PlanillaPagos planilla = new PlanillaPagos();
        planilla.setRutEstudiante(rut);

        // Cálculo de descuentos (aquí puedes incluir la lógica de descuento por promedio de notas, tipo de colegio, etc.)
        double descuento = calcularDescuento(estudiante); // Implementa este método según tus reglas de negocio

        // Cálculo de intereses
        double intereses = calcularIntereses(cuotasEstudiante);

        // Cálculo del monto total del arancel con descuentos
        double montoTotalArancel = ARANCEL_BASE - (ARANCEL_BASE * descuento / 100);

        // Cálculo del monto final a pagar
        double montoFinal = montoTotalArancel + intereses + MATRICULA;

        planilla.setMontoTotalArancel(montoTotalArancel);
        planilla.setDescuentoAplicado(descuento);
        planilla.setIntereses(intereses);
        planilla.setMontoTotalPago(montoFinal);

        return planilla;
    }


    public EstudianteEntity obtenerDatosEstudiante(String rut) {
        try {
            String urlEstudiante = URL_SERVICIO_ESTUDIANTE + rut;
            ResponseEntity<EstudianteEntity> response = restTemplate.exchange(
                    urlEstudiante, HttpMethod.GET, null, new ParameterizedTypeReference<EstudianteEntity>() {
                    });
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener datos del estudiante: " + e.getMessage());
        }
    }

    public List<Notas> obtenerNotasEstudiante(String rut) {
        String urlNotas = URL_BASE_NOTAS + "estudiantes/" + rut;
        ResponseEntity<List<Notas>> response = restTemplate.exchange(
                urlNotas, HttpMethod.GET, null, new ParameterizedTypeReference<List<Notas>>() {});
        return response.getBody();
    }


    public List<Cuotas> obtenerCuotasEstudiante(String rut) {
        try {
            String urlCuotas = URL_SERVICIO_CUOTAS + "estudiantes/" + rut;
            ResponseEntity<List<Cuotas>> response = restTemplate.exchange(
                    urlCuotas, HttpMethod.GET, null, new ParameterizedTypeReference<List<Cuotas>>() {
                    });
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener cuotas del estudiante: " + e.getMessage());
        }
    }


    public double calcularPromedioPruebas(List<Notas> notasEstudiante) {
        return notasEstudiante.stream()
                .mapToInt(Notas::getPuntaje)
                .average()
                .orElse(0.0);
    }

    private double calcularDescuento(EstudianteEntity estudiante) {
        double descuento = 0;

        // Descuento por tipo de colegio
        switch (estudiante.getTipoColegio()) {
            case "Municipal":
                descuento += 20; // 20% de descuento
                break;
            case "Subvencionado":
                descuento += 10; // 10% de descuento
                break;
            case "Privado":
                descuento += 0; // Sin descuento
                break;
            default:
                // Manejo de casos no especificados
                break;
        }

        // Descuento por antigüedad desde el egreso
        LocalDate fechaEgreso = LocalDate.parse(estudiante.getFechaEgresoColegio());
        Period periodoDesdeEgreso = Period.between(fechaEgreso, LocalDate.now());
        int aniosDesdeEgreso = periodoDesdeEgreso.getYears();

        if (aniosDesdeEgreso < 1) {
            descuento += 15; // 15% adicional
        } else if (aniosDesdeEgreso <= 2) {
            descuento += 8; // 8% adicional
        } else if (aniosDesdeEgreso <= 4) {
            descuento += 4; // 4% adicional
        }

        return descuento;
    }

    private double calcularIntereses(List<Cuotas> cuotas) {
        double interesTotal = 0;

        for (Cuotas cuota : cuotas) {
            int mesesAtraso = cuota.getMesesAtraso();
            double montoCuota = cuota.getMontoCuota();

            // Interés variable según los meses de atraso
            double interes = 0;
            if (mesesAtraso == 1) {
                interes = montoCuota * 0.03; // 3% de interés
            } else if (mesesAtraso == 2) {
                interes = montoCuota * 0.06; // 6% de interés
            } else if (mesesAtraso >= 3) {
                interes = montoCuota * 0.09; // 9% de interés para 3 o más meses
            }
            interesTotal += interes;
        }

        return interesTotal;
    }
}


