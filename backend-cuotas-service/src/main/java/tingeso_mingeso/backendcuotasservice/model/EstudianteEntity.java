package tingeso_mingeso.backendcuotasservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteEntity {

    @Id
    private String rut;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String tipoColegio;
    private String nombreColegio;
    private String fechaEgresoColegio;
    private String fechaIngresoColegio;

}
