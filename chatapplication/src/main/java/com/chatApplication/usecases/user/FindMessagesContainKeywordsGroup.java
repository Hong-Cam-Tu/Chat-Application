package com.chatapplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;
import java.util.List;
import com.chatApplication.domains.messageimpl.GroupMessage;

public class FindMessagesContainKeywordsGroup 
        extends UseCase<FindMessagesContainKeywordsGroup.InputValues,FindMessagesContainKeywordsGroup.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;

    public FindMessagesContainKeywordsGroup(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
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
        GroupConversation conversation = 
        _groupConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputValues(Result.Failed, "Wrong id",null);
        } else {
            List<GroupMessage> messages = 
            conversation.getMessages().stream().filter(m->m.getLastestContent().contains(input._keyWords)).toList();
            if(messages.size() == 0) {
                return new OutputValues(Result.Failed, "None fit keywords", messages);
            }
            return new OutputValues(Result.Successed, "Successed", messages);
        }
    }
}
