package com.chatapplication.usecases.user;

import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class RemoveMemberOfPublicGroupByAdmin 
        extends UseCase<RemoveMemberOfPublicGroupByAdmin.InputRemoveMemberOfPublicGroupByAdmin
        ,RemoveMemberOfPublicGroupByAdmin.OutputRemoveMemberOfPublicGroupByAdmin> {
    private PublicGroupStorage _publicGroupStorage;

    public RemoveMemberOfPublicGroupByAdmin(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage = publicGroupStorage;
    }

    public static class InputRemoveMemberOfPublicGroupByAdmin {
        private String _idGroup;
        private String _idUser;
        private String _idAdmin;

        public InputRemoveMemberOfPublicGroupByAdmin(String idGroup, String idUser,String idAdmin) {
            _idGroup = idGroup;
            _idUser = idUser;
            _idAdmin = idAdmin;
        }
    }

    public static class OutputRemoveMemberOfPublicGroupByAdmin {
        private final RemoveMemberOfPublicGroupByAdminResult _result;
        private final String _message;

        public OutputRemoveMemberOfPublicGroupByAdmin(RemoveMemberOfPublicGroupByAdminResult result, String string) {
            _message = string;
            _result = result;
        }

        public RemoveMemberOfPublicGroupByAdminResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum RemoveMemberOfPublicGroupByAdminResult {
        Successed, Failed
    }

    @Override
    public OutputRemoveMemberOfPublicGroupByAdmin excute(InputRemoveMemberOfPublicGroupByAdmin input) {
        PublicGroup group = _publicGroupStorage.getPublicGroup().getFirst(g-> g.getID().equals(input._idGroup));
        if(group == null) {
            return new OutputRemoveMemberOfPublicGroupByAdmin(RemoveMemberOfPublicGroupByAdminResult.Failed, "");
        } else {
            if(group.removeMemberByAdmin(input._idUser, input._idAdmin)){
                return new OutputRemoveMemberOfPublicGroupByAdmin(RemoveMemberOfPublicGroupByAdminResult.Successed, "Successed");
            }else {
                return new OutputRemoveMemberOfPublicGroupByAdmin(RemoveMemberOfPublicGroupByAdminResult.Failed, "Wrong id member");
            }
            
        }
    }
}
