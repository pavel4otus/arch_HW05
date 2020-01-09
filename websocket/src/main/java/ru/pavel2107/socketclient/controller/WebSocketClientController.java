package ru.pavel2107.socketclient.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import ru.pavel2107.socketclient.model.InputMessage;
import ru.pavel2107.socketclient.service.ConversationService;
import ru.pavel2107.socketclient.model.ChatMessage;
import ru.pavel2107.socketclient.model.Conversation;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class WebSocketClientController {

    private ConversationService conversationService;

    @Autowired
    public WebSocketClientController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/hello")
    public InputMessage processMessageFromClient(Principal principal, @Payload InputMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        System.out.println(message);
        String sessionId = (String) headerAccessor.getSessionAttributes().get("sessionId");
        String username = message.getFrom();
        System.out.println("sessionId:" + sessionId);
        System.out.println("getSubscriptionId:" + headerAccessor.getSubscriptionId());

        String user = headerAccessor.getFirstNativeHeader("unique-user");
        System.out.println("user from accessor is" + user);
        //
        // выясним кто пишет клиент или админ
        //
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principal;
        boolean isAdmin = auth
                .getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        //
        // запоминаем сообщение
        //
        ChatMessage chatMessage = new ChatMessage(message.getText(), LocalDateTime.now(), !isAdmin);
        //
        // ищем беседу с оператором. Если не нашли, вносим в список ожидания
        //
        Conversation conversation = null;
        if (isAdmin) {
            conversation = conversationService.findBuUniqueUserName(message.getToUniqueName());
            if (StringUtils.isEmpty(message.getTo())) {
                message.setTo(conversation.getUsername());
            }
        } else {
            conversation = conversationService.findBySessionId(sessionId);
        }
        if (conversation.getSessionId() == null) {
            conversation.setSessionId(sessionId);

            conversation.setUsername(username);
            conversation.setUserUniqueName(principal.getName());

            conversation.setCreated(LocalDateTime.now());
            conversation.setMessages(new ArrayList<>());
            //
            // сообщаем клиенту его уникальное имя
            //
            message.setSessionId(sessionId);
        }
        //
        // если пишет админ, то проверим и заполним имя оператора в беседе и в сообщении
        //
        if (isAdmin) {
            if (StringUtils.isEmpty(conversation.getOperatorName())) {
                conversation.setOperatorUniqueName(message.getFromUniqueName());
                conversation.setOperatorName(message.getFrom());
            }
        }


        conversation.getMessages().add(chatMessage);
        conversationService.save(conversation);
        System.out.println("principal=" + principal.getName() + " -> " + message.getFrom());
        //
        // отправляем сообщение себе, чтобы удостовериться в жизнеспособности сервера
        //
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/reply", message);
        //
        // отправляем сообщение получателю, если он есть конечно
        //
        if (!StringUtils.isEmpty(message.getToUniqueName())) {
            messagingTemplate.convertAndSendToUser(message.getToUniqueName(), "/queue/reply", message);
        }
        return message;
    }


    @MessageMapping("/hello2")
    public InputMessage old_processMessageFromClient(Principal principal, @Payload InputMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        System.out.println(message);
        String sessionId = (String) headerAccessor.getSessionAttributes().get("sessionId");
        String username = message.getFrom();
        System.out.println("sessionId:" + sessionId);
        System.out.println("getSubscriptionId:" + headerAccessor.getSubscriptionId());

        String user = headerAccessor.getFirstNativeHeader("unique-user");
        System.out.println("user from accessor is" + user);


        //
        // запоминаем сообщение
        //
        ChatMessage chatMessage = new ChatMessage(message.getText(), LocalDateTime.now(), true);
        //
        // ищем беседу с оператором. Если не нашли, вносим в список ожидания
        //
        Conversation conversation = conversationService.findBySessionId(sessionId);
        if (conversation.getSessionId() == null) {
            conversation.setSessionId(sessionId);
            conversation.setUsername(username);
            conversation.setCreated(LocalDateTime.now());
            conversation.setMessages(new ArrayList<>());
            conversation.setUserUniqueName(principal.getName());
            //
            // сообщаем клиенту его уникальное имя
            //
            message.setSessionId(sessionId);
            message.setToUniqueName("");
        }
        conversation.getMessages().add(chatMessage);
        conversationService.save(conversation);
        System.out.println("principal=" + principal.getName() + " -> " + message.getFrom());
        //messagingTemplate.convertAndSendToUser(message.getFrom(), "/queue/reply", message);
        //
        // отправляем сообщение себе, чтобы удостовериться в жизнеспособности сервера
        //
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/reply", message);
        //
        // отправляем сообщение получателю, если он есть конечно
        //
        if (!StringUtils.isEmpty(message.getToUniqueName())) {
            messagingTemplate.convertAndSendToUser(message.getToUniqueName(), "/queue/reply", message);
        }
        return message;
    }


    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}