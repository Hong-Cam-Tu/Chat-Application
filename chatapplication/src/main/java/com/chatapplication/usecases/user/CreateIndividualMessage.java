package com.chatapplication.usecases.user;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class CreateIndividualMessage 
extends UseCase<CreateIndividualMessage.InputCreateIndividualMessage,
CreateIndividualMessage.OutputCreateIndividualMessage> {
    private IndividualConversationStorage _individualConversationStorage;

    public CreateIndividualMessage(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public static class InputCreateIndividualMessage {
        private String _idSender;
        private String _idReceiver;
        private String _idConversation;
        private String _content;
        
        public InputCreateIndividualMessage(String idSender,String idReceiver,String idConversation,String content) {
            _idSender =idSender;
            _idReceiver = idReceiver;
            _idConversation = idConversation;
            _content = content;
        }
    }

    public static enum CreateIndividualMessageResult {
        Successed,
        Failed;
     }

    public static class OutputCreateIndividualMessage {
        private final CreateIndividualMessageResult _result;
        private final String _message;
        private final String _idMessage;

        public OutputCreateIndividualMessage(CreateIndividualMessageResult result, String message,String idMessage) {
            _result = result;
            _message = message;
            _idMessage = idMessage;
        }

        public CreateIndividualMessageResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public String getIdMessage() {
            return _idMessage;
        }
    }

    @Override
    public OutputCreateIndividualMessage excute(InputCreateIndividualMessage input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idConversation);
        if(conversation == null) {
            return new OutputCreateIndividualMessage(CreateIndividualMessageResult.Failed, "Wrong id conversation",null);
        } else {
            IndividualMessage individualMessage = new IndividualMessage(input._idSender, input._idReceiver, input._content);
            conversation.setMessage(individualMessage);
            return new OutputCreateIndividualMessage(CreateIndividualMessageResult.Successed, "Successed",individualMessage.getID());
        }
    }
}
