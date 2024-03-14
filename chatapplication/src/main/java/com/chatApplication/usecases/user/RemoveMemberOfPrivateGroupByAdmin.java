package com.chatApplication.usecases.user;

import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;

public class RemoveMemberOfPrivateGroupByAdmin 
        extends UseCase<RemoveMemberOfPrivateGroupByAdmin.InputValues,RemoveMemberOfPrivateGroupByAdmin.OutputValues> {
    private PrivateGroupStorage _privateGroupStorage;

    public RemoveMemberOfPrivateGroupByAdmin(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage = privateGroupStorage;
    }

    public static class InputValues {
        private String _idGroup;
        private String _idUser;
        private String _idAdmin;

        public InputValues(String idGroup, String idUser,String idAdmin) {
            _idGroup = idGroup;
            _idUser = idUser;
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
            if(group.removeMemberByAdmin(input._idUser, input._idAdmin)){
                return new OutputValues(Result.Successed, "Successed");
            }else {
                return new OutputValues(Result.Failed, "Wrong id member");
            }
            
        }
    }
}
