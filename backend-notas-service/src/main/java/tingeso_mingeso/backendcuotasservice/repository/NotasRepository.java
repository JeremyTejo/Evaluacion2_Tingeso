package tingeso_mingeso.backendcuotasservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso_mingeso.backendcuotasservice.entity.Notas;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {
}
