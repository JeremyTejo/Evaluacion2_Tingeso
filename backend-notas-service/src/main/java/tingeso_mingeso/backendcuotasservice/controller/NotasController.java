package tingeso_mingeso.backendcuotasservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tingeso_mingeso.backendcuotasservice.entity.Notas;
import tingeso_mingeso.backendcuotasservice.service.NotasService;

import java.util.List;

@RestController
@RequestMapping("/notas")
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
}


