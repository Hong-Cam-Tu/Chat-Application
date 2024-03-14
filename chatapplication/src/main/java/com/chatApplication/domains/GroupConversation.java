package com.chatapplication.domains;

import java.util.ArrayList;
import java.util.List;

import com.chatApplication.domains.messageimpl.GroupMessage;

public class GroupConversation extends BaseEntity {
    private List<GroupMessage> _groupMessages;
    
    public GroupConversation() {
        super();
        _groupMessages = new ArrayList<>();
    }

    public List<GroupMessage> getMessages() {
        return _groupMessages;
    }

    public void setMessages(GroupMessage messages) {
        _groupMessages.add(messages);
    }
    
    public GroupMessage findMessageById(String idMessage) {
        for(GroupMessage message : _groupMessages) {
            if(message.getID().equals(idMessage)) {
                return message;
            }
        }
        return null;
    }

    public boolean removeMessage(GroupMessage message) {
        if(message==null) {
            return false;
        }
        _groupMessages.remove(message);
        return true;
    }
}
