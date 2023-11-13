package tingeso_mingeso.backendcuotasservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso_mingeso.backendcuotasservice.entity.Notas;

import java.util.List;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {
    List<Notas> findByRutEstudiante(String rut);
}
