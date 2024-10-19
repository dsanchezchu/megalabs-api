package com.megalabsapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "password_recovery_token")
public class PasswordRecoveryToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "representante_id", nullable = false)
    private Representante representante;

    @Column(name = "expiry_date", nullable = false)
    private Timestamp expiryDate;
}

