// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.usecases.user;

import chatApplication.domains.User;
import chatApplication.domains.User.UserBuilder;
import chatApplication.usecases.UseCase;
import chatApplication.usecases.adapters.DataStorage;
import chatApplication.usecases.adapters.Hasher;

public class UserRegistration extends UseCase<UserRegistration.InputValues, UserRegistration.OutputValues> {
   private DataStorage _dataStorage;
   private Hasher _hasher;

   public UserRegistration(DataStorage dataStorage, Hasher hasher) {
      this._dataStorage = dataStorage;
      this._hasher = hasher;
   }

   

   public OutputValues excute(InputValues input) {
      User user = new UserBuilder(input._username, _hasher.hash(input._password)).build();
        _dataStorage.getUsers().add(user);
        return new OutputValues(RegisterResult.Successed, "");
   }

   public class InputValues {
    private String _username;
    private String _password;
 
        public InputValues(String username, String password) {
        this._username = username;
        this._password = password;
        }
    }

    public class OutputValues {
        private final RegisterResult _result;
        private final String _message;
     
        public OutputValues(RegisterResult result, String message) {
           this._message = message;
           this._result = result;
        }
     
        public RegisterResult getResult() {
           return this._result;
        }
     
        public String getMessage() {
           return this._message;
        }
     }

     public enum RegisterResult {
        Successed,
        Failed;
     }   
}
