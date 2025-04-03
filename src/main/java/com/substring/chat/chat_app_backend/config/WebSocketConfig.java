package com.substring.chat.chat_app_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

   @Override
   public void configureMessageBroker(MessageBrokerRegistry registry) {

      // This broker is used by the server to send messages to clients who have
      // subscribed to destinations starting with /topic
      // if a client subscribes to /topic/messages, the broker will route messages to
      // that client
      registry.enableSimpleBroker("/topic");

      // sets a prefix for messages that are bound for methods annotated with
      // @MessageMapping in your controllers
      // when a client sends a message to a destination like
      // /app/sendMessage/{roomId}, it will
      // be routed to the appropriate controller method
      registry.setApplicationDestinationPrefixes("/app");
   }

   @Override
   public void registerStompEndpoints(StompEndpointRegistry registry) {

      // registers /chat as the WebSocket endpoint. Clients will connect to this
      // endpoint to initiate a WebSocket connection
      // The method .withSockJS() enables SockJS fallback options, which ensures that
      // if a WebSocket connection cannot be established, SockJS will simulate one,
      // thereby ensuring robust communication
      registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
   }

}
