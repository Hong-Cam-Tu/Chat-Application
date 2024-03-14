package com.chatApplication.usecases.user;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.domains.messageimpl.IndividualMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;
import java.util.List;

public class GetAllIndividualConversations 
        extends UseCase<GetAllIndividualConversations.InputValues,GetAllIndividualConversations.OutputValues> {
    private IndividualConversationStorage _individualConversationStorage;

    public GetAllIndividualConversations(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public class InputValues {
        private String _idUser;
        
        InputValues(String idUser) {
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
        private final List<IndividualConversation> _conversations;

        OutputValues(Result result, String message,List<IndividualConversation> conversations) {
            _result = result;
            _message = message;
            _conversations = conversations;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<IndividualConversation> getMessages() {
            return _conversations;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        List<IndividualConversation> conversations = _individualConversationStorage.getConversation().getAll(c->{
            List<IndividualMessage> messages = c.getMessages();
            for(IndividualMessage message : messages) {
                if(message.getIdSender().equals(input._idUser) || message.getIdReceiver().equals(input._idUser))
                return true;
            }
            return false;
        });
        if(conversations == null) {
            return new OutputValues(Result.Failed, "failed", null);
        } else {
            return new OutputValues(Result.Successed, "Successed", conversations);
        }
    }
}
