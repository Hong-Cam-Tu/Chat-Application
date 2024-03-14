package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

public class GetReadMessageOfOwnerGroup 
        extends UseCase<GetReadMessageOfOwnerGroup.InputGetReadMessageOfOwnerGroup,GetReadMessageOfOwnerGroup.OutputGetReadMessageOfOwnerGroup> {
    private GroupConversationStorage _groupConversationStorage;
    
    public GetReadMessageOfOwnerGroup(GroupConversationStorage _groupConversationStorage) {
        this._groupConversationStorage = _groupConversationStorage;
    }

    public class InputGetReadMessageOfOwnerGroup {
        private String _idGroupConversation;
        private String _idGroupMessage;
        
        InputGetReadMessageOfOwnerGroup(String idGroupConverSation,String idGroupMessage) {
            _idGroupConversation =idGroupConverSation;
            _idGroupMessage = idGroupMessage;
        }
    }

    public enum GetReadMessageOfOwnerGroupResult {
        Successed,
        Failed;
     }

    public class OutputGetReadMessageOfOwnerGroup {
        private final GetReadMessageOfOwnerGroupResult _result;
        private final String _message;
        private final List<String> _idReadUsers;

        OutputGetReadMessageOfOwnerGroup(GetReadMessageOfOwnerGroupResult result, String message,List<String> idReadUsers) {
            _result = result;
            _message = message;
            _idReadUsers = idReadUsers;
        }

        public GetReadMessageOfOwnerGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<String> getReadUsers() {
            return _idReadUsers;
        }
    }

    @Override
    public OutputGetReadMessageOfOwnerGroup excute(InputGetReadMessageOfOwnerGroup input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idGroupConversation);
        if(conversation == null) {
            return new OutputGetReadMessageOfOwnerGroup(GetReadMessageOfOwnerGroupResult.Failed, "wrong id conversation",null);
        } else {
            GroupMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idGroupMessage)).findFirst().get();
            if(message == null) {
                return new OutputGetReadMessageOfOwnerGroup(GetReadMessageOfOwnerGroupResult.Failed, "Wrong id Message",null);
            } else {
                List<String> idReadUsers = message.getUserRead();
                return new OutputGetReadMessageOfOwnerGroup(GetReadMessageOfOwnerGroupResult.Successed, "Successed", idReadUsers);
            }
        }
    }
}
