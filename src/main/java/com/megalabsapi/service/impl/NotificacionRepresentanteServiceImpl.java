package com.megalabsapi.service.impl;

import com.megalabsapi.integration.notification.email.dto.Mail;
import com.megalabsapi.integration.notification.email.service.EmailService;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.service.NotificacionRepresentanteService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NotificacionRepresentanteServiceImpl implements NotificacionRepresentanteService {

    private final EmailService emailService;
    private final RepresentanteRepository representanteRepository;

    @Value("${spring.mail.username}")
    private String mainFrom;

    @Override
    public void sendMailRepresentante(String to,  String message) throws MessagingException {
        Optional<Representante> representante = representanteRepository.findByEmail(to);
        if (!representante.isPresent()) {
            throw new MessagingException("El correo electrónico no está registrado en el sistema.");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("nombre",representante.get().getNombre());
        model.put("dni",representante.get().getDni());
        model.put("message", message);

        Mail mail = emailService.createMail(
                to,
                "Notificacion a representante",
                model,
                mainFrom
        );
        emailService.sendEmail(mail, "notificacion-representante.html");
    }

}
