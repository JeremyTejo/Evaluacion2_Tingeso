package tingeso_mingeso.backendcuotasservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso_mingeso.backendcuotasservice.entity.CuotasEntity;

import java.util.List;

@Repository
public interface CuotasRepository extends JpaRepository<CuotasEntity, Long> {

    List<CuotasEntity> findCuotasByRut(String rut);

    List<CuotasEntity> findCuotasPendientesByRut(String rut);
}
