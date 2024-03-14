package com.chatApplication.usecases.user;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.GroupConversationStorage;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class GetIdAndNameGroupConversation 
        extends UseCase<GetIdAndNameGroupConversation.InputValues,GetIdAndNameGroupConversation.OutputValues> {
    private GroupConversationStorage _groupConversationStorage;
    private PublicGroupStorage _publicGroupStorage;
    private PrivateGroupStorage _privateGroupStorage;

    public GetIdAndNameGroupConversation(GroupConversationStorage groupConversationStorage,
     PublicGroupStorage publicGroupStorage, PrivateGroupStorage privateGroupStorage) {
        _groupConversationStorage = groupConversationStorage;
        _publicGroupStorage = publicGroupStorage;
        _privateGroupStorage = privateGroupStorage;
    }

    public class InputValues {
        private String _idConversation;

        InputValues(String idConversation) {
            _idConversation = idConversation;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final String _idReceiver;
        private final String _nameReceiver;

        OutputValues(Result result, String message,String idReceiver,String nameReceiver) {
            _result = result;
            _message = message;
            _idReceiver = idReceiver;
            _nameReceiver = nameReceiver;
        }
        
        public String get_idReceiver() {
            return _idReceiver;
        }

        public String get_nameReceiver() {
            return _nameReceiver;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idConversation);
        if(conversation == null) {
            return new OutputValues(Result.Failed, "Wrong id conversation", null, null);
        } else {
            String idGroup =conversation.getMessages().get(0).getIdGroup();
            PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getById(idGroup);
            PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getById(idGroup);
            if(publicGroup != null) {
                return new OutputValues(Result.Successed, "Successed", idGroup, publicGroup.getNameGroup());
            }
            return new OutputValues(Result.Successed, "Successed", idGroup, privateGroup.getNameGroup());
        }
    }
}
