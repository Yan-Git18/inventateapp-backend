package com.cubasquispe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_producto;

    @Column(nullable = false, length = 70)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false, length = 70)
    private String codigo_barra;

    @Column(nullable = false)
    private double precio_compra;

    @Column(nullable = false)
    private double precio_venta;

    @Column(nullable = false)
    private int stock_actual;

    @Column(nullable = false)
    private int stock_minimo;

    @Column(nullable = false, length = 40)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCTO_PROVEEDOR"))
    private Proveedor proveedor;

    @ManyToOne// FK
    @JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCTO_CATEGORIA"))
    private Categoria categoria;
}