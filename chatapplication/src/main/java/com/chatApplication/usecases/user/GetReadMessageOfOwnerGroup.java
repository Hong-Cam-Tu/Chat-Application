package com.chatApplication.usecases.user;

import java.util.List;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.domains.messageimpl.GroupMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;

public class GetReadMessageOfOwnerGroup 
        extends UseCase<GetReadMessageOfOwnerGroup.InputValues,GetReadMessageOfOwnerGroup.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;
    
    public GetReadMessageOfOwnerGroup(GroupConversationStorage _groupConversationStorage) {
        this._groupConversationStorage = _groupConversationStorage;
    }

    public class InputValues {
        private String _idGroupConversation;
        private String _idGroupMessage;
        
        InputValues(String idGroupConverSation,String idGroupMessage) {
            _idGroupConversation =idGroupConverSation;
            _idGroupMessage = idGroupMessage;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<String> _idReadUsers;

        OutputValues(Result result, String message,List<String> idReadUsers) {
            _result = result;
            _message = message;
            _idReadUsers = idReadUsers;
        }

        public Result getResult(){
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
    public OutputValues excute(InputValues input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idGroupConversation);
        if(conversation == null) {
            return new OutputValues(Result.Failed, "wrong id conversation",null);
        } else {
            GroupMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idGroupMessage)).findFirst().get();
            if(message == null) {
                return new OutputValues(Result.Failed, "Wrong id Message",null);
            } else {
                List<String> idReadUsers = message.getUserRead();
                return new OutputValues(Result.Successed, "Successed", idReadUsers);
            }
        }
    }
}
