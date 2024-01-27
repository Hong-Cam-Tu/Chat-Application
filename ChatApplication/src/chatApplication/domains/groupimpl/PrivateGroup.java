// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains.groupimpl;


import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.domains.User;

public class PrivateGroup extends Group {
   private List<User> _users = new ArrayList<>();
   private List<User> _admins = new ArrayList<>();
   
   
   public List<User> getUsers() {
      return _users;
   }


   public void setUsers(User users) {
      _users.add(users);
   }


   public List<User> getAdmins() {
      return _admins;
   }


   public void setAdmins(User admins) {
      _admins.add(admins);
   }


   public PrivateGroup() {
   }
}
