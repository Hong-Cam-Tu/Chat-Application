package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

import java.util.Date;

public class GetKLatestMessagesByDateIndividual 
        extends UseCase<GetKLatestMessagesByDateIndividual.InputGetKLatestMessagesByDateIndividual,GetKLatestMessagesByDateIndividual.OutputGetKLatestMessagesByDateIndividual> {
    private IndividualConversationStorage _individualConversationStorage;

    public GetKLatestMessagesByDateIndividual(IndividualConversationStorage individualConversationStorage) {
        _individualConversationStorage = individualConversationStorage;
    }

    public class InputGetKLatestMessagesByDateIndividual {
        private String _idConversation;
        private int _K;
        private Date _date;

        InputGetKLatestMessagesByDateIndividual(String idConversation, int K, Date date) {
            _idConversation = idConversation;
            _K = K;
            _date = date;
        }
    }

    public enum GetKLatestMessagesByDateIndividualResult {
        Successed,
        Failed;
     }

    public class OutputGetKLatestMessagesByDateIndividual {
        private final GetKLatestMessagesByDateIndividualResult _result;
        private final String _message;
        private final List<IndividualMessage> _messages;

        OutputGetKLatestMessagesByDateIndividual(GetKLatestMessagesByDateIndividualResult result, String message,List<IndividualMessage> messages) {
            _result = result;
            _message = message;
            _messages = messages;
        }

        public GetKLatestMessagesByDateIndividualResult getResult(){
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
    public OutputGetKLatestMessagesByDateIndividual excute(InputGetKLatestMessagesByDateIndividual input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputGetKLatestMessagesByDateIndividual(GetKLatestMessagesByDateIndividualResult.Failed, "Wrong id",null);
        } else {
            List<IndividualMessage> messages = conversation.getMessages().stream().filter(m->m.getTimestamp().before(input._date)).toList();
            if(messages.size() == 0 ) {
                return new OutputGetKLatestMessagesByDateIndividual(GetKLatestMessagesByDateIndividualResult.Failed, "failed", null);
            } else if(messages.size()<= input._K) {
                return new OutputGetKLatestMessagesByDateIndividual(GetKLatestMessagesByDateIndividualResult.Successed, "Successed", messages);
            }
            return new OutputGetKLatestMessagesByDateIndividual(GetKLatestMessagesByDateIndividualResult.Successed, "Successed", messages.subList(0, input._K));
        }
    }
}
