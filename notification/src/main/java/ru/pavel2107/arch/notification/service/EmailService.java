package ru.pavel2107.arch.notification.service;

public interface EmailService {
    void send(String to, String subject, String text);
}
