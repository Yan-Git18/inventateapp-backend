package com.cubasquispe.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {

    private int idProducto;

    @NotNull
    @Size(min = 3, max = 70)
    private String nombre;

    @NotNull
    @Size(min = 3, max = 100)
    private String descripcion;

    @NotNull
    @Size(min = 5, max = 70)
    private String codigo_barra;

    @NotNull
    @Positive
    private double precio_compra;

    @NotNull
    @Positive
    private double precio_venta;

    @NotNull
    @Min(0)
    private int stock_actual;

    @NotNull
    @Min(0)
    private int stock_minimo;

    @NotNull
    @Size(min = 2, max = 40)
    private String estado;

    @NotNull
    private int idProveedor;

    @NotNull
    private int idCategoria;
}