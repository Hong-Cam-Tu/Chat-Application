package com.chatApplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;
import java.util.List;
import com.chatApplication.domains.messageimpl.GroupMessage;

public class GetAllGroupConversations 
        extends UseCase<GetAllGroupConversations.InputValues,GetAllGroupConversations.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;

    public GetAllGroupConversations(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public class InputValues {
        private String _idUser;

        InputValues(String idUser) {
            _idUser = idUser;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<GroupConversation> _conversations;

        OutputValues(Result result, String message,List<GroupConversation> conversations) {
            _result = result;
            _message = message;
            _conversations = conversations;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<GroupConversation> getConversations() {
            return _conversations;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        List<GroupConversation> conversations = _groupConversationStorage.getConversation().getAll(c->{
            List<GroupMessage> messages = c.getMessages();
            for(GroupMessage message : messages) {
                if(message.getIdSender().equals(input._idUser))
                return true;
            }
            return false;
        });
        if(conversations == null) {
            return new OutputValues(Result.Failed, "failed", null);
        } else {
            return new OutputValues(Result.Successed, "Successed", conversations);
        }
    }
}
