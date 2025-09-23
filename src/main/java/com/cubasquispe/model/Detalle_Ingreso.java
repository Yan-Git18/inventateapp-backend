package com.cubasquispe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Detalle_Ingreso {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @Column(nullable = false, length = 10)
    private int cantidad;

    @Column(nullable = false)
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_Ingreso", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLE_INGRESO_INGRESO"))
    private Ingreso ingreso;

    @ManyToOne
    @JoinColumn(name = "id_Producto", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLE_INGRESO_PRODUCTO"))
    private Producto producto;

}
