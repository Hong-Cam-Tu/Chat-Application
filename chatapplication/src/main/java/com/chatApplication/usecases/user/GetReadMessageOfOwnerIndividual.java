package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class GetReadMessageOfOwnerIndividual 
        extends UseCase<GetReadMessageOfOwnerIndividual.InputGetReadMessageOfOwnerIndividual,
        GetReadMessageOfOwnerIndividual.OutputGetReadMessageOfOwnerIndividual> {
    private IndividualConversationStorage _individualConversationStorage;
    
    public GetReadMessageOfOwnerIndividual(IndividualConversationStorage _individualConversationStorage) {
        this._individualConversationStorage = _individualConversationStorage;
    }

    public class InputGetReadMessageOfOwnerIndividual {
        private String _idIndividualConversation;
        private String _idIndividualMessage;
        
        InputGetReadMessageOfOwnerIndividual(String idIndividualConverSation,String idIndividualMessage) {
            _idIndividualConversation =idIndividualConverSation;
            _idIndividualMessage = idIndividualMessage;
        }
    }

    public enum GetReadMessageOfOwnerIndividualResult {
        Successed,
        Failed;
     }

    public class OutputGetReadMessageOfOwnerIndividual {
        private final GetReadMessageOfOwnerIndividualResult _result;
        private final String _message;
        private final List<String> _idReadUsers;

        OutputGetReadMessageOfOwnerIndividual(GetReadMessageOfOwnerIndividualResult result, String message,List<String> idReadUsers) {
            _result = result;
            _message = message;
            _idReadUsers = idReadUsers;
        }

        public GetReadMessageOfOwnerIndividualResult getResult(){
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
    public OutputGetReadMessageOfOwnerIndividual excute(InputGetReadMessageOfOwnerIndividual input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation == null) {
            return new OutputGetReadMessageOfOwnerIndividual(GetReadMessageOfOwnerIndividualResult.Failed, "wrong id conversation",null);
        } else {
            IndividualMessage message = 
            conversation.getMessages().stream().filter(m->m.getID().equals(input._idIndividualMessage)).findFirst().get();
            if(message == null) {
                return new OutputGetReadMessageOfOwnerIndividual(GetReadMessageOfOwnerIndividualResult.Failed, "Wrong id Message",null);
            } else {
                List<String> idReadUsers = message.getUserRead();
                return new OutputGetReadMessageOfOwnerIndividual(GetReadMessageOfOwnerIndividualResult.Successed, "Successed", idReadUsers);
            }
        }
    }
}
