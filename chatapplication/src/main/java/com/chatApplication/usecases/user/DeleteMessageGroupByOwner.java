package com.chatapplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.domains.messageimpl.GroupMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class DeleteMessageGroupByOwner 
        extends UseCase<DeleteMessageGroupByOwner.InputValues,DeleteMessageGroupByOwner.OutputValues> {
    private GroupConversationStorage _groupConvesationStorage;
    private PublicGroupStorage _publicGroupStorage;

    public DeleteMessageGroupByOwner(GroupConversationStorage groupConvesationStorage,
            PublicGroupStorage publicGroupStorage) {
        this._groupConvesationStorage = groupConvesationStorage;
        this._publicGroupStorage = publicGroupStorage;
    }

    public class InputValues {
        private String _idPublicGroup;
        private String _idMessage;
        private String _idSender;
        public InputValues(String idPublicGroup, String idMessage, String idSender) {
            this._idPublicGroup = idPublicGroup;
            this._idMessage = idMessage;
            this._idSender = idSender;
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
        PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getById(input._idPublicGroup);

        if(publicGroup == null) {
            return new OutputValues(Result.Failed, "Wrong id group");
        }else {
            GroupConversation conversation = _groupConvesationStorage.getConversation().getById(publicGroup.getIdConversation());
            GroupMessage message = conversation.getMessages().stream().filter(m->m.getID().equals(input._idMessage)).findFirst().get();

            if(message == null) {
                return new OutputValues(Result.Failed, "Wrong id Message");
            }else{
                if(message.getIdSender().equals(input._idSender)) {
                    conversation.removeMessage(message);
                    return new OutputValues(Result.Successed, "Successed");
                } else {
                    return new OutputValues(Result.Failed, "Wrong id sender");
                }
            }
        }
       
    }
}
