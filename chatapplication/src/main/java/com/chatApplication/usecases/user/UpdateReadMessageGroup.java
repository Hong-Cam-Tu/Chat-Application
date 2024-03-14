package com.chatApplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.domains.messageimpl.GroupMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;

public class UpdateReadMessageGroup 
        extends UseCase<UpdateReadMessageGroup.InputValues,UpdateReadMessageGroup.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;

    public UpdateReadMessageGroup(GroupConversationStorage groupConversationStorage) {
        this._groupConversationStorage = groupConversationStorage;
    }

    public class InputValues {
        private String _idGroupConversation;
        private String _idGroupMessage;
        private String _idUser;
        
        InputValues(String idGroupConverSation,String idGroupMessage,String idUser) {
            _idGroupConversation =idGroupConverSation;
            _idGroupMessage = idGroupMessage;
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

        OutputValues(Result result, String message) {
            _result = result;
            _message = message;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }
    @Override
    public OutputValues excute(InputValues input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idGroupConversation);
        if(conversation == null) {
            return new OutputValues(Result.Failed, "wrong id conversation");
        } else {
            GroupMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idGroupMessage)).findFirst().get();
            if(message == null) {
                return new OutputValues(Result.Failed, "Wrong id Message");
            } else {
                message.addUserRead(input._idUser);
                return new OutputValues(Result.Successed, "Successed");
            }

        }
    }
}
