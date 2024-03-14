package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class GetAllIndividualConversations 
        extends UseCase<GetAllIndividualConversations.InputGetAllIndividualConversations,
        GetAllIndividualConversations.OutputGetAllIndividualConversations> {
    private IndividualConversationStorage _individualConversationStorage;

    public GetAllIndividualConversations(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public static class InputGetAllIndividualConversations {
        private String _idUser;
        
        public InputGetAllIndividualConversations(String idUser) {
            _idUser = idUser;
        }
    }

    public static enum GetAllIndividualConversationsResult {
        Successed,
        Failed;
     }

    public static class OutputGetAllIndividualConversations {
        private final GetAllIndividualConversationsResult _result;
        private final String _message;
        private final List<IndividualConversation> _conversations;

        public OutputGetAllIndividualConversations(GetAllIndividualConversationsResult result, String message,List<IndividualConversation> conversations) {
            _result = result;
            _message = message;
            _conversations = conversations;
        }

        public GetAllIndividualConversationsResult getResult(){
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
    public OutputGetAllIndividualConversations excute(InputGetAllIndividualConversations input) {
        List<IndividualConversation> conversations = _individualConversationStorage.getConversation().getAll(c->{
            List<IndividualMessage> messages = c.getMessages();
            for(IndividualMessage message : messages) {
                if(message.getIdSender().equals(input._idUser) || message.getIdReceiver().equals(input._idUser))
                return true;
            }
            return false;
        });
        if(conversations == null) {
            return new OutputGetAllIndividualConversations(GetAllIndividualConversationsResult.Failed, "failed", null);
        } else {
            return new OutputGetAllIndividualConversations(GetAllIndividualConversationsResult.Successed, "Successed", conversations);
        }
    }
}
