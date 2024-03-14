package com.chatapplication.usecases.user;

import com.chatapplication.domains.User;
import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class JoinPublicGroupByInvitation extends UseCase<JoinPublicGroupByInvitation.InputJoinPublicGroupByInvitation,JoinPublicGroupByInvitation.OutputJoinPublicGroupByInvitation>{
    private PublicGroupStorage _publicGroupStorage;

    public JoinPublicGroupByInvitation(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage= publicGroupStorage;
    }


    public static class InputJoinPublicGroupByInvitation {
        private User _user;
        private String _idGroup;
        private String _idMember;
        
        public InputJoinPublicGroupByInvitation(User user, String idGroup,String idMember) {
            _user= user;
            _idGroup = idGroup;
            _idMember = idMember;
        }
    }

    public static enum JoinPublicGroupByInvitationResult {
        Successed,
        Failed;
     }

    public static class OutputJoinPublicGroupByInvitation {
        private final JoinPublicGroupByInvitationResult _result;
        private final String _message;

        public OutputJoinPublicGroupByInvitation(JoinPublicGroupByInvitationResult result, String message) {
            _result = result;
            _message = message;
        }

        public JoinPublicGroupByInvitationResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputJoinPublicGroupByInvitation excute(InputJoinPublicGroupByInvitation input) {
        PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getFirst(g -> g.getID().equals(input._idGroup));

        if(publicGroup==null) {
            return new OutputJoinPublicGroupByInvitation(JoinPublicGroupByInvitationResult.Failed, "Wrong id public group.");
        }else {
            if( publicGroup.isWithinGroup(input._idMember)) {
                publicGroup.addMember(input._user);
                return new OutputJoinPublicGroupByInvitation(JoinPublicGroupByInvitationResult.Successed, "Successed");
            } else {
                return new OutputJoinPublicGroupByInvitation(JoinPublicGroupByInvitationResult.Failed, "Wrong id member");
            }
        }
    }
}
