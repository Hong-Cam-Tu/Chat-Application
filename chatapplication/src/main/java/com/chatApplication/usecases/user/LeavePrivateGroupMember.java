package com.chatapplication.usecases.user;

import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;

public class LeavePrivateGroupMember 
        extends UseCase<LeavePrivateGroupMember.InputLeavePrivateGroupMember,
            LeavePrivateGroupMember.OutputLeavePrivateGroupMember> {
    private PrivateGroupStorage _privateGroupStorage;

    public LeavePrivateGroupMember(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage = privateGroupStorage;
    }

    public static class InputLeavePrivateGroupMember {
        private String _idGroup;
        private String _idUser;

        public InputLeavePrivateGroupMember(String idGroup, String idUser) {
            _idGroup = idGroup;
            _idUser = idUser;
        }
    }

    public static class OutputLeavePrivateGroupMember {
        private final LeavePrivateGroupMemberResult _result;
        private final String _message;

        public OutputLeavePrivateGroupMember(LeavePrivateGroupMemberResult result, String string) {
            _message = string;
            _result = result;
        }

        public LeavePrivateGroupMemberResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum LeavePrivateGroupMemberResult {
        Successed, Failed
    }

    @Override
    public OutputLeavePrivateGroupMember excute(InputLeavePrivateGroupMember input) {
        PrivateGroup group = _privateGroupStorage.getPrivateGroup().getFirst(g-> g.getID().equals(input._idGroup));
        if(group == null) {
            return new OutputLeavePrivateGroupMember(LeavePrivateGroupMemberResult.Failed, "");
        } else {
            if(group.leaveGroupMember(input._idUser)) {
                return new OutputLeavePrivateGroupMember(LeavePrivateGroupMemberResult.Successed, "Successed");
            } else {
                return new OutputLeavePrivateGroupMember(LeavePrivateGroupMemberResult.Failed, "Wrong id member");
            }
            
        }
    }
}
