// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains.groupimpl;


import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.domains.User;

public class PrivateGroup extends Group {
   public PrivateGroup(String nameGroup) {
      super(nameGroup);
      //TODO Auto-generated constructor stub
   }


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


   @Override
   public List<User> getUsersByName(String name) {
      List<User> getEqualsUsersName = new ArrayList<>();
         for(User user : getUsers()) {
            if(user.getFullName().contains(name)) {
               getEqualsUsersName.add(user);
            }
         }
         return getEqualsUsersName;
   }


   
   public void creator(User user) {
       _admins.add(user);
   }
}
