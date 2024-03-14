package com.chatApplication.usecases.user;

import java.util.List;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.domains.messageimpl.IndividualMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;

public class GetReadMessageOfOwnerIndividual 
        extends UseCase<GetReadMessageOfOwnerIndividual.InputValues,GetReadMessageOfOwnerIndividual.OutputValues> {
    private IndividualConversationStorage _individualConversationStorage;
    
    public GetReadMessageOfOwnerIndividual(IndividualConversationStorage _individualConversationStorage) {
        this._individualConversationStorage = _individualConversationStorage;
    }

    public class InputValues {
        private String _idIndividualConversation;
        private String _idIndividualMessage;
        
        InputValues(String idIndividualConverSation,String idIndividualMessage) {
            _idIndividualConversation =idIndividualConverSation;
            _idIndividualMessage = idIndividualMessage;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<String> _idReadUsers;

        OutputValues(Result result, String message,List<String> idReadUsers) {
            _result = result;
            _message = message;
            _idReadUsers = idReadUsers;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<String> getReadUsers() {
            return _idReadUsers;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation == null) {
            return new OutputValues(Result.Failed, "wrong id conversation",null);
        } else {
            IndividualMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idIndividualMessage)).findFirst().get();
            if(message == null) {
                return new OutputValues(Result.Failed, "Wrong id Message",null);
            } else {
                List<String> idReadUsers = message.getUserRead();
                return new OutputValues(Result.Successed, "Successed", idReadUsers);
            }
        }
    }
}
