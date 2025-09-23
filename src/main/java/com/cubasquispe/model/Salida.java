package com.cubasquispe.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor  //Constructor vacío
@AllArgsConstructor //Constructor con parámetros
@Data   //Getters and Setters
@Entity

public class Salida {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSalida;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false, length = 100)
    private String tipoSalida;

    @Column(nullable = false)
    private String destino;

    private String observaciones;
}
