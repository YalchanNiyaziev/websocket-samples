package com.yalco.websockets.model;

public class ConversationMessage {
    private String messageContent;
    private String fromUser;

    public ConversationMessage() {
    }

    public ConversationMessage(String messageContent, String fromUser) {
        this.messageContent = messageContent;
        this.fromUser = fromUser;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
}
