package chatApplication.infrastructure.conversationstorage;

import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.usecases.adapters.Conversation;

public class ConversationStorage extends Conversation {
    private List<Conversation> _conversations = new ArrayList<>();

    public List<Conversation> getConversations() {
        return _conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this._conversations = conversations;
    }

    public Conversation getConversationById(String idConversation) {
        for(Conversation conversation:_conversations) {
            if(idConversation.equals(conversation.getIdConversation())) {
                return conversation;
            }
        }
        return null;
    }

    public ConversationStorage(Group group) {
        super(group);
        //TODO Auto-generated constructor stub
    }
    
}
