package com.chatapplication.usecases.user;

import com.chatapplication.domains.User;
import com.chatapplication.domains.User.UserBuilder;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.DataStorage;
import com.chatapplication.usecases.adapters.Hasher;

public class UserRegistration
        extends UseCase<UserRegistration.InputRegistration, UserRegistration.OutputRegistration> {
    private DataStorage _dataStorage;
    private Hasher _hasher;

    public UserRegistration(DataStorage dataStorage, Hasher hasher) {
        _dataStorage = dataStorage;
        _hasher = hasher;
    }

    public static class InputRegistration {
        private String _username;
        private String _password;

        public InputRegistration(String username, String password) {
            _username = username;
            _password = password;
        }
    }

    public static class OutputRegistration {
        private final RegistrationResult _result;
        private final String _message;

        public OutputRegistration(RegistrationResult result, String message) {
            _message = message;
            _result = result;
        }

        public RegistrationResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    public static enum RegistrationResult {
        Successed, Failed
    }

   @Override
   public OutputRegistration excute(InputRegistration input) {
      User userAvailable = _dataStorage.getUsers().getFirst(u -> u.getUsername().equals(input._username));
      if(userAvailable==null) {
         User user = new UserBuilder(input._username, _hasher.hash(input._password)).build();
        _dataStorage.getUsers().add(user);
        return new OutputRegistration(RegistrationResult.Successed, "");
      }
      return new OutputRegistration(RegistrationResult.Failed, "Fail");
   }

}