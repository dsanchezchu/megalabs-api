package com.megalabsapi.api;

import com.megalabsapi.entity.LoginAttempt;
import com.megalabsapi.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/suspicious-activities")
    public ResponseEntity<List<LoginAttempt>> getSuspiciousActivities(@RequestParam String representanteDni) {

        List<LoginAttempt> suspiciousAttempts = loginAttemptService.getSuspiciousAttemptsByRepresentanteDni(representanteDni);

        if (suspiciousAttempts.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si no hay intentos sospechosos
        }

        return ResponseEntity.ok(suspiciousAttempts);
    }
}

