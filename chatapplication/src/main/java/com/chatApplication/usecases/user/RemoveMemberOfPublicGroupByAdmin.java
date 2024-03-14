package com.chatapplication.usecases.user;

import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class RemoveMemberOfPublicGroupByAdmin 
        extends UseCase<RemoveMemberOfPublicGroupByAdmin.InputValues,RemoveMemberOfPublicGroupByAdmin.OutputValues> {
    private PublicGroupStorage _publicGroupStorage;

    public RemoveMemberOfPublicGroupByAdmin(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage = publicGroupStorage;
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
        PublicGroup group = _publicGroupStorage.getPublicGroup().getFirst(g-> g.getID().equals(input._idGroup));
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
