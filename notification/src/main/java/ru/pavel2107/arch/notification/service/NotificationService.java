package ru.pavel2107.arch.notification.service;

import java.util.Map;

public interface NotificationService {
    void send(Map<String, String> properties);
}
