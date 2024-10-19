package com.megalabsapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "login_attempt")
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "device", nullable = false)
    private String device;

    @Column(name = "location")
    private String location;

    @Column(name = "attempt_time", nullable = false)
    private Timestamp attemptTime;

    @ManyToOne
    @JoinColumn(name = "representante_id", nullable = false)
    private Representante representante;

    @Column(name = "is_suspicious", nullable = false)
    private boolean isSuspicious;

    public void setIsSuspicious(boolean isSuspicious) {
    }
}

