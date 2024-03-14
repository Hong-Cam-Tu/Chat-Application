package com.chatapplication.usecases.user;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

public class CreateGroupMessage 
extends UseCase<CreateGroupMessage.InputCreateGroupMessage,
CreateGroupMessage.OutputCreateGroupMessage> {
    private GroupConversationStorage _groupConversationStorage;

    public CreateGroupMessage(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public static class InputCreateGroupMessage {
        private String _idGroup;
        private String _idUser;
        private String _idConversation;
        private String _content;
        
        public InputCreateGroupMessage(String idGroup,String idUser,String idConversation,String content) {
            _idGroup =idGroup;
            _idUser = idUser;
            _idConversation = idConversation;
            _content = content;
        }
    }

    public static enum CreateGroupMessageResult {
        Successed,
        Failed;
     }

    public static class OutputCreateGroupMessage {
        private final CreateGroupMessageResult _result;
        private final String _message;
        private final String _idMessage;

        public OutputCreateGroupMessage(CreateGroupMessageResult result, String message,String idMessage) {
            _result = result;
            _message = message;
            _idMessage = idMessage;
        }

        public CreateGroupMessageResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public String getIdMassage() {
            return _idMessage;
        }
    }

    @Override
    public OutputCreateGroupMessage excute(InputCreateGroupMessage input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idConversation);
        if(conversation == null) {
            return new OutputCreateGroupMessage(CreateGroupMessageResult.Failed, "Wrong id conversation",null);
        }
        GroupMessage groupMessage = new GroupMessage(input._idUser, input._idGroup,input._content);
        conversation.setMessages(groupMessage);
        return new OutputCreateGroupMessage(CreateGroupMessageResult.Successed, "Successed",groupMessage.getID());
    }
}
