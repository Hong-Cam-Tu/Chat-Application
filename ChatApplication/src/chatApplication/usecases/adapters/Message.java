package chatApplication.usecases.adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import chatApplication.domains.User;
import chatApplication.files.File;

public class Message {
    private final String _massageId;
    private User _sender;
    private User _userReceiver;
    private String _groupReceiver;
    private Date _timestamp;
    private List<String> _contents = new ArrayList<>();
    private List<File> _attachments = new ArrayList<>();

    public Message(String _massageId) {
        this._massageId = _massageId;
    }

    public User getSender() {
        return _sender;
    }
    
    public String getMassageId() {
        return _massageId;
    }

    public void setSender(User sender) {
        _sender = sender;
    }

    public User getUserReceiver() {
        return _userReceiver;
    }

    public void set_userReceiver(User userReceiver) {
        _userReceiver = userReceiver;
    }

    public String getGroupReceiver() {
        return _groupReceiver;
    }

    public void setGroupReceiver(String groupReceiver) {
        _groupReceiver = groupReceiver;
    }

    public Date getTimestamp() {
        return _timestamp;
    }

    public void setTimestamp(Date timestamp) {
        _timestamp = timestamp;
    }

    public List<String> getContents() {
        return _contents;
    }

    public void addContent(String content) {
        this._contents.add(content);
    }

    public List<File> getAttachments() {
        return _attachments;
    }

    public void addAttachment(File attachment) {
        _attachments.add(attachment);
    }
    
    
    
}
