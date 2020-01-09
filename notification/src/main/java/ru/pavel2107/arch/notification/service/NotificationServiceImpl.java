package ru.pavel2107.arch.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private PushService pushService;
    private EmailService emailService;

    @Autowired
    public NotificationServiceImpl(PushService pushService, EmailService emailService) {
        this.emailService = emailService;
        this.pushService = pushService;
    }


    @Override
    public void send(Map<String, String> properties) {
        String destination = (String) properties.get("destination");
        switch (destination) {
            case "push":
                String phone = (String) properties.get("phone");
                String message = (String) properties.get("message");
                pushService.push(phone, message);
                break;
            case "email":
                String to = (String) properties.get("to");
                String subject = (String) properties.get("subject");
                String text = (String) properties.get("text");
                emailService.send(to, subject, text);
                break;
        }
    }
}
