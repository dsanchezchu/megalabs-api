package com.megalabsapi.api;

import com.megalabsapi.service.NotificacionClienteService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    // Ya no necesitas @Autowired, con @RequiredArgsConstructor se inyecta automáticamente
    private final NotificacionClienteService notificacionClienteService;

    @PostMapping("/enviar")
    public String sendNotification(@RequestParam String email, @RequestParam String message) {
        try {
            // Llamar al servicio para enviar el correo
            notificacionClienteService.sendMailRepresentante(email, message);
            return "Notificación enviada exitosamente a " + email;
        } catch (MessagingException e) {
            return "Error al enviar la notificación: " + e.getMessage();
        }
    }
}