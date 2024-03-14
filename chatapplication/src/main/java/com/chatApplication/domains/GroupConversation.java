package com.chatapplication.domains;

import java.util.ArrayList;
import java.util.List;

import com.chatapplication.domains.messageimpl.GroupMessage;

public class GroupConversation extends BaseEntity {
    private List<GroupMessage> _groupMessages;
    
    public GroupConversation() {
        super();
        _groupMessages = new ArrayList<>();
    }
    //get message
    public List<GroupMessage> getMessages() {
        return _groupMessages;
    }
    // add message
    public void setMessages(GroupMessage messages) {
        _groupMessages.add(messages);
    }
    //Get message by ID
    public GroupMessage findMessageById(String idMessage) {
        for(GroupMessage message : _groupMessages) {
            if(message.getID().equals(idMessage)) {
                return message;
            }
        }
        return null;
    }
    // remove message
    public boolean removeMessage(GroupMessage message) {
        if(message==null) {
            return false;
        }
        _groupMessages.remove(message);
        return true;
    }
}
