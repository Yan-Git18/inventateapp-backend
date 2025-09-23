package com.cubasquispe.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProveedorDTO {

    private int idProveedor;

    @NotNull
    @Size(min = 3, max = 70)
    private String nombre;

    @NotNull
    @Pattern(regexp = "[0-9]{11}", message = "El RUC debe tener 11 dígitos")
    private String ruc;

    @NotNull
    @Size(min = 3, max = 70)
    private String direccion;

    @NotNull
    @Pattern(regexp = "[0-9]+", message = "El teléfono solo debe contener números")
    private String telefono;

    @Email
    private String correo;
}