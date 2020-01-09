package ru.pavel2107.socketclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.pavel2107.socketclient.model.ChatMessage;
import ru.pavel2107.socketclient.model.Conversation;
import ru.pavel2107.socketclient.model.InputMessage;
import ru.pavel2107.socketclient.service.ConversationService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WebSocketAdminController {

    private ConversationService conversationService;
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketAdminController(ConversationService conversationService, SimpMessagingTemplate messagingTemplate) {
        this.conversationService = conversationService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping(value = "/microservices/v1/admin/chat/all_sessions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Conversation> getSessions() {
        List<Conversation> list = conversationService.findAll();
        return list;
    }

    @GetMapping(value = "/microservices/v1/admin/chat/avail_sessions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Conversation> getAvailableSessions() {
        List<Conversation> list = conversationService.findAvailable();
        return list;
    }

    @PutMapping(value = "/microservices/v1/admin/chat/sendmessage")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void produceMessageFromOperator(Principal principal, @RequestParam String sessionId, @RequestParam String message) throws Exception {
        System.out.println("sessionId: " + sessionId);
        System.out.println("message  : " + message);
        //
        // запоминаем сообщение
        //
        ChatMessage chatMessage = new ChatMessage(message, LocalDateTime.now(), true);
        //
        // ищем беседу с оператором. Если не нашли, вносим в список ожидания
        //
        Conversation conversation = conversationService.findBySessionId(sessionId);
        if (conversation.getSessionId() == null) {
            throw new Exception("Session# " + sessionId + " not found");
        }
        if (StringUtils.isEmpty(conversation.getOperatorName())) {
            conversation.setOperatorName(principal.getName());
            conversation.setOperatorUniqueName(principal.getName());
        }
        conversation.getMessages().add(chatMessage);
        conversationService.save(conversation);
        System.out.println("principal=" + principal.getName());
        //
        // отправляем сообщение получателю, если он есть конечно
        //
        if (!StringUtils.isEmpty(conversation.getUserUniqueName())) {
            messagingTemplate.convertAndSendToUser(conversation.getUserUniqueName(), "/queue/reply", message);
        }
    }


}