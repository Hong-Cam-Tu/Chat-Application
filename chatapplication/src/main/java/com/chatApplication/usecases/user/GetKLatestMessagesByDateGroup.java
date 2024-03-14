package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.messageimpl.GroupMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;

import java.util.Date;

public class GetKLatestMessagesByDateGroup 
        extends UseCase<GetKLatestMessagesByDateGroup.InputGetKLatestMessagesByDateGroup,GetKLatestMessagesByDateGroup.OutputGetKLatestMessagesByDateGroup> {
    private GroupConversationStorage _groupConversationStorage;

    public GetKLatestMessagesByDateGroup(GroupConversationStorage groupConversationStorage) {
        _groupConversationStorage = groupConversationStorage;
    }

    public class InputGetKLatestMessagesByDateGroup {
        private String _idConversation;
        private int _K;
        private Date _date;

        InputGetKLatestMessagesByDateGroup(String idConversation, int K, Date date) {
            _idConversation = idConversation;
            _K = K;
            _date = date;
        }
    }

    public enum GetKLatestMessagesByDateGroupResult {
        Successed,
        Failed;
     }

    public class OutputGetKLatestMessagesByDateGroup {
        private final GetKLatestMessagesByDateGroupResult _result;
        private final String _message;
        private final List<GroupMessage> _messages;

        OutputGetKLatestMessagesByDateGroup(GetKLatestMessagesByDateGroupResult result, String message,List<GroupMessage> messages) {
            _result = result;
            _message = message;
            _messages = messages;
        }

        public GetKLatestMessagesByDateGroupResult getResult(){
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
    public OutputGetKLatestMessagesByDateGroup excute(InputGetKLatestMessagesByDateGroup input) {
        GroupConversation conversation =
         _groupConversationStorage.getConversation().getFirst(c->c.getID().equals(input._idConversation));
        if(conversation == null) {
            return new OutputGetKLatestMessagesByDateGroup(GetKLatestMessagesByDateGroupResult.Failed, "Wrong id",null);
        } else {
             List<GroupMessage> messages = conversation.getMessages().stream().filter(m->m.getTimestamp().before(input._date)).toList();
                if(messages.size() == 0 ) {
                    return new OutputGetKLatestMessagesByDateGroup(GetKLatestMessagesByDateGroupResult.Failed, "failed", null);
                } else if(messages.size()<= input._K) {
                    return new OutputGetKLatestMessagesByDateGroup(GetKLatestMessagesByDateGroupResult.Successed, "Successed", messages);
                }
                return new OutputGetKLatestMessagesByDateGroup(GetKLatestMessagesByDateGroupResult.Successed, "Successed", messages.subList(0, input._K));
        }
    }
}
