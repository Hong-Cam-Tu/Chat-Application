package com.chatapplication.usecases.user;

import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;

public class LeavePrivateGroupAdmin extends UseCase<LeavePrivateGroupAdmin.InputValues,LeavePrivateGroupAdmin.OutputValues> {
    private PrivateGroupStorage _privateGroupStorage;

    public LeavePrivateGroupAdmin(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage = privateGroupStorage;
    }

    public static class InputValues {
        private String _idGroup;
        private String _idAdmin;

        public InputValues(String idGroup, String idAdmin) {
            _idGroup = idGroup;
            _idAdmin = idAdmin;
        }
    }

    public static class OutputValues {
        private final Result _result;
        private final String _message;

        public OutputValues(Result result, String string) {
            _message = string;
            _result = result;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum Result {
        Successed, Failed
    }

    @Override
    public OutputValues excute(InputValues input) {
        PrivateGroup group = _privateGroupStorage.getPrivateGroup().getFirst(g-> g.getID().equals(input._idGroup));
        if(group == null) {
            return new OutputValues(Result.Failed, "");
        } else {
            if(group.leaveGroupAdmin(input._idAdmin)) {
                return new OutputValues(Result.Successed, "Successed");
            } else {
                return new OutputValues(Result.Failed, "Wrong id admin or group must have at least one Admin");
            }
            
        }
    }
}
