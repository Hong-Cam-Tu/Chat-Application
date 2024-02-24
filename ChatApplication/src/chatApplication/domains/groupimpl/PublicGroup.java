// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains.groupimpl;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.domains.User;

public class PublicGroup extends Group {
   private List<User> _members = new ArrayList();
   private String _joinCode;

   public String getJoinCode() {
      return _joinCode;
   }

   public List<User> getAllMembers() {
      return _members;
   }

   public void setJoinCode(String joinCode) {
      _joinCode =  UUID.randomUUID().toString();
   }

   public PublicGroup(String name) {
      super(name);
   }

   @Override
   public List<User> getUsersByName(String name) {
         List<User> getUsers = new ArrayList<>();
         for(User user : _members) {
            if(user.getFullName().contains(name)) {
               getUsers.add(user);
            }
         }
         return getUsers;
   }

   public OutputValues addMemberbyJoinCode(User member,String joinCode) {
      if(this.getJoinCode().equals(joinCode)) {
         addMember(member);
         return new OutputValues(Status.Successed, "");
      }
         return new OutputValues(Status.Failed, "");
   }

   public void addMember(User member) {
      _members.add(member);
   }

   public enum Status {
         Successed,Failed;
   }

   public static class OutputValues {
         private final Status _result;
         private final String _message;

         public OutputValues(Status result, String message) {
            _result = result;
            _message = message;
         }
    }

   @Override
   public boolean isWithinGroup(String id) {
      for(User member : getAllMembers()) {
         if(member.getID().equals(id)) {
            return true;
         }
      }
      return false;
   }

   @Override
   public void inviteMember(String idAdmin, String idMember) {
      if(isWithinGroup(idAdmin)) {
         this.addMember(findMemberById(idMember));
      }
   }

   @Override
   public void removeMember(String idAdmin, String idMember) {
      if(isWithinGroup(idAdmin)) {
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
