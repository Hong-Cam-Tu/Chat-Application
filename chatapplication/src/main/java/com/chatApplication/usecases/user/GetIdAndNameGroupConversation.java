package com.chatapplication.usecases.user;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.GroupConversationStorage;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class GetIdAndNameGroupConversation 
        extends UseCase<GetIdAndNameGroupConversation.InputGetIdAndNameGroupConversation,
        GetIdAndNameGroupConversation.OutputGetIdAndNameGroupConversation> {
    private GroupConversationStorage _groupConversationStorage;
    private PublicGroupStorage _publicGroupStorage;
    private PrivateGroupStorage _privateGroupStorage;

    public GetIdAndNameGroupConversation(GroupConversationStorage groupConversationStorage,
     PublicGroupStorage publicGroupStorage, PrivateGroupStorage privateGroupStorage) {
        _groupConversationStorage = groupConversationStorage;
        _publicGroupStorage = publicGroupStorage;
        _privateGroupStorage = privateGroupStorage;
    }

    public class InputGetIdAndNameGroupConversation {
        private String _idConversation;

        InputGetIdAndNameGroupConversation(String idConversation) {
            _idConversation = idConversation;
        }
    }

    public enum GetIdAndNameGroupConversationResult {
        Successed,
        Failed;
     }

    public class OutputGetIdAndNameGroupConversation {
        private final GetIdAndNameGroupConversationResult _result;
        private final String _message;
        private final String _idReceiver;
        private final String _nameReceiver;

        OutputGetIdAndNameGroupConversation(GetIdAndNameGroupConversationResult result, String message,String idReceiver,String nameReceiver) {
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

        public GetIdAndNameGroupConversationResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputGetIdAndNameGroupConversation excute(InputGetIdAndNameGroupConversation input) {
        GroupConversation conversation = _groupConversationStorage.getConversation().getById(input._idConversation);
        if(conversation == null) {
            return new OutputGetIdAndNameGroupConversation(GetIdAndNameGroupConversationResult.Failed, "Wrong id conversation", null, null);
        } else {
            String idGroup =conversation.getMessages().get(0).getIdGroup();
            PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getById(idGroup);
            PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getById(idGroup);
            if(publicGroup != null) {
                return new OutputGetIdAndNameGroupConversation(GetIdAndNameGroupConversationResult.Successed, "Successed", idGroup, publicGroup.getNameGroup());
            }
            return new OutputGetIdAndNameGroupConversation(GetIdAndNameGroupConversationResult.Successed, "Successed", idGroup, privateGroup.getNameGroup());
        }
    }
}
