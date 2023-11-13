package tingeso_mingeso.backendcuotasservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tingeso_mingeso.backendcuotasservice.entity.Notas;
import tingeso_mingeso.backendcuotasservice.model.PlanillaPagos;
import tingeso_mingeso.backendcuotasservice.service.NotasService;

import java.util.List;

@RestController
@RequestMapping("/notas")
@CrossOrigin(origins = "http://localhost:3000")
public class NotasController {

    private final NotasService notasService;

    @Autowired
    public NotasController(NotasService notasService) {
        this.notasService = notasService;
    }

    @PostMapping("/importar")
    public ResponseEntity<?> importarNotas(@RequestParam("archivo") MultipartFile archivo) {
        try {
            List<Notas> notasImportadas = notasService.procesarArchivoNotas(archivo);
            return ResponseEntity.ok(notasImportadas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    @GetMapping("/estudiantes/{rut}")
    public ResponseEntity<List<Notas>> obtenerNotasPorEstudiante(@PathVariable String rut) {
        try {
            List<Notas> notasEstudiante = notasService.obtenerNotasEstudiante(rut);
            if (notasEstudiante.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(notasEstudiante);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/planilla/{rut}")
    public ResponseEntity<?> obtenerPlanillaPorRut(@PathVariable String rut) {
        try {
            PlanillaPagos planilla = notasService.calcularPlanillaPagos(rut);
            if (planilla == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la planilla para el RUT: " + rut);
            }
            return ResponseEntity.ok(planilla);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al calcular la planilla de pagos: " + e.getMessage());
        }
    }
}



