package com.chatapplication.usecases.user;

import com.chatApplication.domains.User;
import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class JoinPublicGroupByInvitation extends UseCase<JoinPublicGroupByInvitation.InputValues,JoinPublicGroupByInvitation.OutputValues>{
    private PublicGroupStorage _publicGroupStorage;

    public JoinPublicGroupByInvitation(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage= publicGroupStorage;
    }


    public class InputValues {
        private User _user;
        private String _idGroup;
        private String _idMember;
        
        InputValues(User user, String idGroup,String idMember) {
            _user= user;
            _idGroup = idGroup;
            _idMember = idMember;
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
            return new OutputValues(JoinResult.Failed, "Wrong id public group.");
        }else {
            if( publicGroup.isWithinGroup(input._idMember)) {
                publicGroup.addMember(input._user);
                return new OutputValues(JoinResult.Successed, "Successed");
            } else {
                return new OutputValues(JoinResult.Failed, "Wrong id member");
            }
        }
    }
}
