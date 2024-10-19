package com.megalabsapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "laboratorio")
public class Laboratorio {
    @Id
    @Column(name = "RUC", length = 11)
    private String ruc;

    @Column(name = "NRM_Registro_Sanitario", length = 20)
    private String nrmRegistroSanitario;

    @Column(name = "Direccion", length = 50)
    private String direccion;

    @Column(name = "Telefono", length = 9)
    private String telefono;
}