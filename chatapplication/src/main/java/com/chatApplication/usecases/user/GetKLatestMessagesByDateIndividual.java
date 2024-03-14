package com.chatapplication.usecases.user;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;
import java.util.List;
import com.chatApplication.domains.messageimpl.IndividualMessage;
import java.util.Date;

public class GetKLatestMessagesByDateIndividual 
        extends UseCase<GetKLatestMessagesByDateIndividual.InputValues,GetKLatestMessagesByDateIndividual.OutputValues> {
    private IndividualConversationStorage _individualConversationStorage;

    public GetKLatestMessagesByDateIndividual(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public class InputValues {
        private String _idConversation;
        private int _K;
        private Date _date;

        InputValues(String idConversation, int K, Date date) {
            _idConversation = idConversation;
            _K = K;
            _date = date;
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
        IndividualConversation conversation = _individualConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputValues(Result.Failed, "Wrong id",null);
        } else {
            List<IndividualMessage> messages = conversation.getMessages().stream().filter(m->m.getTimestamp().before(input._date)).toList();
            if(messages.size() == 0 ) {
                return new OutputValues(Result.Failed, "failed", null);
            } else if(messages.size()<= input._K) {
                return new OutputValues(Result.Successed, "Successed", messages);
            }
            return new OutputValues(Result.Successed, "Successed", messages.subList(0, input._K));
        }
    }
}
