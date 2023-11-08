package tingeso_mingeso.backendestudiantesservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;

import java.util.List;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante,String> {
    List<Estudiante> findByRut(String rut);
}
