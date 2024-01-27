// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.usecases.user;

import chatapplication.usecases.UseCase;
import chatapplication.usecases.adapters.Hasher;

public class LoginUseCase extends UseCase<InputValues, OutputValues> {
   private DataStorage _dataStorage;
   private Hasher _hasher;

   public LoginUseCase() {
      throw new Error("Unresolved compilation problem: \n\tDataStorage cannot be resolved to a type\n");
   }

   public OutputValues excute(InputValues var1) {
      throw new Error("Unresolved compilation problem: \n");
   }

   public class InputValues {
        private String _email;
        private String _password;
 
        public LoginUseCase$InputValues(String var1, String var2) {
        throw new Error("Unresolved compilation problem: \n\tDataStorage cannot be resolved to a type\n");
        }
    }

    public enum LoginUseCase$LoginResult {
        Successed,
        Failed;
     
        private LoginUseCase$LoginResult() {
           throw new Error("Unresolved compilation problem: \n\tDataStorage cannot be resolved to a type\n");
        }
     }

   

    public abstract class OutputValues {
    private final LoginResult _result;
    private final String _message;

    public OutputValues(LoginUseCase var1, LoginUseCase.LoginResult var2, String var3) {
        throw new Error("Unresolved compilation problem: \n\tDataStorage cannot be resolved to a type\n");
    }
    }

}
