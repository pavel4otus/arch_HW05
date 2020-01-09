package ru.pavel2107.socketclient.service;

import ru.pavel2107.socketclient.model.Conversation;

import java.util.List;

public interface ConversationService {
    List<Conversation> findAvailable();

    List<Conversation> findAll();

    Conversation findBySessionId(String sessionId);

    Conversation findBuUniqueUserName(String uniqueUserName);

    Conversation save(Conversation conversation);
}
