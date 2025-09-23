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
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_proveedor;

    @Column(nullable = false, length = 70)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String ruc;

    @Column(nullable = false, length = 70)
    private String direccion;

    @Column(nullable = false, length = 70)
    private String telefono;

    @Column(nullable = false, length = 70)
    private String correo;
}