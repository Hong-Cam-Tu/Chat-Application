package com.chatapplication.usecases.user;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

public class UpdateReadMessageGroup 
        extends UseCase<UpdateReadMessageGroup.InputUpdateReadMessageGroup,UpdateReadMessageGroup.OutputUpdateReadMessageGroup> {
    private GroupConversationStorage _groupConversationStorage;

    public UpdateReadMessageGroup(GroupConversationStorage groupConversationStorage) {
        this._groupConversationStorage = groupConversationStorage;
    }

    public static class InputUpdateReadMessageGroup {
        private String _idGroupConversation;
        private String _idGroupMessage;
        private String _idUser;
        
        public InputUpdateReadMessageGroup(String idGroupConverSation,String idGroupMessage,String idUser) {
            _idGroupConversation =idGroupConverSation;
            _idGroupMessage = idGroupMessage;
            _idUser = idUser;
        }
    }

    public static enum UpdateReadMessageGroupResult {
        Successed,
        Failed;
     }

    public static class OutputUpdateReadMessageGroup {
        private final UpdateReadMessageGroupResult _result;
        private final String _message;

        public OutputUpdateReadMessageGroup(UpdateReadMessageGroupResult result, String message) {
            _result = result;
            _message = message;
        }

        public UpdateReadMessageGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }
    @Override
    public OutputUpdateReadMessageGroup excute(InputUpdateReadMessageGroup input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idGroupConversation);
        if(conversation == null) {
            return new OutputUpdateReadMessageGroup(UpdateReadMessageGroupResult.Failed, "wrong id conversation");
        } else {
            GroupMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idGroupMessage)).findFirst().get();
            if(message == null) {
                return new OutputUpdateReadMessageGroup(UpdateReadMessageGroupResult.Failed, "Wrong id Message");
            } else {
                message.addUserRead(input._idUser);
                return new OutputUpdateReadMessageGroup(UpdateReadMessageGroupResult.Successed, "Successed");
            }

        }
    }
}
