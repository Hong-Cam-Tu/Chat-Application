// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.domains.groupimpl;


import java.util.ArrayList;
import java.util.List;
import com.chatapplication.domains.Group;
import com.chatapplication.domains.User;

public class PrivateGroup extends Group {
   private List<User> _members ;
   private List<User> _admins;
   private List<User> _joinRequest;

   public PrivateGroup(String nameGroup,User admin) {
      super(nameGroup);
      _members = new ArrayList<>();
      _admins = new ArrayList<>();
      _joinRequest = new ArrayList<>();
      setAdmin(admin);
   }

   public void addJoinRequest(User userRequest) {
      _joinRequest.add(userRequest);
   }

   public List<User> getJoinRequest() {
      return _joinRequest;
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

   public void setAdmin(User admin) {
      _admins.add(admin);
   }
   // get user by name
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
   // check is admin
   public boolean isAdmin(String id) {
      for(User admin : getAdmins()) {
         if(admin.getID().equals(id)) {
            return true;
         }
      }
      return false;
   }
   // check whether within group
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
   //invite member
   @Override
   public void inviteMember(String idAmin, String idMember) {
      if(isAdmin(idAmin)) {
         _members.add(findMemberById(idMember));
      }
   }
   //remove member
   @Override
   public void removeMember(String idAdmin, String idMember) {
      if(isAdmin(idAdmin)) {
         _members.remove(findMemberById(idMember));
      }
   }
   //find member by id
   @Override
   public User findMemberById(String idMember) {
      for(User member : _members) {
         if(idMember.equals(member.getID())) {
            return member;
         }
      }
      return null;
   }
   //get requested user by id
   public User findJoinRequestById(String idRequest) {
      for(User request : _joinRequest) {
         if(idRequest.equals(request.getID())) {
            return request;
         }
      }
      return null;
   }

   public User findAdminById(String idAdmin) {
      for(User admin : _admins) {
         if(idAdmin.equals(admin.getID())) {
            return admin;
         }
      }
      return null;
   }
   //leave group of member
   @Override
   public boolean leaveGroupMember(String idMember) {
      User user = findMemberById(idMember);
      if(user==null) {
         return false;
      }
      _members.remove(user);
      return true;
   }
   // leave group of admin
   public boolean leaveGroupAdmin(String idAdmin) {
      User admin = findAdminById(idAdmin);
      if(admin==null || _admins.size()==1) {
         return false;
      } 
      _admins.remove(admin);
      return true;
   }
   // remove member by admin
   @Override
   public boolean removeMemberByAdmin(String idMember, String idAdmin) {
      if(isAdmin(idAdmin) && isWithinGroup(idMember)) {
         leaveGroupMember(idMember);
         return true;
      }
      return false;
   }
   //accept join request
   @Override
   public boolean approveJoinRequest(String idRequest, String idAdmin, boolean isApproval) {
         User requestUser = findJoinRequestById(idRequest);
         if(requestUser == null || !isAdmin(idAdmin)) {
            return false;
         }
         _joinRequest.remove(requestUser);
         if(isApproval) {
            _members.add(requestUser);
            return true;
         }
         return false;
   }
}
