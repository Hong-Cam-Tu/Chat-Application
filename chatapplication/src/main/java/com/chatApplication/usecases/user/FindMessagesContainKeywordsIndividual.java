package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class FindMessagesContainKeywordsIndividual 
        extends UseCase<FindMessagesContainKeywordsIndividual.InputFindMessageIndividual,
        FindMessagesContainKeywordsIndividual.OutputFindMessageIndividual> {
    private IndividualConversationStorage _individualConversationStorage;

    public FindMessagesContainKeywordsIndividual(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public static class InputFindMessageIndividual {
        private String _idConversation;
        private String _keyWords;

        public InputFindMessageIndividual(String idConversation, String keywords) {
            _idConversation = idConversation;
            _keyWords = keywords;
        }
    }

    public static enum FindMessageIndividualResult {
        Successed,
        Failed;
     }

    public static class OutputFindMessageIndividual {
        private final FindMessageIndividualResult _result;
        private final String _message;
        private final List<IndividualMessage> _messages;

        public OutputFindMessageIndividual(FindMessageIndividualResult result, String message,List<IndividualMessage> messages) {
            _result = result;
            _message = message;
            _messages = messages;
        }

        public FindMessageIndividualResult getResult(){
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
    public OutputFindMessageIndividual excute(InputFindMessageIndividual input) {
        IndividualConversation conversation =
         _individualConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputFindMessageIndividual(FindMessageIndividualResult.Failed, "Wrong id",null);
        } else {
            List<IndividualMessage> messages = 
            conversation.getMessages().stream().filter(m->m.getLastestContent().contains(input._keyWords)).toList();
            if(messages.size() == 0) {
                return new OutputFindMessageIndividual(FindMessageIndividualResult.Failed, "None fit keywords", messages);
            }
            return new OutputFindMessageIndividual(FindMessageIndividualResult.Successed, "Successed", messages);
        }
    }
}
