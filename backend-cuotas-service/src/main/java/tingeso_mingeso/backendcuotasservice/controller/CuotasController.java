package tingeso_mingeso.backendcuotasservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso_mingeso.backendcuotasservice.entity.CuotasEntity;
import tingeso_mingeso.backendcuotasservice.model.EstudianteEntity;
import tingeso_mingeso.backendcuotasservice.service.AdministracionService;
import tingeso_mingeso.backendcuotasservice.service.CuotasService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cuotas")
public class CuotasController {

    private final CuotasService cuotasService;

    @Autowired
    public CuotasController(CuotasService cuotasService, AdministracionService administracionService) {
        this.cuotasService = cuotasService;
    }

    // Endpoint para generar cuotas para un estudiante dado su RUT
    // HU3: Generar cuotas de pago
    @PostMapping("/generar/{rut}")
    public ResponseEntity<?> generarCuotas(@PathVariable String rut) {
        try {
            cuotasService.generarCuotas(rut);
            return ResponseEntity.ok("Cuotas generadas exitosamente para el estudiante con RUT: " + rut);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al generar cuotas: " + e.getMessage());
        }
    }


    // HU4: Listar cuotas de un estudiante
    @GetMapping("/estudiante/{rut}")
    public ResponseEntity<List<CuotasEntity>> listarCuotasEstudiante(@PathVariable String rut) {
        List<CuotasEntity> cuotas = cuotasService.findCuotasByRut(rut);
        if (cuotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuotas);
    }


    // Endpoint para registrar el pago de una cuota
    // HU5: Registrar pago de cuota
    @PutMapping("/pagar/{idCuota}")
    public ResponseEntity<?> registrarPagoCuota(@PathVariable Long idCuota) {
        try {
            CuotasEntity cuotaPagada = cuotasService.registrarPagoCuota(idCuota);
            return ResponseEntity.ok(cuotaPagada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            String errorMessage = e.getMessage() != null ? e.getMessage() : "Error al procesar el pago de la cuota.";
            return ResponseEntity.badRequest().body(errorMessage);
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            String errorMessage = e.getMessage() != null ? e.getMessage() : "Error interno del servidor.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



}
