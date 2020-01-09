package ru.pavel2107.socketclient.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;
import ru.pavel2107.socketclient.service.ConversationService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig
        implements WebSocketMessageBrokerConfigurer {

    @Autowired
    ConversationService service;

    @Bean
    public ConversationEventListener presenceEventListener() {
        ConversationEventListener presence = new ConversationEventListener(service);
        return presence;
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic/", "/queue/", "/user/");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                //.addEndpoint("/eshop_websocket")
                .addEndpoint("/gs-guide-websocket")
                .addInterceptors(
                        new HandshakeInterceptor() {
                            @Override
                            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                                if (request instanceof ServletServerHttpRequest) {
                                    ServletServerHttpRequest servletRequest
                                            = (ServletServerHttpRequest) request;
                                    HttpSession session = servletRequest
                                            .getServletRequest().getSession();
                                    attributes.put("sessionId", session.getId());
                                    System.out.println("beforeHandshake. sessionId ->" + session.getId());
                                }
                                return true;
                            }

                            @Override
                            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
                                System.out.println("afterHandshake");
                            }
                        }
                )

                .withSockJS();
    }


    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {

            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                System.out.println("configureClientInboundChannel.preSend");
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // String user = accessor.getFirstNativeHeader("user");
                    String user = accessor.getFirstNativeHeader("unique-user");
                    if (!StringUtils.isEmpty(user)) {
                        System.out.println("configureClientInboundChannel.user=" + user);
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        String token = accessor.getFirstNativeHeader("X-Authorization");
                        if (!StringUtils.isEmpty(token)) {
                            Jwt jwtToken = JwtHelper.decode(token);
                            try {
                                ObjectMapper objectMapper = new ObjectMapper();
                                Map<String, Object> claims = objectMapper.readValue(jwtToken.getClaims(), Map.class);
                                // user = (String) claims.get( "user_name");
                                List<String> roles = (ArrayList<String>) claims.get("authorities");
                                for (String role : roles) {
                                    authorities.add(new SimpleGrantedAuthority(role));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                        }
                        Authentication auth = new UsernamePasswordAuthenticationToken(user, user, authorities);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        accessor.setUser(auth);
                    }
                }

                return message;
            }
        });
    }


}