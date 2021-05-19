package com.yalco.websockets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yalco.websockets.model.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WSController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

//    @MessageMapping("/message")
//    public void handleText(Message<String> message,  Principal principal){
//        String user = principal.getName();
//        messagingTemplate.convertAndSend("/broker/message/receive", "Yeess beee");
//    }

    @MessageMapping("/message")
    public void handleText(Message<String> message,  Principal principal) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleMessage sm = mapper.readValue(message.getPayload(), SimpleMessage.class);
        String user = principal.getName();
        messagingTemplate.convertAndSendToUser(sm.getToUser(),"/broker/yalco", sm.getContent()+" from  user:"+ sm.getFromUser());
    }
}
