package com.yalco.websockets.config;

import com.yalco.websockets.handler.CustomWebSocketHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry
//                .addEndpoint("/ws")
//                .setAllowedOrigins("/*")
//                .withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/broker");
//        registry.setApplicationDestinationPrefixes("/app");
//        registry.setUserDestinationPrefix("/chat_user");
//    }
//
//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//        return true;
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/broker");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/myuser");

    }

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                //Method .addInterceptors for enabling interceptor
//                .addInterceptors(new HttpSessionHandshakeInterceptor())
//                .setHandshakeHandler(new CustomWebSocketHandshakeHandler())
                .withSockJS();
    }
}
