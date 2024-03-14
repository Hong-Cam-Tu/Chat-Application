package com.chatapplication.usecases.user;

import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class CreatePublicGroup extends UseCase<CreatePublicGroup.InputValues,CreatePublicGroup.OutputValues> {
    private PublicGroupStorage _publicGroupStorage;

    public CreatePublicGroup(PublicGroupStorage publicGroupStorage) {
        _publicGroupStorage=publicGroupStorage;
    }
    public class InputValues {
        private String _nameGroup;
        
        InputValues(String nameGroup) {
            _nameGroup =nameGroup;
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
        PublicGroup publicGroup = _publicGroupStorage.getPublicGroup().getFirst(group -> group.getNameGroup().equals(input._nameGroup));
        if(publicGroup==null) {
            PublicGroup group = new PublicGroup(input._nameGroup);
            _publicGroupStorage.getPublicGroup().add(group);
            return new OutputValues(Result.Successed, "Successed");
        } 
            return new OutputValues(Result.Failed, "Group has already exist");
        
    }

}
