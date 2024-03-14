package com.chatapplication.usecases.user;

import java.util.List;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.domains.messageimpl.IndividualMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;

public class GetLatestReadMessageIndividual 
        extends UseCase<GetLatestReadMessageIndividual.InputValues,GetLatestReadMessageIndividual.OutputValues>{
    private IndividualConversationStorage _individualConversationStorage;
    
    public GetLatestReadMessageIndividual(IndividualConversationStorage _individualConversationStorage) {
        this._individualConversationStorage = _individualConversationStorage;
    }

    public class InputValues {
        private String _idIndividualConversation;
        private String _idUser;
        
        InputValues(String idIndividualConverSation,String idUser) {
            _idIndividualConversation =idIndividualConverSation;
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
        private final IndividualMessage _individualMessage;

        OutputValues(Result result, String message,IndividualMessage individualMessage) {
            _result = result;
            _message = message;
            _individualMessage = individualMessage;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public IndividualMessage getIndividualMessage() {
            return _individualMessage;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation==null) {
            return new OutputValues(Result.Failed, "Wrong id conversation",null);
        } else {
            List<IndividualMessage> messages= conversation.getMessages().stream().filter(m->m.isReadMessage(input._idUser)).toList();
            if(messages.size()==0) {
                return new OutputValues( Result.Failed, null, null);
            } else {
                return new OutputValues(Result.Successed, "Successed", messages.get(messages.size()-1));
            }
        }
    }
}
