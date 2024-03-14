package com.chatapplication.domains;

import java.util.LinkedList;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Message extends BaseEntity{
    private String _idSender;
    private Date _timestamp;
    private LinkedList<String> _contents;
    private String _idFile;
    private List<String> _idUserReadMessage;

    public Message(String idSender) {
        super();
        _idSender = idSender;
        _contents = new LinkedList<>();
        _idUserReadMessage = new ArrayList<>();
    }

    public String getIdSender() {
        return _idSender;
    }

    public void setSender(String sender) {
        _idSender = sender;
    }

    public Date getTimestamp() {
        return _timestamp;
    }

    public void setTimestamp(Date timestamp) {
        _timestamp = timestamp;
    }

    public List<String> getAllContents() {
        return _contents;
    }

    public String getLastestContent() {
        return _contents.getFirst();
    }
    
    public void fixContent(String content) {
        this._contents.addFirst(content);
    }

    public String getIdFile() {
        return _idFile;
    }

    public void setIdFile(String idFile) {
        this._idFile = idFile;
    }

    public boolean addUserRead(String idUser) {
        if(idUser.equals("")) {
            return false;
        }
        _idUserReadMessage.add(idUser);
        return true;
    }

    public List<String> getUserRead() {
        return _idUserReadMessage;
    }

    public boolean isReadMessage(String idUSer) {
        if(_idUserReadMessage.contains(idUSer)) {
            return true;
        }
        return false;
    }
}
