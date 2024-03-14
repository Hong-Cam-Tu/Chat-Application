package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

import java.util.ArrayList;

public class GetKLatestMessagesBeforeMGroup 
        extends UseCase<GetKLatestMessagesBeforeMGroup.InputGetKLatestMessagesBeforeMGroup,
        GetKLatestMessagesBeforeMGroup.OutputGetKLatestMessagesBeforeMGroup> {
    private GroupConversationStorage _groupConversationStorage;

    public GetKLatestMessagesBeforeMGroup(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public static class InputGetKLatestMessagesBeforeMGroup {
        private String _idConversation;
        private int _K;
        private int _M;

        public InputGetKLatestMessagesBeforeMGroup(String idConversation, int K, int M) {
            _idConversation = idConversation;
            _K = K;
            _M = M;
        }
    }

    public static enum GetKLatestMessagesBeforeMGroupResult {
        Successed,
        Failed;
     }

    public static class OutputGetKLatestMessagesBeforeMGroup {
        private final GetKLatestMessagesBeforeMGroupResult _result;
        private final String _message;
        private final List<GroupMessage> _messages;

        public OutputGetKLatestMessagesBeforeMGroup(GetKLatestMessagesBeforeMGroupResult result, String message,List<GroupMessage> messages) {
            _result = result;
            _message = message;
            _messages = messages;
        }

        public GetKLatestMessagesBeforeMGroupResult getResult(){
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
    public OutputGetKLatestMessagesBeforeMGroup excute(InputGetKLatestMessagesBeforeMGroup input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputGetKLatestMessagesBeforeMGroup(GetKLatestMessagesBeforeMGroupResult.Failed, "Wrong id",null);
        } else {
            int messagesSize = conversation.getMessages().size();
            List<GroupMessage> messages = new ArrayList<>();
            for(int i=messagesSize - input._K - input._M ; i < messagesSize-input._M;i++) {
                messages.add(conversation.getMessages().get(i));
            }
            return new OutputGetKLatestMessagesBeforeMGroup(GetKLatestMessagesBeforeMGroupResult.Successed, "Successed", messages);
        }
    }
}
