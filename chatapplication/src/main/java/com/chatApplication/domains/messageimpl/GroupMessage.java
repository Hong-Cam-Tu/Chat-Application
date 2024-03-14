package com.chatApplication.domains.messageimpl;

import com.chatApplication.domains.Message;

public class GroupMessage extends Message {
    private String _idGroup;

    public GroupMessage(String idSender,String idGroup) {
        super(idSender);
        _idGroup = idGroup;
    }
    
    public String getIdGroup() {
        return _idGroup;
    }
}
