package chatApplication.usecases.adapters;

import chatApplication.domains.User;

public class Message {
    private final String _massageId;
    private User _sender;
    private User _userReceiver;
    private String _groupReceiver;

    public Message(String _massageId) {
        this._massageId = _massageId;
    }

    public User getSender() {
        return _sender;
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
    
    
    
}
