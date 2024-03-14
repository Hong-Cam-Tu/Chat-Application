package com.chatapplication.usecases.user;

import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;

public class RemoveMemberOfPrivateGroupByAdmin 
        extends UseCase<RemoveMemberOfPrivateGroupByAdmin.InputRemoveMemberOfPrivateGroupByAdmin,
        RemoveMemberOfPrivateGroupByAdmin.OutputRemoveMemberOfPrivateGroupByAdmin> {
    private PrivateGroupStorage _privateGroupStorage;

    public RemoveMemberOfPrivateGroupByAdmin(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage = privateGroupStorage;
    }

    public static class InputRemoveMemberOfPrivateGroupByAdmin {
        private String _idGroup;
        private String _idUser;
        private String _idAdmin;

        public InputRemoveMemberOfPrivateGroupByAdmin(String idGroup, String idUser,String idAdmin) {
            _idGroup = idGroup;
            _idUser = idUser;
            _idAdmin = idAdmin;
        }
    }

    public static class OutputRemoveMemberOfPrivateGroupByAdmin {
        private final RemoveMemberOfPrivateGroupByAdminResult _result;
        private final String _message;

        public OutputRemoveMemberOfPrivateGroupByAdmin(RemoveMemberOfPrivateGroupByAdminResult result, String string) {
            _message = string;
            _result = result;
        }

        public RemoveMemberOfPrivateGroupByAdminResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum RemoveMemberOfPrivateGroupByAdminResult {
        Successed, Failed
    }

    @Override
    public OutputRemoveMemberOfPrivateGroupByAdmin excute(InputRemoveMemberOfPrivateGroupByAdmin input) {
        PrivateGroup group = _privateGroupStorage.getPrivateGroup().getFirst(g-> g.getID().equals(input._idGroup));
        if(group == null) {
            return new OutputRemoveMemberOfPrivateGroupByAdmin(RemoveMemberOfPrivateGroupByAdminResult.Failed, "");
        } else {
            if(group.removeMemberByAdmin(input._idUser, input._idAdmin)){
                return new OutputRemoveMemberOfPrivateGroupByAdmin(RemoveMemberOfPrivateGroupByAdminResult.Successed, "Successed");
            }else {
                return new OutputRemoveMemberOfPrivateGroupByAdmin(RemoveMemberOfPrivateGroupByAdminResult.Failed, "Wrong id member");
            }
            
        }
    }
}
