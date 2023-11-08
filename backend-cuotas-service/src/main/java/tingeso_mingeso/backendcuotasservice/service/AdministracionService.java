package tingeso_mingeso.backendcuotasservice.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

@Data
@Service
public class AdministracionService {

    private static final int ARANCEL_BASE = 1500000; // Arancel de estudio base
    private static final int MATRICULA = 70000; // Valor de la matrícula

    // Método para obtener el arancel base (podría ser un valor fijo o recuperado de una base de datos/configuración)
    public int getArancel() {
        return ARANCEL_BASE;
    }

    // Método para calcular el descuento por tipo de colegio de procedencia
    public int descuentoTipoColegio(String tipoColegio) {
        switch (tipoColegio) {
            case "Municipal":
                return 20; // 20% de descuento
            case "Subvencionado":
                return 10; // 10% de descuento
            case "Privado":
                return 0; // Sin descuento
            default:
                throw new IllegalArgumentException("Tipo de colegio desconocido");
        }
    }

    // Método para calcular el descuento por años de egreso del colegio
    public int descuentoEgresoColegio(String fechaEgreso, String fechaIngreso) {
        int aniosDesdeEgreso = Period.between(LocalDate.parse(fechaEgreso), LocalDate.now()).getYears();
        if (aniosDesdeEgreso < 1) {
            return 15; // 15% de descuento
        } else if (aniosDesdeEgreso <= 2) {
            return 8; // 8% de descuento
        } else if (aniosDesdeEgreso <= 4) {
            return 4; // 4% de descuento
        } else {
            return 0; // Sin descuento
        }
    }

    // Método para calcular el número máximo de cuotas según el tipo de colegio
    public int obtenerNumeroMaximoCuotas(String tipoColegio) {
        switch (tipoColegio) {
            case "Municipal":
                return 10;
            case "Subvencionado":
                return 7;
            case "Privado":
                return 4;
            default:
                throw new IllegalArgumentException("Tipo de colegio desconocido");
        }
    }

    // Método para calcular la fecha de vencimiento de cada cuota
    public LocalDate calcularFechaVencimiento(int numeroCuota) {
        // Asumiendo que el año académico comienza en marzo y las cuotas se pagan entre el 5 y el 10 de cada mes
        LocalDate inicioClases = LocalDate.of(LocalDate.now().getYear(), Month.MARCH, 5);
        return inicioClases.plusMonths(numeroCuota - 1);
    }

    // ... Otros métodos que puedas necesitar, como cálculo de intereses por atraso, etc. ...
}


