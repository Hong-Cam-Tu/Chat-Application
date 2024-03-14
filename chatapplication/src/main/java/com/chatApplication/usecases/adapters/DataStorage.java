// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.usecases.adapters;

import com.chatapplication.domains.User;

public interface DataStorage {
   Repository<User> getUsers();

   void cleanAll();

}
