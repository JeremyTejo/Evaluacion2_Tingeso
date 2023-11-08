package tingeso_mingeso.backendcuotasservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso_mingeso.backendcuotasservice.entity.CuotasEntity;
import tingeso_mingeso.backendcuotasservice.model.EstudianteEntity;
import tingeso_mingeso.backendcuotasservice.service.AdministracionService;
import tingeso_mingeso.backendcuotasservice.service.CuotasService;

import java.util.List;

@RestController
@RequestMapping("/cuotas")
public class CuotasController {

    private final CuotasService cuotasService;
    private final AdministracionService administracionService;

    @Autowired
    public CuotasController(CuotasService cuotasService, AdministracionService administracionService) {
        this.cuotasService = cuotasService;
        this.administracionService = administracionService;
    }

    // Endpoint para generar cuotas para un estudiante dado su RUT
    @PostMapping("/generar/{rut}")
    public ResponseEntity<?> generarCuotas(@PathVariable String rut) {
        try {
            cuotasService.generarCuotas(rut);
            return new ResponseEntity<>("Cuotas generadas correctamente.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para obtener todas las cuotas de un estudiante por RUT
    @GetMapping("/estudiante/{rut}")
    public ResponseEntity<List<CuotasEntity>> obtenerCuotasPorRut(@PathVariable String rut) {
        List<CuotasEntity> cuotas = cuotasService.findCuotasByRut(rut);
        if (cuotas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cuotas, HttpStatus.OK);
    }

    // Endpoint para registrar el pago de una cuota
    @PostMapping("/pagar/{idCuota}")
    public ResponseEntity<?> registrarPagoCuota(@PathVariable Long idCuota) {
        try {
            CuotasEntity cuotaPagada = cuotasService.registrarPagoCuota(idCuota);
            return new ResponseEntity<>(cuotaPagada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // ... Otros endpoints que puedas necesitar ...

}
