package tingeso_mingeso.backendestudiantesservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;
import tingeso_mingeso.backendestudiantesservice.service.EstudianteService;

import java.util.List;


@RestController
@RequestMapping("/estudiantes")
@CrossOrigin(origins = "http://localhost:3000")
public class EstudianteControlador {

    @Autowired
    EstudianteService estudianteServicio;

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAll() {
        List<Estudiante> estudiantes = estudianteServicio.getAllEstudiantes();
        if(estudiantes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Estudiante> getByRut(@PathVariable("rut") String rut) {
        Estudiante estudiante = estudianteServicio.getEstudianteByRut(rut);
        if(estudiante == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(estudiante);
    }

    @PostMapping
    public ResponseEntity<Estudiante> save(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteServicio.saveEstudiante(estudiante);
        return ResponseEntity.ok(nuevoEstudiante);
    }

    @GetMapping("/bystudent/{rut}")
    public ResponseEntity<List<Estudiante>> byEstudianteRut(@PathVariable("rut")String rut){
        List<Estudiante> estudiantes =estudianteServicio.byEstudianteRut(rut);
        return ResponseEntity.ok(estudiantes);
    }

}
