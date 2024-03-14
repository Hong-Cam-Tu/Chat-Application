package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

public class GetLatestReadMessageGroup 
        extends UseCase<GetLatestReadMessageGroup.InputGetLatestReadMessageGroup,GetLatestReadMessageGroup.OutputGetLatestReadMessageGroup> {
    private GroupConversationStorage _groupConversationStorage;

    public GetLatestReadMessageGroup(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public static class InputGetLatestReadMessageGroup {
        private String _idGroupConversation;
        private String _idUser;
        
        public InputGetLatestReadMessageGroup(String idGroupConverSation,String idUser) {
            _idGroupConversation =idGroupConverSation;
            _idUser = idUser;
        }
    }

    public static enum GetLatestReadMessageGroupResult {
        Successed,
        Failed;
     }

    public static class OutputGetLatestReadMessageGroup {
        private final GetLatestReadMessageGroupResult _result;
        private final String _message;
        private final GroupMessage _groupMessage;

        public OutputGetLatestReadMessageGroup(GetLatestReadMessageGroupResult result, String message,GroupMessage groupMessage) {
            _result = result;
            _message = message;
            _groupMessage = groupMessage;
        }

        public GetLatestReadMessageGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public GroupMessage getIndividualMessage() {
            return _groupMessage;
        }
    }

    @Override
    public OutputGetLatestReadMessageGroup excute(InputGetLatestReadMessageGroup input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idGroupConversation);
        if(conversation==null) {
            return new OutputGetLatestReadMessageGroup(GetLatestReadMessageGroupResult.Failed, "Wrong id conversation",null);
        } else {
            List<GroupMessage> messages= conversation.getMessages().stream().filter(m->m.isReadMessage(input._idUser)).toList();
            if(messages.size()==0) {
                return new OutputGetLatestReadMessageGroup( GetLatestReadMessageGroupResult.Failed, null, null);
            } else {
                return new OutputGetLatestReadMessageGroup(GetLatestReadMessageGroupResult.Successed, "Successed", messages.get(messages.size()-1));
            }
        }
    }
}
