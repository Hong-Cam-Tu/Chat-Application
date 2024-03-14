package com.chatapplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.domains.messageimpl.GroupMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;

public class DeleteMessagePrivateGroupByAdmin 
        extends UseCase<DeleteMessagePrivateGroupByAdmin.InputValues,DeleteMessagePrivateGroupByAdmin.OutputValues> {
    private PrivateGroupStorage _privateGroupStorage;
    private GroupConversationStorage _groupConversationStorage;

    public DeleteMessagePrivateGroupByAdmin(PrivateGroupStorage privateGroupStorage,
            GroupConversationStorage _groupConversationStorage) {
        this._privateGroupStorage = privateGroupStorage;
        this._groupConversationStorage = _groupConversationStorage;
    }

    public class InputValues {
        private String _idPrivateGroup;
        private String _idAdmin;
        private String _idMessage;

        public InputValues( String idPrivateGroup, String idAdmin, String idMessage) {
            this._idPrivateGroup = idPrivateGroup;
            this._idAdmin = idAdmin;
            this._idMessage = idMessage;
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
        PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getById(input._idPrivateGroup);
        if(privateGroup == null) {
            return new OutputValues(Result.Failed, "Wrong id group");
        } else {
            if(!privateGroup.isAdmin(input._idAdmin)) {
                return new OutputValues(Result.Failed, "");
            } else {
                GroupConversation conversation = _groupConversationStorage.getConversation().getById(privateGroup.getIdConversation());
                GroupMessage message = conversation.getMessages().stream().filter(m->m.getID().equals(input._idMessage)).findFirst().get();
                if(message == null) {
                    return new OutputValues(Result.Failed, "Wrong id message");
                }else{
                    conversation.getMessages().remove(message);
                    return new OutputValues(Result.Successed, "Successed");
                }
            }
        }
    }
}
