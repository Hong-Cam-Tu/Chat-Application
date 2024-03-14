package com.chatapplication.domains;

import java.util.ArrayList;
import java.util.List;

import com.chatapplication.domains.messageimpl.IndividualMessage;

public class IndividualConversation extends BaseEntity {
    private List<IndividualMessage> _individualMessages;
    private String _idSender;
    private String _idReceiver;
    
    public IndividualConversation(String idSender,String idReceiver) {
        super();
        _individualMessages = new ArrayList<>();
        _idReceiver = idReceiver;
        _idSender = idSender;
    }
    //get id sender
    public String getIdSender() {
        return _idSender;
    }
    // set id sender
    public void setIdSender(String idSender) {
        this._idSender = idSender;
    }
    //get id receiver
    public String getIdReceiver() {
        return _idReceiver;
    }
    // set id receiver
    public void setIdReceiver(String idReceiver) {
        this._idReceiver = idReceiver;
    }
    //get list messages
    public List<IndividualMessage> getMessages() {
        return _individualMessages;
    }
    //add message
    public void setMessage(IndividualMessage message) {
        _individualMessages.add(message);
    }
    //find message by id message
    public IndividualMessage findMessageById(String idMessage) {
        for(IndividualMessage message : _individualMessages) {
            if(message.getID().equals(idMessage)) {
                return message;
            }
        }
        return null;
    }
    //remove message by object
    public boolean removeMessage(IndividualMessage message) {
        if(message==null) {
            return false;
        }
        _individualMessages.remove(message);
        return true;
    }
    //remove message by list object
    public boolean removeAllMessages(List<IndividualMessage> messages) {
        if(messages.size()==0) {
            return false;
        }
        _individualMessages.removeAll(messages);
        return true;
    }
}
