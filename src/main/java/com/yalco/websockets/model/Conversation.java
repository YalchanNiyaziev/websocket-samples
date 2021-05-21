package com.yalco.websockets.model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<ConversationMessage> messages;
    private String userOne;
    private String userTwo;

    public Conversation() {
        this.messages = new ArrayList<>();
    }

    public Conversation(String userOne, String userTwo) {
        this.messages = new ArrayList<>();
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public List<ConversationMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ConversationMessage> messages) {
        this.messages = messages;
    }

    public String getUserOne() {
        return userOne;
    }

    public void setUserOne(String userOne) {
        this.userOne = userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(String userTwo) {
        this.userTwo = userTwo;
    }

    public void addMessage(ConversationMessage message){
        messages.add(message);
    }
}
