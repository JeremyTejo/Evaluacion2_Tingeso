package tingeso_mingeso.backendcuotasservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "cuotas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuotasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuota;
    private String rut;
    private LocalDate fechaPago;
    private LocalDate fechaVencimiento;
    private double montoCuota;
    private String estadoCuota;
    private int mesesAtraso = 0;
}
