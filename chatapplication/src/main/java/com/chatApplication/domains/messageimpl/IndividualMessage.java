package com.chatApplication.domains.messageimpl;

import com.chatApplication.domains.Message;

public class IndividualMessage extends Message{
    private String _idReceiver;

    public IndividualMessage(String idSender,String idReceiver) {
        super(idSender);
        _idReceiver = idReceiver;
    }
    
    public String getIdReceiver() {
        return _idReceiver;
    }
}
