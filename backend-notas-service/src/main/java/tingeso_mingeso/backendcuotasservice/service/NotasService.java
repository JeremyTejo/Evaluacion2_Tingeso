package tingeso_mingeso.backendcuotasservice.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tingeso_mingeso.backendcuotasservice.entity.Notas;
import tingeso_mingeso.backendcuotasservice.model.EstudianteEntity;
import tingeso_mingeso.backendcuotasservice.repository.NotasRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
public class NotasService {
    private final RestTemplate restTemplate;
    private final NotasRepository notasRepository;
    private static final String URL_SERVICIO_ESTUDIANTE = "http://localhost:8080/estudiantes/";
    private static final String URL_SERVICIO_CUOTAS = "http://localhost:8080/cuotas/";

    public NotasService( RestTemplate restTemplate, NotasRepository notasRepository) {
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
                    String[] datos = linea.split(",");
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

}