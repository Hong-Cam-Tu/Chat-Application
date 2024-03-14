package com.chatapplication.usecases.user;

import com.chatapplication.domains.User;
import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;

public class JoinPrivateGroup extends UseCase<JoinPrivateGroup.InputJoinPrivateGroup,JoinPrivateGroup.OutputJoinPrivateGroup>{
    private PrivateGroupStorage _privateGroupStorage;

    public JoinPrivateGroup(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage=  privateGroupStorage;
    }

    public static class InputJoinPrivateGroup {
        private User _user;
        private String _idGroup;
        private String _idAdmin;
        
        public InputJoinPrivateGroup(User user, String idGroup,String idAdmin) {
            _user= user;
            _idGroup = idGroup;
            this._idAdmin = idAdmin;
        }
    }

    public static enum JoinPrivateGroupResult {
        Successed,
        Failed;
     }

    public static class OutputJoinPrivateGroup {
        private final JoinPrivateGroupResult _result;
        private final String _message;

        public OutputJoinPrivateGroup(JoinPrivateGroupResult result, String message) {
            _result = result;
            _message = message;
        }

        public JoinPrivateGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputJoinPrivateGroup excute(InputJoinPrivateGroup input) {
        PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getFirst(g -> g.getID().equals(input._idGroup));

        if(privateGroup==null) {
            return new OutputJoinPrivateGroup(JoinPrivateGroupResult.Failed, "Wrong id group.");
        }else {
            if(privateGroup.isAdmin(input._idAdmin)) {
                privateGroup.addMember(input._user);
                return new OutputJoinPrivateGroup(JoinPrivateGroupResult.Successed, "Successed");
            } else {
                return new OutputJoinPrivateGroup(JoinPrivateGroupResult.Failed, "Wrong id admin");
            }
        }
    }
}
