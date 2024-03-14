package com.chatApplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;
import java.util.List;

import com.chatApplication.domains.messageimpl.GroupMessage;
import java.util.ArrayList;

public class GetKLatestMessagesBeforeMGroup 
        extends UseCase<GetKLatestMessagesBeforeMGroup.InputValues,GetKLatestMessagesBeforeMGroup.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;

    public GetKLatestMessagesBeforeMGroup(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public class InputValues {
        private String _idConversation;
        private int _K;
        private int _M;

        InputValues(String idConversation, int K, int M) {
            _idConversation = idConversation;
            _K = K;
            _M = M;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<GroupMessage> _messages;

        OutputValues(Result result, String message,List<GroupMessage> messages) {
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

        public List<GroupMessage> getMessages() {
            return _messages;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputValues(Result.Failed, "Wrong id",null);
        } else {
            int messagesSize = conversation.getMessages().size();
            List<GroupMessage> messages = new ArrayList<>();
            for(int i=messagesSize - input._K - input._M ; i < messagesSize-input._M;i++) {
                messages.add(conversation.getMessages().get(i));
            }
            return new OutputValues(Result.Successed, "Successed", messages);
        }
    }
}
