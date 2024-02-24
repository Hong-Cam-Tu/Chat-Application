// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains.groupimpl;


import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.domains.User;

public class PrivateGroup extends Group {
   
   private List<User> _members = new ArrayList<>();
   private List<User> _admins = new ArrayList<>();

   public PrivateGroup(String nameGroup) {
      super(nameGroup);
      //TODO Auto-generated constructor stub
   }

   public List<User> getAllMembers() {
      return _members;
   }


   public void addMember(User member) {
      _members.add(member);
   }

   public List<User> getAdmins() {
      return _admins;
   }

   public void setAdmins(User admin) {
      _admins.add(admin);
   }

   @Override
   public List<User> getUsersByName(String name) {
      List<User> getEqualsUsersName = new ArrayList<>();
         for(User user : getAllMembers()) {
            if(user.getFullName().contains(name)) {
               getEqualsUsersName.add(user);
            }
         }
         return getEqualsUsersName;
   }

   public boolean isAdmin(String id) {
      for(User admin : getAdmins()) {
         if(admin.getID().equals(id)) {
            return true;
         }
      }
      return false;
   }

   public void creator(User user) {
       _admins.add(user);
   }

   @Override
   public boolean isWithinGroup(String id) {
      for(User member : getAllMembers()) {
         if(member.getID().equals(id)) {
            return true;
         }
      }

      for(User admin : getAdmins()) {
         if(admin.getID().equals(id)) {
            return true;
         }
      }
      return false;
   }

   @Override
   public void inviteMember(String idAmin, String idMember) {
      if(isAdmin(idAmin)) {
         this.addMember(findMemberById(idMember));
      }
   }

   @Override
   public void removeMember(String idAdmin, String idMember) {
      if(isAdmin(idAdmin)) {
         _members.remove(findMemberById(idMember));
      }
   }

   @Override
   public User findMemberById(String idMember) {
      for(User member : _members) {
         if(idMember.equals(member.getId())) {
            return member;
         }
      }
      return null;
   }
}
