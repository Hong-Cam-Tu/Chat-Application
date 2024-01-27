// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.usecases.user;

import chatapplication.usecases.UseCase;
import chatapplication.usecases.adapters.DataStorage;
import chatapplication.usecases.adapters.Hasher;

public class UserRegistration extends UseCase<InputValues, OutputValues> {
   private DataStorage _dataStorage;
   private Hasher _hasher;

   public UserRegistration(DataStorage dataStorage, Hasher hasher) {
      this._dataStorage = dataStorage;
      this._hasher = hasher;
   }

   public OutputValues execute(InputValues var1) {
      throw new Error("Unresolved compilation problems: \n\tThe method execute(UserRegistration.InputValues) of type UserRegistration must override or implement a supertype method\n\tUserBuilder cannot be resolved to a type\n");
   }

   public OutputValues excute(InputValues input) {
      return null;
   }

   public class InputValues {
    private String _email;
    private String _password;
 
        public UserRegistration$InputValues(String email, String password) {
        this._email = email;
        this._password = password;
        }
    }

    public class OutputValues {
        private final UserRegistration.RegisterResult _result;
        private final String _message;
     
        public UserRegistration$OutputValues(UserRegistration.RegisterResult result, String message) {
           this._message = message;
           this._result = result;
        }
     
        public UserRegistration.RegisterResult getResult() {
           return this._result;
        }
     
        public String getMessage() {
           return this._message;
        }
     }

     public enum UserRegistration$RegisterResult {
        Successed,
        Failed;
     
        private UserRegistration$RegisterResult() {
        }
     }   
}
