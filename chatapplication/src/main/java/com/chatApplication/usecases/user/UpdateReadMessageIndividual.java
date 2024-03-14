package com.chatapplication.usecases.user;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class UpdateReadMessageIndividual 
        extends UseCase<UpdateReadMessageIndividual.InputValues,UpdateReadMessageIndividual.OutputValues> {
    private IndividualConversationStorage _individualConversationStorage;
    
    public UpdateReadMessageIndividual(IndividualConversationStorage _individualConversationStorage) {
        this._individualConversationStorage = _individualConversationStorage;
    }

    public class InputValues {
        private String _idIndividualConversation;
        private String _idIndividualMessage;
        private String _idUser;
        
        InputValues(String idIndividualConverSation,String idIndividualMessage,String idUser) {
            _idIndividualConversation =idIndividualConverSation;
            _idIndividualMessage = idIndividualMessage;
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
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation == null) {
            return new OutputValues(Result.Failed, "wrong id conversation");
        } else {
            IndividualMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idIndividualMessage)).findFirst().get();
            if(message == null) {
                return new OutputValues(Result.Failed, "Wrong id Message");
            } else {
                message.addUserRead(input._idUser);
                return new OutputValues(Result.Successed, "Successed");
            }

        }
    }
}
