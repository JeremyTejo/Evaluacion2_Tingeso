package tingeso_mingeso.backendcuotasservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rut_estudiante")
    private String rutEstudiante;

    @Column(name = "fecha_examen")
    private LocalDate fechaExamen;

    @Column(name = "puntaje")
    private int puntaje;

}