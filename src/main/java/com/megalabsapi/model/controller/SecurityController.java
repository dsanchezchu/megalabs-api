package com.megalabsapi.model.controller;

import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/suspicious-activities")
    public ResponseEntity<List<LoginAttempt>> getSuspiciousActivities(@RequestParam String representanteDni) {
        List<LoginAttempt> suspiciousAttempts = loginAttemptService.getSuspiciousAttemptsByRepresentanteDni(representanteDni);
        return ResponseEntity.ok(suspiciousAttempts);
    }
}

