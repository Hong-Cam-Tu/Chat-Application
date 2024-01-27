// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.usecases.adapters;

import chatApplication.domains.User;

public interface DataStorage {
   Repository<User> getUsers();

   void cleanAll();
}
