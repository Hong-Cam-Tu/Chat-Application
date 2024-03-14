package com.chatapplication.domains.messageimpl;

import com.chatapplication.domains.Message;

public class IndividualMessage extends Message{
    private String _idReceiver;

    public IndividualMessage(String idSender,String idReceiver,String content) {
        super(idSender,content);
        _idReceiver = idReceiver;
    }
    
    public String getIdReceiver() {
        return _idReceiver;
    }
}
