package tingeso_mingeso.backendestudiantesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;
import tingeso_mingeso.backendestudiantesservice.repository.EstudianteRepositorio;


import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepositorio estudianteRepository;

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante getEstudianteByRut(String rut) {
        return estudianteRepository.findById(rut).orElse(null);
    }

    public Estudiante saveEstudiante(Estudiante estudiante) {
        Estudiante estudianteNuevo = estudianteRepository.save(estudiante);
        return estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(String rut) {
        estudianteRepository.deleteById(rut);
    }

    public  List<Estudiante>byEstudianteRut(String rut){
        return estudianteRepository.findByRut(rut);
    }

}
