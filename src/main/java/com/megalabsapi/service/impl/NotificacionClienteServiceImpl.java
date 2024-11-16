package com.megalabsapi.service.impl;

import com.megalabsapi.integration.notification.email.dto.Mail;
import com.megalabsapi.integration.notification.email.service.EmailService;
import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.repository.ClienteRepository;
import com.megalabsapi.service.NotificacionClienteService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NotificacionClienteServiceImpl implements NotificacionClienteService {

    private final EmailService emailService;
    private final ClienteRepository clienteRepository;
    @Value("${spring.mail.username}")
    private String mainFrom;

    @Override
    public void sendMailRepresentante(String to,  String message) throws MessagingException {
        Optional<Cliente> cliente = clienteRepository.findByEmail(to);
        if (!cliente.isPresent()) {
            throw new MessagingException("El correo electrónico no está registrado en el sistema.");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("nombre",cliente.get().getNombre());
        model.put("message", message);

        Mail mail = emailService.createMail(
                to,
                "Hola "+ cliente.get().getNombre()+", Megalabs te informa",
                model,
                mainFrom
        );
        emailService.sendEmail(mail, "notificacion-cliente.html");
    }

}
