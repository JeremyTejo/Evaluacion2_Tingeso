package tingeso_mingeso.backendcuotasservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso_mingeso.backendcuotasservice.entity.CuotasEntity;
import tingeso_mingeso.backendcuotasservice.model.EstudianteEntity;
import tingeso_mingeso.backendcuotasservice.repository.CuotasRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;


@Service
public class CuotasService {

    private final CuotasRepository cuotaRepository;
    private final RestTemplate restTemplate;
    private final AdministracionService administracionService;

    @Autowired
    public CuotasService(CuotasRepository cuotaRepository, RestTemplate restTemplate, AdministracionService administracionService) {
        this.cuotaRepository = cuotaRepository;
        this.restTemplate = restTemplate;
        this.administracionService = administracionService;
    }


    public void generarCuotas(String rut, int numeroCuotas) {
        EstudianteEntity estudiante = findByRut(rut);
        descuentoArancelGeneracionCuotas(estudiante, numeroCuotas);
    }

    private void descuentoArancelGeneracionCuotas(EstudianteEntity estudiante, int cantidadCuotas) {
        int descuentoTipoColegio = administracionService.descuentoTipoColegio(estudiante.getTipoColegio());
        int descuentoAnio = administracionService.descuentoEgresoColegio(estudiante.getFechaEgresoColegio(), estudiante.getFechaIngresoColegio());
        int descuentoTotal = descuentoTipoColegio + descuentoAnio;
        float descuentoDecimal = (float) descuentoTotal / 100;
        int valorArancel = (int) (administracionService.getArancel() * (1 - descuentoDecimal));
        float cuotaValor = (float) valorArancel / cantidadCuotas;

        for (int i = 1; i <= cantidadCuotas; i++) {
            CuotasEntity cuota = new CuotasEntity();
            cuota.setMontoCuota(cuotaValor);
            cuota.setEstadoCuota("Pendiente");
            cuota.setFechaVencimiento(administracionService.calcularFechaVencimiento(i));
            // Aquí asumimos que tienes un método para asignar el RUT del estudiante a la cuota
            cuota.setRut(estudiante.getRut());
            cuotaRepository.save(cuota);
        }
    }

    private EstudianteEntity findByRut(String rut) {
        ResponseEntity<EstudianteEntity> response = restTemplate.exchange(
                "http://localhost:8082/estudiante/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EstudianteEntity>() {}
        );
        return response.getBody();
    }

    public List<CuotasEntity> findCuotasByRut(String rut) {
        return cuotaRepository.findCuotasByRut(rut);
    }

    public CuotasEntity registrarPagoCuota(Long idCuota) {
        // Obtener la cuota por su ID
        CuotasEntity cuota = cuotaRepository.findById(idCuota)
                .orElseThrow(() -> new EntityNotFoundException("Cuota no encontrada con id: " + idCuota));

        // Verificar si la cuota ya está pagada
        if ("Pagado".equals(cuota.getEstadoCuota())) {
            throw new IllegalStateException("La cuota ya está pagada.");
        }

        // Registrar el pago
        cuota.setEstadoCuota("Pagado");
        cuota.setFechaPago(LocalDate.now()); // Asumiendo que tienes un campo para la fecha de pago

        // Guardar la cuota actualizada
        return cuotaRepository.save(cuota);
    }
}
