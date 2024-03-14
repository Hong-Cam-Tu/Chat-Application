package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class DeleteNLatestMessagesIndividual 
extends UseCase<DeleteNLatestMessagesIndividual.InputDeleteMessage,
DeleteNLatestMessagesIndividual.OutputDeleteMessage> {
    private IndividualConversationStorage _individualConversationStorage;

    public DeleteNLatestMessagesIndividual(IndividualConversationStorage individualConversationStorage) {
        this._individualConversationStorage = individualConversationStorage;
    }

    public class InputDeleteMessage {
        private String _idIndividualConversation;
        private String _idUser;
        private int  _N;

        public InputDeleteMessage(String idIndividualConversation, String idUser, int N) {
            this._idIndividualConversation = idIndividualConversation;
            this._idUser = idUser;
            this._N = N;
        }     
    }

    public enum DeleteMessageResult {
        Successed,
        Failed;
     }

    public class OutputDeleteMessage {
        private final DeleteMessageResult _result;
        private final String _message;

        OutputDeleteMessage(DeleteMessageResult result, String message) {
            _result = result;
            _message = message;
        }

        public DeleteMessageResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputDeleteMessage excute(InputDeleteMessage input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idIndividualConversation);
        if(conversation == null) {
            return new OutputDeleteMessage(DeleteMessageResult.Failed, "Wrong id conversation");
        } else {
            List<IndividualMessage> messages = conversation.getMessages().stream().filter(m -> m.getIdSender().equals(input._idUser)).toList();
            if(messages.size() > input._N ) {
                messages = messages.subList(messages.size()-input._N, messages.size());
            }
            conversation.removeAllMessages(messages);
            return new OutputDeleteMessage(DeleteMessageResult.Successed, "Successed");
        }
    }
}
