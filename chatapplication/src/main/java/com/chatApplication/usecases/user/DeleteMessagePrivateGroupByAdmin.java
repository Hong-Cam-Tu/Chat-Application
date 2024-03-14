package com.chatapplication.usecases.user;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;

public class DeleteMessagePrivateGroupByAdmin 
        extends UseCase<DeleteMessagePrivateGroupByAdmin.InputDeleteMessagePrivateGroup,
        DeleteMessagePrivateGroupByAdmin.OutputDeleteMessagePrivateGroup> {
    private PrivateGroupStorage _privateGroupStorage;
    private GroupConversationStorage _groupConversationStorage;

    public DeleteMessagePrivateGroupByAdmin(PrivateGroupStorage privateGroupStorage,
            GroupConversationStorage groupConversationStorage) {
        this._privateGroupStorage = privateGroupStorage;
        this._groupConversationStorage = groupConversationStorage;
    }

    public static class InputDeleteMessagePrivateGroup {
        private String _idPrivateGroup;
        private String _idAdmin;
        private String _idMessage;

        public InputDeleteMessagePrivateGroup( String idPrivateGroup, String idAdmin, String idMessage) {
            this._idPrivateGroup = idPrivateGroup;
            this._idAdmin = idAdmin;
            this._idMessage = idMessage;
        }         
    }

    public static enum DeleteMessagePrivateGroupResult {
        Successed,
        Failed;
     }

    public static class OutputDeleteMessagePrivateGroup {
        private final DeleteMessagePrivateGroupResult _result;
        private final String _message;

        public OutputDeleteMessagePrivateGroup(DeleteMessagePrivateGroupResult result, String message) {
            _result = result;
            _message = message;
        }

        public DeleteMessagePrivateGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputDeleteMessagePrivateGroup excute(InputDeleteMessagePrivateGroup input) {
        PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getById(input._idPrivateGroup);
        if(privateGroup == null) {
            return new OutputDeleteMessagePrivateGroup(DeleteMessagePrivateGroupResult.Failed, "Wrong id group");
        } else {
            if(!privateGroup.isAdmin(input._idAdmin)) {
                return new OutputDeleteMessagePrivateGroup(DeleteMessagePrivateGroupResult.Failed, "");
            } else {
                GroupConversation conversation = _groupConversationStorage.getConversation().getById(privateGroup.getIdConversation());
                GroupMessage message = conversation.getMessages().stream().filter(m->m.getID().equals(input._idMessage)).findFirst().get();
                if(message == null) {
                    return new OutputDeleteMessagePrivateGroup(DeleteMessagePrivateGroupResult.Failed, "Wrong id message");
                }else{
                    conversation.getMessages().remove(message);
                    return new OutputDeleteMessagePrivateGroup(DeleteMessagePrivateGroupResult.Successed, "Successed");
                }
            }
        }
    }
}
