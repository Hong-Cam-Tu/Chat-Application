package chatApplication.usecases.adapters;
import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.domains.User;

public class Conversation {
    private List<Message> _messages = new ArrayList<>();
    private Group  _group;
    private String _idConversation;

    public Conversation(Group group) {
        this._group= group;
    }
    
    public String getIdConversation() {
        return _idConversation;
    }

    public void set_idConversation(String idConversation) {
        this._idConversation = idConversation;
    }

    public List<Message> getMessages(String idMember) {
        if(getGroup().isWithinGroup(idMember)) {
            return _messages;
        }
        return null;
    }

    public void addMessage(Message _messages) {
        this._messages.add(_messages);
    }

    public Group getGroup() {
        return _group;
    }

    public Message findMessageById(String idMessage) {
        for(Message message:_messages) {
            if(idMessage.equals(message.getMassageId())) {
                return message;
            }
        }
        return null;
    }
    
    public void deleteMessage(String idMessage) {
        _messages.remove(findMessageById(idMessage));
    }
}