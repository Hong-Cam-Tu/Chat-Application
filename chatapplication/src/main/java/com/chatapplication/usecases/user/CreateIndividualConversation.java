package com.chatapplication.usecases.user;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class CreateIndividualConversation 
extends UseCase<CreateIndividualConversation.InputCreateIndividualConversation,
CreateIndividualConversation.OutputCreateIndividualConversation>{
    private IndividualConversationStorage _individualConversationStorage;

    public CreateIndividualConversation(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }
    
    public static class InputCreateIndividualConversation {
         private String _idSender;
         private String _idReceiver;

        public InputCreateIndividualConversation(String idSender, String idReceiver) {
            _idReceiver = idReceiver;
            _idSender = idSender;
        }
    }

    public static enum CreateIndividualConversationResult {
        Successed,
        Failed;
     }

    public static class OutputCreateIndividualConversation {
        private final CreateIndividualConversationResult _result;
        private final String _message;
        private final String _idIndividualConversation;

        public OutputCreateIndividualConversation
        (CreateIndividualConversationResult result, String message,String idIndividualConversation) {
            _result = result;
            _message = message;
            _idIndividualConversation =idIndividualConversation;
        }

        public CreateIndividualConversationResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public String getIdConversation() {
            return _idIndividualConversation;
        }
    }

    @Override
    public OutputCreateIndividualConversation excute(InputCreateIndividualConversation input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation()
        .getFirst(c->c.getIdReceiver().equals(input._idReceiver) && c.getIdSender().equals(input._idSender));
        if(conversation == null) {
            IndividualConversation individualConversation = new IndividualConversation(input._idSender, input._idReceiver);
            _individualConversationStorage.getConversation().add(individualConversation);
            return new OutputCreateIndividualConversation(CreateIndividualConversationResult.Successed, "Successed", individualConversation.getID());
        } else {
            return new OutputCreateIndividualConversation(CreateIndividualConversationResult.Failed, "Existed conversation", null);
        }
    }
}
