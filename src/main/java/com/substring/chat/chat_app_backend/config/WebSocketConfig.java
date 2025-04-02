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

      // server will only send messages to clients who have subscribed to an endpoint
      // with topic as prefix e.g. /topic/messages
      registry.enableSimpleBroker("/topic");

      // client will send messages to server with endpoint as prefix e.g. /app/chat
      registry.setApplicationDestinationPrefixes("/app");
   }

   @Override
   public void registerStompEndpoints(StompEndpointRegistry registry) {

      // client will connect to this endpoint
      registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
   }

}
