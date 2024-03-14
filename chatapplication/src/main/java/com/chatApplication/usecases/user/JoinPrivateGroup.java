package com.chatApplication.usecases.user;

import com.chatApplication.domains.User;
import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;

public class JoinPrivateGroup extends UseCase<JoinPrivateGroup.InputValues,JoinPrivateGroup.OutputValues>{
    private PrivateGroupStorage _privateGroupStorage;

    public JoinPrivateGroup(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage=  privateGroupStorage;
    }

    public class InputValues {
        private User _user;
        private String _idGroup;
        private String _idAdmin;
        
        InputValues(User user, String idGroup,String idAdmin) {
            _user= user;
            _idGroup = idGroup;
            this._idAdmin = idAdmin;
        }
    }

    public enum JoinResult {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final JoinResult _result;
        private final String _message;

        OutputValues(JoinResult result, String message) {
            _result = result;
            _message = message;
        }

        public JoinResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getFirst(g -> g.getID().equals(input._idGroup));

        if(privateGroup==null) {
            return new OutputValues(JoinResult.Failed, "Wrong id group.");
        }else {
            if(privateGroup.isAdmin(input._idAdmin)) {
                privateGroup.addMember(input._user);
                return new OutputValues(JoinResult.Successed, "Successed");
            } else {
                return new OutputValues(JoinResult.Failed, "Wrong id admin");
            }
        }
    }
}
