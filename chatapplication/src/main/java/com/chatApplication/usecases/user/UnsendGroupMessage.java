package com.chatapplication.usecases.user;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

public class UnsendGroupMessage extends UseCase<UnsendGroupMessage.InputUnsendGroupMessage,UnsendGroupMessage.OutputUnsendGroupMessage> {
    private GroupConversationStorage _groupConversationStorage;

    public UnsendGroupMessage(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public class InputUnsendGroupMessage {
        private String _idGroupConversation;
        private String _idGroupMessage;
        
        InputUnsendGroupMessage(String idGroupConverSation,String idGroupMessage) {
            _idGroupConversation =idGroupConverSation;
            _idGroupMessage = idGroupMessage;
        }
    }

    public enum UnsendGroupMessageResult {
        Successed,
        Failed;
     }

    public class OutputUnsendGroupMessage {
        private final UnsendGroupMessageResult _result;
        private final String _message;

        OutputUnsendGroupMessage(UnsendGroupMessageResult result, String message) {
            _result = result;
            _message = message;
        }

        public UnsendGroupMessageResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputUnsendGroupMessage excute(InputUnsendGroupMessage input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idGroupConversation);
        if(conversation == null) {
            return new OutputUnsendGroupMessage(UnsendGroupMessageResult.Failed, "Wrong id conversation");
        } else {
            GroupMessage message = conversation.findMessageById(input._idGroupMessage);
            if(conversation.removeMessage(message)) {
                return new OutputUnsendGroupMessage(UnsendGroupMessageResult.Successed, "Successed");
            }else{
                return new OutputUnsendGroupMessage(UnsendGroupMessageResult.Failed, "Wrong id Message");
            }
        }
    }
}
