package com.cubasquispe.model;

import java.sql.Date;

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

public class Ingreso {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngreso;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false, length = 150)
    private String tipoDocumento;

    @Column(nullable = false, length = 20)
    private String numeroDocumento;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_Proveedor", nullable = false, foreignKey = @ForeignKey(name = "FK_INGRESO_PROVEEDOR"))
    private Proveedor proveedor;
}
