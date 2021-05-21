package com.yalco.websockets.controller;

import com.yalco.websockets.model.ConversationMessage;
import com.yalco.websockets.model.SimpleMessage;
import com.yalco.websockets.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @PostMapping("/messages")
    public ResponseEntity<Void> addConversationMessage(@RequestBody SimpleMessage sm){
        conversationService.addMessageToConversation(sm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/messages/{userOne}/{userTwo}")
    public List<ConversationMessage> getConversationMessages(@PathVariable String userOne, @PathVariable String userTwo){
        return conversationService.getConversationMessage(userOne, userTwo);
    }


}
