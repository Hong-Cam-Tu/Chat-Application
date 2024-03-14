package com.chatapplication.usecases.user;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class UpdateReadMessageIndividual 
        extends UseCase<UpdateReadMessageIndividual.InputUpdateReadMessagesIndividual
        ,UpdateReadMessageIndividual.OutputUpdateReadMessagesIndividual> {
    private IndividualConversationStorage _individualConversationStorage;
    
    public UpdateReadMessageIndividual(IndividualConversationStorage _individualConversationStorage) {
        this._individualConversationStorage = _individualConversationStorage;
    }

    public static class InputUpdateReadMessagesIndividual {
        private String _idIndividualConversation;
        private String _idIndividualMessage;
        private String _idUser;
        
        public InputUpdateReadMessagesIndividual(String idIndividualConverSation,String idIndividualMessage,String idUser) {
            _idIndividualConversation =idIndividualConverSation;
            _idIndividualMessage = idIndividualMessage;
            _idUser = idUser;
        }
    }

    public static enum UpdateReadMessagesIndividualResult {
        Successed,
        Failed;
     }

    public static class OutputUpdateReadMessagesIndividual {
        private final UpdateReadMessagesIndividualResult _result;
        private final String _message;

        public OutputUpdateReadMessagesIndividual(UpdateReadMessagesIndividualResult result, String message) {
            _result = result;
            _message = message;
        }

        public UpdateReadMessagesIndividualResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputUpdateReadMessagesIndividual excute(InputUpdateReadMessagesIndividual input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation == null) {
            return new OutputUpdateReadMessagesIndividual(UpdateReadMessagesIndividualResult.Failed, "wrong id conversation");
        } else {
            IndividualMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idIndividualMessage)).findFirst().get();
            if(message == null) {
                return new OutputUpdateReadMessagesIndividual(UpdateReadMessagesIndividualResult.Failed, "Wrong id Message");
            } else {
                message.addUserRead(input._idUser);
                return new OutputUpdateReadMessagesIndividual(UpdateReadMessagesIndividualResult.Successed, "Successed");
            }

        }
    }
}
