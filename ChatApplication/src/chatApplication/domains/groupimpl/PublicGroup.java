// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains.groupimpl;



import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.domains.User;

public class PublicGroup extends Group {
   List<User> users = new ArrayList();

   public PublicGroup() {
   }

   @Override
   public List<User> getUsersByName(String name) {
         List<User> getUsers = new ArrayList<>();
         for(User user : users) {
            if(user.getFullName().contains(name)) {
               getUsers.add(user);
            }
         }
         return getUsers;
   }
}
