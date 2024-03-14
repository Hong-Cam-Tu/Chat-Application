// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatApplication.usecases.adapters;

import com.chatApplication.domains.User;

public interface DataStorage {
   Repository<User> getUsers();

   void cleanAll();

}
