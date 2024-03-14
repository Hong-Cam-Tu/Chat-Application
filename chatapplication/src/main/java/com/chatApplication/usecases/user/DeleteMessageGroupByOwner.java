package com.chatapplication.usecases.user;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class DeleteMessageGroupByOwner 
        extends UseCase<DeleteMessageGroupByOwner.InputDeleteByOwner,
        DeleteMessageGroupByOwner.OutputDeleteByOwner> {
    private GroupConversationStorage _groupConvesationStorage;
    private PublicGroupStorage _publicGroupStorage;

    public DeleteMessageGroupByOwner(GroupConversationStorage groupConvesationStorage,
            PublicGroupStorage publicGroupStorage) {
        this._groupConvesationStorage = groupConvesationStorage;
        this._publicGroupStorage = publicGroupStorage;
    }

    public static class InputDeleteByOwner {
        private String _idPublicGroup;
        private String _idMessage;
        private String _idSender;
        public InputDeleteByOwner(String idPublicGroup, String idMessage, String idSender) {
            this._idPublicGroup = idPublicGroup;
            this._idMessage = idMessage;
            this._idSender = idSender;
        }         
    }

    public static enum DeleteByOwnerResult {
        Successed,
        Failed;
     }

    public static class OutputDeleteByOwner {
        private final DeleteByOwnerResult _result;
        private final String _message;

        public OutputDeleteByOwner(DeleteByOwnerResult result, String message) {
            _result = result;
            _message = message;
        }

        public DeleteByOwnerResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputDeleteByOwner excute(InputDeleteByOwner input) {
        PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getById(input._idPublicGroup);

        if(publicGroup == null) {
            return new OutputDeleteByOwner(DeleteByOwnerResult.Failed, "Wrong id group");
        }else {
            GroupConversation conversation = _groupConvesationStorage.getConversation().getById(publicGroup.getIdConversation());
            GroupMessage message = conversation.getMessages().stream().filter(m->m.getID().equals(input._idMessage)).findFirst().get();

            if(message == null) {
                return new OutputDeleteByOwner(DeleteByOwnerResult.Failed, "Wrong id Message");
            }else{
                if(message.getIdSender().equals(input._idSender)) {
                    conversation.removeMessage(message);
                    return new OutputDeleteByOwner(DeleteByOwnerResult.Successed, "Successed");
                } else {
                    return new OutputDeleteByOwner(DeleteByOwnerResult.Failed, "Wrong id sender");
                }
            }
        }
       
    }
}
