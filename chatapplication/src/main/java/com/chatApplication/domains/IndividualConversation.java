package com.chatApplication.domains;

import java.util.ArrayList;
import java.util.List;

import com.chatApplication.domains.messageimpl.IndividualMessage;

public class IndividualConversation extends BaseEntity {
    private List<IndividualMessage> _individualMessages;
    
    public IndividualConversation() {
        super();
        _individualMessages = new ArrayList<>();
    }

    public List<IndividualMessage> getMessages() {
        return _individualMessages;
    }

    public void setMessages(IndividualMessage messages) {
        _individualMessages.add(messages);
    }
    
    public IndividualMessage findMessageById(String idMessage) {
        for(IndividualMessage message : _individualMessages) {
            if(message.getID().equals(idMessage)) {
                return message;
            }
        }
        return null;
    }

    public boolean removeMessage(IndividualMessage message) {
        if(message==null) {
            return false;
        }
        _individualMessages.remove(message);
        return true;
    }

    public boolean removeAllMessages(List<IndividualMessage> messages) {
        if(messages.size()==0) {
            return false;
        }
        _individualMessages.removeAll(messages);
        return true;
    }
}
