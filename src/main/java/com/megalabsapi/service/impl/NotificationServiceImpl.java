package com.megalabsapi.service.impl;

import com.megalabsapi.model.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendRecoveryEmail(String email, String recoveryUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recuperación de contraseña");
        message.setText("Haga clic en el siguiente enlace para recuperar su contraseña: " + recoveryUrl);
        mailSender.send(message);
    }


}
