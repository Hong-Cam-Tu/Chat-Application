// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.usecases.user;

import chatApplication.usecases.UseCase;
import chatApplication.usecases.adapters.DataStorage;
import chatApplication.usecases.adapters.Hasher;

public class LoginUseCase extends UseCase<LoginUseCase.InputValues, LoginUseCase.OutputValues> {
   private DataStorage _dataStorage;
   private Hasher _hasher;

   public LoginUseCase(DataStorage dataStorage, Hasher hasher) {
      _dataStorage = dataStorage;
      _hasher = hasher;
   }

   

   @Override
   public OutputValues excute(InputValues input) {
      return new OutputValues(LoginResult.Successed, "");
   }



   public class InputValues {
        private String _username;
        private String _password;
 
        public InputValues(String username, String password) {
            _username = username;
            _password = password;
        }
    }

    public enum LoginResult {
        Successed,
        Failed;
     }

   

    public static class OutputValues {
         private final LoginResult _result;
         private final String _message;

         public OutputValues(LoginResult result, String message) {
            _result = result;
            _message = message;
         }
    }

}
