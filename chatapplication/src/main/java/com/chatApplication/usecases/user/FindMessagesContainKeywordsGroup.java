package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

public class FindMessagesContainKeywordsGroup 
        extends UseCase<FindMessagesContainKeywordsGroup.InputFindMessageGroup,FindMessagesContainKeywordsGroup.OutputFindMessageGroup> {
    private GroupConversationStorage _groupConversationStorage;

    public FindMessagesContainKeywordsGroup(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public static class InputFindMessageGroup {
        private String _idConversation;
        private String _keyWords;

        public InputFindMessageGroup(String idConversation, String keywords) {
            _idConversation = idConversation;
            _keyWords = keywords;
        }
    }

    public static enum FindMessageGroupResult {
        Successed,
        Failed;
     }

    public static class OutputFindMessageGroup {
        private final FindMessageGroupResult _result;
        private final String _message;
        private final List<GroupMessage> _messages;

        public OutputFindMessageGroup(FindMessageGroupResult result, String message,List<GroupMessage> messages) {
            _result = result;
            _message = message;
            _messages = messages;
        }

        public FindMessageGroupResult getResult(){
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
    public OutputFindMessageGroup excute(InputFindMessageGroup input) {
        GroupConversation conversation = 
        _groupConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputFindMessageGroup(FindMessageGroupResult.Failed, "Wrong id",null);
        } else {
            List<GroupMessage> messages = 
            conversation.getMessages().stream().filter(m->m.getLastestContent().contains(input._keyWords)).toList();
            if(messages.size() == 0) {
                return new OutputFindMessageGroup(FindMessageGroupResult.Failed, "None fit keywords", messages);
            }
            return new OutputFindMessageGroup(FindMessageGroupResult.Successed, "Successed", messages);
        }
    }
}
