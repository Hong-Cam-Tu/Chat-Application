package com.chatapplication.usecases.user;

import com.chatapplication.domains.User;
import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;

public class CreatePrivateGroup 
    extends UseCase<CreatePrivateGroup.InputCreatePrivateGroup,
            CreatePrivateGroup.OutputCreatePrivateGroup> {
    private PrivateGroupStorage _privateGroupStorage;

    public CreatePrivateGroup(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage=privateGroupStorage;
    }

    public static class InputCreatePrivateGroup {
        private String _nameGroup;
        private User _admin;
        
        public InputCreatePrivateGroup(String nameGroup,User admin) {
            _nameGroup =nameGroup;
            _admin = admin;
        }
    }

    public static enum CreatePrivateGroupResult {
        Successed,
        Failed;
     }

    public static class OutputCreatePrivateGroup {
        private final CreatePrivateGroupResult _result;
        private final String _message;
        private final String _idGroup;

        public OutputCreatePrivateGroup(CreatePrivateGroupResult result, String message,String idGroup) {
            _result = result;
            _message = message;
            _idGroup = idGroup;
        }

        public CreatePrivateGroupResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public String getIdGroup() {
            return _idGroup;
        }
    }

    @Override
    public OutputCreatePrivateGroup excute(InputCreatePrivateGroup input) {
        PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getFirst(group -> group.getNameGroup().equals(input._nameGroup));
        if(privateGroup==null) {
            PrivateGroup group = new PrivateGroup(input._nameGroup,input._admin);
            _privateGroupStorage.getPrivateGroup().add(group);
            return new OutputCreatePrivateGroup(CreatePrivateGroupResult.Successed, "Successed",group.getID());
        } 
            return new OutputCreatePrivateGroup(CreatePrivateGroupResult.Failed, "Group has already exist",null);
    }

}
