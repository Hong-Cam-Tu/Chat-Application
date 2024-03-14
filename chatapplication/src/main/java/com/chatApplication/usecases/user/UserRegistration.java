package com.chatApplication.usecases.user;

import com.chatApplication.domains.User;
import com.chatApplication.domains.User.UserBuilder;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.DataStorage;
import com.chatApplication.usecases.adapters.Hasher;

public class UserRegistration
        extends UseCase<UserRegistration.InputValues, UserRegistration.OutputValues> {
    private DataStorage _dataStorage;
    private Hasher _hasher;

    public UserRegistration(DataStorage dataStorage, Hasher hasher) {
        _dataStorage = dataStorage;
        _hasher = hasher;
    }

    public static class InputValues {
        private String _username;
        private String _password;

        public InputValues(String username, String password) {
            _username = username;
            _password = password;
        }
    }

    public static class OutputValues {
        private final RegisterResult _result;
        private final String _message;

        public OutputValues(RegisterResult result, String message) {
            _message = message;
            _result = result;
        }

        public RegisterResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum RegisterResult {
        Successed, Failed
    }

   @Override
   public OutputValues excute(InputValues input) {
      User userAvailable = _dataStorage.getUsers().getFirst(u -> u.getUsername().equals(input._username));
      if(userAvailable==null) {
         User user = new UserBuilder(input._username, _hasher.hash(input._password)).build();
        _dataStorage.getUsers().add(user);
        return new OutputValues(RegisterResult.Successed, "");
      }
      return new OutputValues(RegisterResult.Failed, "Fail");
   }

}