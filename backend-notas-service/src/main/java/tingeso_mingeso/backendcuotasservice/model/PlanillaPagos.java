package tingeso_mingeso.backendcuotasservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "planilla_pagos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanillaPagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rut_estudiante")
    private String rutEstudiante;

    @Column(name = "monto_total_arancel")
    private double montoTotalArancel;

    @Column(name = "descuento_aplicado")
    private double descuentoAplicado;

    @Column(name = "monto_final")
    private double montoFinal; // Monto después de aplicar descuentos

    @Column(name = "intereses")
    private double intereses; // Intereses por pagos atrasados

    @Column(name = "monto_total_pago")
    private double montoTotalPago; // Monto total incluyendo intereses

}

