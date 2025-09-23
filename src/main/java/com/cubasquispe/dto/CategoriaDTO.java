package com.cubasquispe.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaDTO {

    private int idCategoria;

    @NotNull
    @Size(min = 3, max = 70)
    private String nombre;

    @NotNull
    @Size(min = 3, max = 100)
    private String descripcion;
}