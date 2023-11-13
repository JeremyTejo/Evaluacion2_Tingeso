package tingeso_mingeso.backendcuotasservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuotas {
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