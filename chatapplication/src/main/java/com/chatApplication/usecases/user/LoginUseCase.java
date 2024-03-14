package com.chatapplication.usecases.user;

import com.chatApplication.domains.User;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.DataStorage;
import com.chatApplication.usecases.adapters.Hasher;

public class LoginUseCase
        extends UseCase<LoginUseCase.InputValues, LoginUseCase.OutputValues> {
    private DataStorage _dataStorage;
    private Hasher _hasher;

    public LoginUseCase(DataStorage dataStorage, Hasher hasher) {
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
        private final LoginResult _result;
        private final String _message;
        private final User _user;

        public OutputValues(LoginResult result, String message,User user) {
            _message = message;
            _result = result;
            _user =user;
        }

        public LoginResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public User getUser() {
            return _user;
        }
    }

    public static enum LoginResult {
        Successed, Failed
    }

   @Override
   public OutputValues excute(InputValues input) {
      User user = _dataStorage.getUsers()
      .getFirst(u->(u.getUsername().equals(input._username) && u.checkPassword(_hasher.hash(input._password))));
      if(user == null) {
         return new OutputValues(LoginResult.Failed, "Fail", null);
      }
      return new OutputValues(LoginResult.Successed, "", user);
   }
}