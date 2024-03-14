package com.chatapplication.usecases.user;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class UnsendIndividualMessage extends UseCase<UnsendIndividualMessage.InputUnsendIndividualMessage
,UnsendIndividualMessage.OutputUnsendIndividualMessage> {
    private IndividualConversationStorage _individualConversationStorage;

    public UnsendIndividualMessage(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public class InputUnsendIndividualMessage {
        private String _idIndividualConversation;
        private String _idIndividualMessage;
        
        InputUnsendIndividualMessage(String idIndividualConverSation,String idIndividualMessage) {
            _idIndividualConversation =idIndividualConverSation;
            _idIndividualMessage = idIndividualMessage;
        }
    }

    public enum UnsendIndividualMessageResult {
        Successed,
        Failed;
     }

    public class OutputUnsendIndividualMessage {
        private final UnsendIndividualMessageResult _result;
        private final String _message;

        OutputUnsendIndividualMessage(UnsendIndividualMessageResult result, String message) {
            _result = result;
            _message = message;
        }

        public UnsendIndividualMessageResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputUnsendIndividualMessage excute(InputUnsendIndividualMessage input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation == null) {
            return new OutputUnsendIndividualMessage(UnsendIndividualMessageResult.Failed, "Wrong id conversation");
        } else {
            IndividualMessage message = conversation.findMessageById(input._idIndividualMessage);
            if(conversation.removeMessage(message)) {
                return new OutputUnsendIndividualMessage(UnsendIndividualMessageResult.Successed, "Successed");
            }else{
                return new OutputUnsendIndividualMessage(UnsendIndividualMessageResult.Failed, "Wrong id Message");
            }
        }
    }
}
