package ru.pavel2107.socketclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.socketclient.model.Conversation;
import ru.pavel2107.socketclient.repository.ConversationRepository;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    private ConversationRepository repository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Conversation> findAll() {
        return repository.findAll();
    }

    public List<Conversation> findAvailable() {
        return repository.findAllByDisconnectedIsNull();
    }

    @Override
    public Conversation findBySessionId(String sessionId) {
        return repository.findById(sessionId).orElse(new Conversation());
    }

    @Override
    public Conversation findBuUniqueUserName(String uniqueUserName) {
        Conversation conversation = repository.findByUserUniqueNameEquals(uniqueUserName);
        if (conversation == null) conversation = new Conversation();
        return conversation;
    }

    @Override
    public Conversation save(Conversation conversation) {
        return repository.save(conversation);
    }
}
