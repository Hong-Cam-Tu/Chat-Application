package com.chatApplication.usecases.user;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;
import java.util.List;
import com.chatApplication.domains.messageimpl.IndividualMessage;

public class DeleteNLatestMessagesIndividual 
extends UseCase<DeleteNLatestMessagesIndividual.InputValues,DeleteNLatestMessagesIndividual.OutputValues> {
    private IndividualConversationStorage _individualConversationStorage;

    public DeleteNLatestMessagesIndividual(IndividualConversationStorage individualConversationStorage) {
        this._individualConversationStorage = individualConversationStorage;
    }

    public class InputValues {
        private String _idIndividualConversation;
        private String _idUser;
        private int  _N;

        public InputValues(String idIndividualConversation, String idUser, int N) {
            this._idIndividualConversation = idIndividualConversation;
            this._idUser = idUser;
            this._N = N;
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
            return new OutputValues(Result.Failed, "Wrong id conversation");
        } else {
            List<IndividualMessage> messages = conversation.getMessages().stream().filter(m -> m.getIdSender().equals(input._idUser)).toList();
            if(messages.size() > input._N ) {
                messages = messages.subList(messages.size()-input._N, messages.size());
            }
            conversation.removeAllMessages(messages);
            return new OutputValues(Result.Successed, "Successed");
        }
    }
}
