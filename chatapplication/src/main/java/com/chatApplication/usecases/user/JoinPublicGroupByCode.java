package com.chatapplication.usecases.user;

import com.chatapplication.domains.User;
import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class JoinPublicGroupByCode extends UseCase<JoinPublicGroupByCode.InputJoinPublicGroupByCode,JoinPublicGroupByCode.OutputJoinPublicGroupByCode>{
    private PublicGroupStorage _publicGroupStorage;

    public JoinPublicGroupByCode(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage= publicGroupStorage;
    }

    public static class InputJoinPublicGroupByCode {
        private User _user;
        private String _idGroup;
        private String _joinCode;
        
        public InputJoinPublicGroupByCode(User user, String idGroup,String joinCode) {
            _user= user;
            _idGroup = idGroup;
            this._joinCode = joinCode;
        }
    }

    public static enum JoinPublicGroupByCodeResult {
        Successed,
        Failed;
     }

    public static class OutputJoinPublicGroupByCode {
        private final JoinPublicGroupByCodeResult _result;
        private final String _message;

        public OutputJoinPublicGroupByCode(JoinPublicGroupByCodeResult result, String message) {
            _result = result;
            _message = message;
        }

        public JoinPublicGroupByCodeResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputJoinPublicGroupByCode excute(InputJoinPublicGroupByCode input) {
        PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getFirst(g -> g.getID().equals(input._idGroup));

        if(publicGroup==null) {
            return new OutputJoinPublicGroupByCode(JoinPublicGroupByCodeResult.Failed, "Wrong id public group .");
        }else {
            if( publicGroup.getJoinCode().equals(input._joinCode)) {
                publicGroup.addMember(input._user);
                return new OutputJoinPublicGroupByCode(JoinPublicGroupByCodeResult.Successed, "Successed");
            } else {
                return new OutputJoinPublicGroupByCode(JoinPublicGroupByCodeResult.Failed, "Wrong joincode");
            }
        }
    }
}
