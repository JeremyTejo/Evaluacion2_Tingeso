package tingeso_mingeso.backendestudiantesservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    private String rut;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String tipoColegio;
    private String nombreColegio;

    @Column(name = "fecha_egreso_colegio", nullable = false)
    private String fechaEgresoColegio;
    @Column(name = "fecha_ingreso_colegio", nullable = false)
    private String fechaIngresoColegio;
}

