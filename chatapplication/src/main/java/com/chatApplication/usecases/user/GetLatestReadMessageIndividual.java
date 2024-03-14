package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class GetLatestReadMessageIndividual 
        extends UseCase<GetLatestReadMessageIndividual.InputGetLatestReadMessageIndividual,GetLatestReadMessageIndividual.OutputGetLatestReadMessageIndividual>{
    private IndividualConversationStorage _individualConversationStorage;
    
    public GetLatestReadMessageIndividual(IndividualConversationStorage _individualConversationStorage) {
        this._individualConversationStorage = _individualConversationStorage;
    }

    public class InputGetLatestReadMessageIndividual {
        private String _idIndividualConversation;
        private String _idUser;
        
        InputGetLatestReadMessageIndividual(String idIndividualConverSation,String idUser) {
            _idIndividualConversation =idIndividualConverSation;
            _idUser = idUser;
        }
    }

    public enum GetLatestReadMessageIndividualResult {
        Successed,
        Failed;
     }

    public class OutputGetLatestReadMessageIndividual {
        private final GetLatestReadMessageIndividualResult _result;
        private final String _message;
        private final IndividualMessage _individualMessage;

        OutputGetLatestReadMessageIndividual(GetLatestReadMessageIndividualResult result, String message,IndividualMessage individualMessage) {
            _result = result;
            _message = message;
            _individualMessage = individualMessage;
        }

        public GetLatestReadMessageIndividualResult getResult(){
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
    public OutputGetLatestReadMessageIndividual excute(InputGetLatestReadMessageIndividual input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation==null) {
            return new OutputGetLatestReadMessageIndividual(GetLatestReadMessageIndividualResult.Failed, "Wrong id conversation",null);
        } else {
            List<IndividualMessage> messages= conversation.getMessages().stream().filter(m->m.isReadMessage(input._idUser)).toList();
            if(messages.size()==0) {
                return new OutputGetLatestReadMessageIndividual( GetLatestReadMessageIndividualResult.Failed, null, null);
            } else {
                return new OutputGetLatestReadMessageIndividual(GetLatestReadMessageIndividualResult.Successed, "Successed", messages.get(messages.size()-1));
            }
        }
    }
}
