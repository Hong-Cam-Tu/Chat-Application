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

    public Message(String idSender,String content) {
        super();
        _idSender = idSender;
        _contents = new LinkedList<>();
        _idUserReadMessage = new ArrayList<>();
        fixContent(content);
    }
    //get id sender
    public String getIdSender() {
        return _idSender;
    }
    //set id sender
    public void setSender(String sender) {
        _idSender = sender;
    }
    //get date
    public Date getTimestamp() {
        return _timestamp;
    }
    //set date
    public void setTimestamp(Date timestamp) {
        _timestamp = timestamp;
    }
    //get all history contents
    public List<String> getAllContents() {
        return _contents;
    }
    //get recently content
    public String getLastestContent() {
        return _contents.getFirst();
    }
    //fix content
    public void fixContent(String content) {
        this._contents.addFirst(content);
    }
    //get id file
    public String getIdFile() {
        return _idFile;
    }
    //set id file
    public void setIdFile(String idFile) {
        this._idFile = idFile;
    }
    // add user have read message
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
