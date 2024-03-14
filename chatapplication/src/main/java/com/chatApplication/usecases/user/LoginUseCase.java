package com.chatapplication.usecases.user;

import com.chatapplication.domains.User;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.DataStorage;
import com.chatapplication.usecases.adapters.Hasher;

public class LoginUseCase
        extends UseCase<LoginUseCase.InputLoginUseCase, LoginUseCase.OutputLoginUseCase> {
    private DataStorage _dataStorage;
    private Hasher _hasher;

    public LoginUseCase(DataStorage dataStorage, Hasher hasher) {
        _dataStorage = dataStorage;
        _hasher = hasher;
    }

    public static class InputLoginUseCase {
        private String _username;
        private String _password;

        public InputLoginUseCase(String username, String password) {
            _username = username;
            _password = password;
        }
    }

    public static class OutputLoginUseCase {
        private final LoginResult _result;
        private final String _message;
        private final User _user;

        public OutputLoginUseCase(LoginResult result, String message,User user) {
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
   public OutputLoginUseCase excute(InputLoginUseCase input) {
      User user = _dataStorage.getUsers()
      .getFirst(u->(u.getUsername().equals(input._username) && u.checkPassword(_hasher.hash(input._password))));
      if(user == null) {
         return new OutputLoginUseCase(LoginResult.Failed, "Fail", null);
      }
      return new OutputLoginUseCase(LoginResult.Successed, "", user);
   }
}