package com.chatapplication.usecases.user;

import com.chatapplication.domains.User;
import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class CreatePublicGroup 
    extends UseCase<CreatePublicGroup.InputCreatePublicGroup,
            CreatePublicGroup.OutputCreatePublicGroup> {
    private PublicGroupStorage _publicGroupStorage;

    public CreatePublicGroup(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage=publicGroupStorage;
    }

    public static class InputCreatePublicGroup {
        private String _nameGroup;
        private User _userCreator;
        
        public InputCreatePublicGroup(String nameGroup,User userCreator) {
            _nameGroup =nameGroup;
            _userCreator = userCreator;
        }
    }

    public static enum CreatePublicGroupResult {
        Successed,
        Failed;
     }

    public static class OutputCreatePublicGroup {
        private final CreatePublicGroupResult _result;
        private final String _message;
        private final String _idGroup;
        private final String _joinCode;

        public OutputCreatePublicGroup(CreatePublicGroupResult result, String message,String idGroup,String joinCode) {
            _result = result;
            _message = message;
            _idGroup = idGroup;
            _joinCode = joinCode;
        }

        public CreatePublicGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public String getIdGroup() {
            return _idGroup;
        }

        public String getJoinCode() {
            return _joinCode;
        }
    }

    @Override
    public OutputCreatePublicGroup excute(InputCreatePublicGroup input) {
        PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getFirst(group -> group.getNameGroup().equals(input._nameGroup));
        if(publicGroup==null) {
            PublicGroup group = new PublicGroup(input._nameGroup,input._userCreator);
            _publicGroupStorage.getPublicGroup().add(group);
            return new OutputCreatePublicGroup(CreatePublicGroupResult.Successed, "Successed",group.getID(),group.getJoinCode());
        } 
            return new OutputCreatePublicGroup(CreatePublicGroupResult.Failed, "Group has already exist",null,null);
        
    }

}
