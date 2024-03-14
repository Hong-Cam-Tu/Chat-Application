package com.chatApplication.usecases.user;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;
import java.util.List;
import com.chatApplication.domains.messageimpl.IndividualMessage;

public class FindMessagesContainKeywordsIndividual 
        extends UseCase<FindMessagesContainKeywordsIndividual.InputValues,
        FindMessagesContainKeywordsIndividual.OutputValues> {
    private IndividualConversationStorage _individualConversationStorage;

    public FindMessagesContainKeywordsIndividual(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public class InputValues {
        private String _idConversation;
        private String _keyWords;

        InputValues(String idConversation, String keywords) {
            _idConversation = idConversation;
            _keyWords = keywords;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<IndividualMessage> _messages;

        OutputValues(Result result, String message,List<IndividualMessage> messages) {
            _result = result;
            _message = message;
            _messages = messages;
        }

        public Result getResult(){
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
    public OutputValues excute(InputValues input) {
        IndividualConversation conversation =
         _individualConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputValues(Result.Failed, "Wrong id",null);
        } else {
            List<IndividualMessage> messages = 
            conversation.getMessages().stream().filter(m->m.getLastestContent().contains(input._keyWords)).toList();
            if(messages.size() == 0) {
                return new OutputValues(Result.Failed, "None fit keywords", messages);
            }
            return new OutputValues(Result.Successed, "Successed", messages);
        }
    }
}
