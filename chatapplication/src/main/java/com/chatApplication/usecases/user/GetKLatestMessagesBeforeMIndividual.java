package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

import java.util.ArrayList;

public class GetKLatestMessagesBeforeMIndividual 
        extends UseCase<GetKLatestMessagesBeforeMIndividual.InputGetKLatestMessagesBeforeMIndividual,
        GetKLatestMessagesBeforeMIndividual.OutputGetKLatestMessagesBeforeMIndividual> {
    private IndividualConversationStorage _individualConversationStorage;

    public GetKLatestMessagesBeforeMIndividual(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public  static class InputGetKLatestMessagesBeforeMIndividual {
        private String _idConversation;
        private int _K;
        private int _M;

        public InputGetKLatestMessagesBeforeMIndividual(String idConversation, int K, int M) {
            _idConversation = idConversation;
            _K = K;
            _M = M;
        }
    }

    public static enum GetKLatestMessagesBeforeMIndividualResult {
        Successed,
        Failed;
     }

    public static class OutputGetKLatestMessagesBeforeMIndividual {
        private final GetKLatestMessagesBeforeMIndividualResult _result;
        private final String _message;
        private final List<IndividualMessage> _messages;

        public OutputGetKLatestMessagesBeforeMIndividual(GetKLatestMessagesBeforeMIndividualResult result, String message,List<IndividualMessage> messages) {
            _result = result;
            _message = message;
            _messages = messages;
        }

        public GetKLatestMessagesBeforeMIndividualResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<IndividualMessage> getMessages() {
            return _messages;
        }
    }

    @Override
    public OutputGetKLatestMessagesBeforeMIndividual excute(InputGetKLatestMessagesBeforeMIndividual input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputGetKLatestMessagesBeforeMIndividual(GetKLatestMessagesBeforeMIndividualResult.Failed, "Wrong id",null);
        } else {
            int messagesSize = conversation.getMessages().size();
            List<IndividualMessage> messages = new ArrayList<>();
            for(int i=messagesSize - input._K - input._M ; i < messagesSize-input._M;i++) {
                messages.add(conversation.getMessages().get(i));
            }
            return new OutputGetKLatestMessagesBeforeMIndividual(GetKLatestMessagesBeforeMIndividualResult.Successed, "Successed", messages);
        }
    }
}
