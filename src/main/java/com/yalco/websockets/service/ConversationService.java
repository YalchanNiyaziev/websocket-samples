package com.yalco.websockets.service;

import com.yalco.websockets.model.Conversation;
import com.yalco.websockets.model.ConversationMessage;
import com.yalco.websockets.model.SimpleMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationService {
    private final List<Conversation> conversations = new ArrayList<>();

    @PostConstruct
    private void initialConversation(){
        ConversationMessage messageOneBill = new ConversationMessage("Hey bill", "kim");
        ConversationMessage messageTwoBill = new ConversationMessage("What's going on", "kim");
        ConversationMessage messageOneKim = new ConversationMessage("Hi kim", "bill");
        ConversationMessage messageTwoKim = new ConversationMessage("i am fine", "bill");
        ConversationMessage messageThreeKim = new ConversationMessage("What about you", "bill");

        Conversation conversation = new Conversation();
        conversation.setUserOne("kim");
        conversation.setUserTwo("bill");

        conversation.addMessage(messageOneBill);
        conversation.addMessage(messageTwoBill);
        conversation.addMessage(messageOneKim);
        conversation.addMessage(messageTwoKim);
        conversation.addMessage(messageThreeKim);

        this.conversations.add(conversation);
    }

    public void addMessageToConversation(SimpleMessage sm) {
        Conversation conversation = getConversationByUsers(sm.getFromUser(), sm.getToUser());

        ConversationMessage message = new ConversationMessage();
        message.setFromUser(sm.getFromUser());
        message.setMessageContent(sm.getContent());

        conversation.getMessages().add(message);

        int d = 4;

    }

    public List<ConversationMessage> getConversationMessage(String userOne, String userTwo) {
        Conversation conversation = getConversationByUsers(userOne, userTwo);
        return conversation.getMessages();
    }

    private Conversation getConversationByUsers(String usernameOne, String usernameTwo) {
        validateConversationUsers(usernameOne, usernameTwo);

        List<Conversation> foundConversations = conversations.stream().filter(
                c -> (c.getUserOne().equals(usernameOne) || c.getUserOne().equals(usernameTwo))
                        && (c.getUserTwo().equals(usernameOne) || c.getUserTwo().equals(usernameTwo)))
                .collect(Collectors.toList());

        if (foundConversations.size() > 1) {
            throw new RuntimeException("There is more than one conversation between those users with username: " + usernameOne + " and username:" + usernameTwo);
        }

        Conversation conversation = null;
        if (foundConversations.size() == 0) {
            conversation = createConversation(usernameOne, usernameTwo);
        } else {
            conversation = foundConversations.get(0);
        }
        return conversation;
    }

    private Conversation createConversation(String usernameOne, String usernameTwo) {
        Conversation conversation = new Conversation();
        conversation.setUserOne(usernameOne);
        conversation.setUserTwo(usernameTwo);
        conversations.add(conversation);
        return conversation;
    }

    private void validateConversationUsers(String usernameOne, String usernameTwo) {
        if (usernameOne == null || usernameTwo == null) {
            throw new IllegalArgumentException("The given usernames must not be null");
        }
        if (usernameOne.equals(usernameTwo)) {
            throw new IllegalArgumentException("The given usernames must not be equal");
        }
    }
}
