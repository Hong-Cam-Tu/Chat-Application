package com.chatApplication.usecases.user;

import com.chatApplication.domains.User;
import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;

public class CreatePrivateGroup extends UseCase<CreatePrivateGroup.InputValues,CreatePrivateGroup.OutputValues> {
    private PrivateGroupStorage _privateGroupStorage;

    public CreatePrivateGroup(PrivateGroupStorage privateGroupStorage) {
        _privateGroupStorage=privateGroupStorage;
    }
    public class InputValues {
        private String _nameGroup;
        private User _admin;
        
        InputValues(String nameGroup,User admin) {
            _nameGroup =nameGroup;
            _admin = admin;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;

        OutputValues(Result result, String message) {
            _result = result;
            _message = message;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        PrivateGroup privateGroup = _privateGroupStorage.getPrivateGroup().getFirst(group -> group.getNameGroup().equals(input._nameGroup));
        if(privateGroup==null) {
            PrivateGroup group = new PrivateGroup(input._nameGroup,input._admin);
            _privateGroupStorage.getPrivateGroup().add(group);
            return new OutputValues(Result.Successed, "Successed");
        } 
            return new OutputValues(Result.Failed, "Group has already exist");
    }

}
