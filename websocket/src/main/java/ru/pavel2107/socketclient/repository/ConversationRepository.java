package ru.pavel2107.socketclient.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pavel2107.socketclient.model.Conversation;

import java.util.List;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation, String> {
    List<Conversation> findAllByDisconnectedIsNull();

    Conversation findByUserUniqueNameEquals(String uniqueUserName);

    List<Conversation> findAll();
}
