package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

public class GetAllGroupConversations 
        extends UseCase<GetAllGroupConversations.InputGetAllGroupConversations,GetAllGroupConversations.OutputGetAllGroupConversations> {
    private GroupConversationStorage _groupConversationStorage;

    public GetAllGroupConversations(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public static class InputGetAllGroupConversations {
        private String _idUser;

        public InputGetAllGroupConversations(String idUser) {
            _idUser = idUser;
        }
    }

    public static enum GetAllGroupConversationsResult {
        Successed,
        Failed;
     }

    public static class OutputGetAllGroupConversations {
        private final GetAllGroupConversationsResult _result;
        private final String _message;
        private final List<GroupConversation> _conversations;

        OutputGetAllGroupConversations(GetAllGroupConversationsResult result, String message,List<GroupConversation> conversations) {
            _result = result;
            _message = message;
            _conversations = conversations;
        }

        public GetAllGroupConversationsResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<GroupConversation> getConversations() {
            return _conversations;
        }
    }

    @Override
    public OutputGetAllGroupConversations excute(InputGetAllGroupConversations input) {
        List<GroupConversation> conversations = _groupConversationStorage.getConversation().getAll(c->{
            List<GroupMessage> messages = c.getMessages();
            for(GroupMessage message : messages) {
                if(message.getIdSender().equals(input._idUser))
                return true;
            }
            return false;
        });
        if(conversations == null) {
            return new OutputGetAllGroupConversations(GetAllGroupConversationsResult.Failed, "failed", null);
        } else {
            return new OutputGetAllGroupConversations(GetAllGroupConversationsResult.Successed, "Successed", conversations);
        }
    }
}
