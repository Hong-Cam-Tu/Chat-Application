package com.chatApplication.usecases.user;

import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;

public class LeavePrivateGroupMember extends UseCase<LeavePrivateGroupMember.InputValues,LeavePrivateGroupMember.OutputValues> {
    private PrivateGroupStorage _privateGroupStorage;

    public LeavePrivateGroupMember(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage = privateGroupStorage;
    }

    public static class InputValues {
        private String _idGroup;
        private String _idUser;

        public InputValues(String idGroup, String idUser) {
            _idGroup = idGroup;
            _idUser = idUser;
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
            if(group.leaveGroupMember(input._idUser)) {
                return new OutputValues(Result.Successed, "Successed");
            } else {
                return new OutputValues(Result.Failed, "Wrong id member");
            }
            
        }
    }
}
