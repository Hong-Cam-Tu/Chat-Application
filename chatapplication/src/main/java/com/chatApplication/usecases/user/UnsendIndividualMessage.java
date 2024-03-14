package com.chatapplication.usecases.user;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.domains.messageimpl.IndividualMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;

public class UnsendIndividualMessage extends UseCase<UnsendIndividualMessage.InputValues,UnsendIndividualMessage.OutputValues> {
    private IndividualConversationStorage _individualConversationStorage;

    public UnsendIndividualMessage(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
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
            IndividualMessage message = conversation.findMessageById(input._idIndividualMessage);
            if(conversation.removeMessage(message)) {
                return new OutputValues(Result.Successed, "Successed");
            }else{
                return new OutputValues(Result.Failed, "Wrong id Message");
            }
        }
    }
}
