package com.chatapplication.domains.messageimpl;

import com.chatapplication.domains.Message;

public class GroupMessage extends Message {
    private String _idGroup;

    public GroupMessage(String idSender,String idGroup,String content) {
        super(idSender,content);
        _idGroup = idGroup;
    }
    
    public String getIdGroup() {
        return _idGroup;
    }
}
