package ru.pavel2107.socketclient.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "conversation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @Id
    private String sessionId;
    private String username;
    private String userUniqueName;
    private LocalDateTime created;
    private LocalDateTime disconnected;
    private String operatorName;
    private String operatorUniqueName;
    private List<ChatMessage> messages;
}
