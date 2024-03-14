package com.chatapplication.usecases.user;

import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class LeavePublicGroup extends UseCase<LeavePublicGroup.InputLeavePublicGroup,LeavePublicGroup.OutputLeavePublicGroup> {
    private PublicGroupStorage _publicGroupStorage;

    public LeavePublicGroup(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage = publicGroupStorage;
    }

    public static class InputLeavePublicGroup {
        private String _idGroup;
        private String _idUser;

        public InputLeavePublicGroup(String idGroup, String idUser) {
            _idGroup = idGroup;
            _idUser = idUser;
        }
    }

    public static class OutputLeavePublicGroup {
        private final LeavePublicGroupResult _result;
        private final String _message;

        public OutputLeavePublicGroup(LeavePublicGroupResult result, String string) {
            _message = string;
            _result = result;
        }

        public LeavePublicGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum LeavePublicGroupResult {
        Successed, Failed
    }

    @Override
    public OutputLeavePublicGroup excute(InputLeavePublicGroup input) {
        PublicGroup group = _publicGroupStorage.getPublicGroup().getFirst(g-> g.getID().equals(input._idGroup));
        if(group == null) {
            return new OutputLeavePublicGroup(LeavePublicGroupResult.Failed, "");
        } else {
            if(group.leaveGroupMember(input._idUser)){
                return new OutputLeavePublicGroup(LeavePublicGroupResult.Successed, "Successed");
            }else {
                return new OutputLeavePublicGroup(LeavePublicGroupResult.Failed, "Wrong id member");
            }
            
        }
    }
}
