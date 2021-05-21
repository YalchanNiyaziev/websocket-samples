package com.yalco.websockets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yalco.websockets.model.SimpleMessage;
import com.yalco.websockets.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Controller
public class WSController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    private ConversationService conversationService;

//    @MessageMapping("/message")
//    public void handleText(Message<String> message,  Principal principal){
//        String user = principal.getName();
//        messagingTemplate.convertAndSend("/broker/message/receive", "Yeess beee");
//    }

    @MessageMapping("/message")
    public void handleText(@Payload SimpleMessage sm, @Headers Map<String, String> headers, Principal principal) {
        String user = principal.getName();
        conversationService.addMessageToConversation(sm);
        messagingTemplate.convertAndSendToUser(sm.getToUser(),"/broker/yalco", sm);
    }
}
