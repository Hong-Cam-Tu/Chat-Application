package com.chatapplication.usecases.user;

import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;

public class LeavePrivateGroupAdmin 
extends UseCase<LeavePrivateGroupAdmin.InputLeavePrivateGroupAdmin,
        LeavePrivateGroupAdmin.OutputLeavePrivateGroupAdmin> {
    private PrivateGroupStorage _privateGroupStorage;

    public LeavePrivateGroupAdmin(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage = privateGroupStorage;
    }

    public static class InputLeavePrivateGroupAdmin {
        private String _idGroup;
        private String _idAdmin;

        public InputLeavePrivateGroupAdmin(String idGroup, String idAdmin) {
            _idGroup = idGroup;
            _idAdmin = idAdmin;
        }
    }

    public static class OutputLeavePrivateGroupAdmin {
        private final LeavePrivateGroupAdminResult _result;
        private final String _message;

        public OutputLeavePrivateGroupAdmin(LeavePrivateGroupAdminResult result, String string) {
            _message = string;
            _result = result;
        }

        public LeavePrivateGroupAdminResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum LeavePrivateGroupAdminResult {
        Successed, Failed
    }

    @Override
    public OutputLeavePrivateGroupAdmin excute(InputLeavePrivateGroupAdmin input) {
        PrivateGroup group = _privateGroupStorage.getPrivateGroup().getFirst(g-> g.getID().equals(input._idGroup));
        if(group == null) {
            return new OutputLeavePrivateGroupAdmin(LeavePrivateGroupAdminResult.Failed, "");
        } else {
            if(group.leaveGroupAdmin(input._idAdmin)) {
                return new OutputLeavePrivateGroupAdmin(LeavePrivateGroupAdminResult.Successed, "Successed");
            } else {
                return new OutputLeavePrivateGroupAdmin(LeavePrivateGroupAdminResult.Failed, "Wrong id admin or group must have at least one Admin");
            }
            
        }
    }
}
