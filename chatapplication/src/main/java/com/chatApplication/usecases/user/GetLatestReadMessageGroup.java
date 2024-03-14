package com.chatApplication.usecases.user;

import java.util.List;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.domains.messageimpl.GroupMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;

public class GetLatestReadMessageGroup 
        extends UseCase<GetLatestReadMessageGroup.InputValues,GetLatestReadMessageGroup.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;

    public GetLatestReadMessageGroup(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public class InputValues {
        private String _idGroupConversation;
        private String _idUser;
        
        InputValues(String idGroupConverSation,String idUser) {
            _idGroupConversation =idGroupConverSation;
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
        private final GroupMessage _groupMessage;

        OutputValues(Result result, String message,GroupMessage groupMessage) {
            _result = result;
            _message = message;
            _groupMessage = groupMessage;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public GroupMessage getIndividualMessage() {
            return _groupMessage;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idGroupConversation);
        if(conversation==null) {
            return new OutputValues(Result.Failed, "Wrong id conversation",null);
        } else {
            List<GroupMessage> messages= conversation.getMessages().stream().filter(m->m.isReadMessage(input._idUser)).toList();
            if(messages.size()==0) {
                return new OutputValues( Result.Failed, null, null);
            } else {
                return new OutputValues(Result.Successed, "Successed", messages.get(messages.size()-1));
            }
        }
    }
}
