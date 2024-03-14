package com.chatapplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.domains.messageimpl.GroupMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;

public class UnsendGroupMessage extends UseCase<UnsendGroupMessage.InputValues,UnsendGroupMessage.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;

    public UnsendGroupMessage(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
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
            return new OutputValues(Result.Failed, "Wrong id conversation");
        } else {
            GroupMessage message = conversation.findMessageById(input._idGroupMessage);
            if(conversation.removeMessage(message)) {
                return new OutputValues(Result.Successed, "Successed");
            }else{
                return new OutputValues(Result.Failed, "Wrong id Message");
            }
        }
    }
}
