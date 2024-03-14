package com.chatapplication.usecases.user;

import com.chatApplication.domains.User;
import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class JoinPublicGroupByCode extends UseCase<JoinPublicGroupByCode.InputValues,JoinPublicGroupByCode.OutputValues>{
    private PublicGroupStorage _publicGroupStorage;

    public JoinPublicGroupByCode(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage= publicGroupStorage;
    }


    public class InputValues {
        private User _user;
        private String _idGroup;
        private String _joinCode;
        
        InputValues(User user, String idGroup,String joinCode) {
            _user= user;
            _idGroup = idGroup;
            this._joinCode = joinCode;
        }
    }

    public enum JoinResult {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final JoinResult _result;
        private final String _message;

        OutputValues(JoinResult result, String message) {
            _result = result;
            _message = message;
        }

        public JoinResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getFirst(g -> g.getID().equals(input._idGroup));

        if(publicGroup==null) {
            return new OutputValues(JoinResult.Failed, "Wrong id public group .");
        }else {
            if( publicGroup.getJoinCode().equals(input._joinCode)) {
                publicGroup.addMember(input._user);
                return new OutputValues(JoinResult.Successed, "Successed");
            } else {
                return new OutputValues(JoinResult.Failed, "Wrong joincode");
            }
        }
    }
}
