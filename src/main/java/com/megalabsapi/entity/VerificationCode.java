package com.megalabsapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "verification_code")
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "expiry_date", nullable = false)
    private Timestamp expiryDate;

    @ManyToOne
    @JoinColumn(name = "representante_id", nullable = false)
    private Representante representante;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed = false; // Para asegurarse de que el código no sea reutilizado

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

}

