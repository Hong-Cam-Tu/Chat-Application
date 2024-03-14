package com.chatApplication.usecases.user;

import java.util.List;

import com.chatApplication.domains.User;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.DataStorage;

public class FindUsersByGivenName extends UseCase<FindUsersByGivenName.InputValues,FindUsersByGivenName.OutputValues> {
    private DataStorage _dataStorage;

    public FindUsersByGivenName(DataStorage dataStorage) {
        _dataStorage = dataStorage;
    }

    public static class InputValues {
        private String _name;

        public InputValues(String name) {
            _name = name;
        }
    }

    public static class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<User> _users;

        public OutputValues(Result result, String message,List<User> users) {
            _message = message;
            _result = result;
            _users = users;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<User> getUsers() {
            return _users;
        }
    }

    public static enum Result {
        Successed, Failed
    }

   @Override
   public OutputValues excute(InputValues input) {
        List<User> users = _dataStorage.getUsers().getAll(user -> user.getFullName().contains(input._name));
        if(users.size()==0) {
            return new OutputValues(Result.Failed, "There are no such User exist",users);
        } else {
            return new OutputValues(Result.Successed, "Successed",users);
        }
   }
}
