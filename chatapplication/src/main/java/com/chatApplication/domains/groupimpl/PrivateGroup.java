// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatApplication.domains.groupimpl;


import java.util.ArrayList;
import java.util.List;
import com.chatApplication.domains.Group;
import com.chatApplication.domains.User;

public class PrivateGroup extends Group {
   private List<User> _members ;
   private List<User> _admins;
   private List<User> _joinRequest;

   public PrivateGroup(String nameGroup,User Admin) {
      super(nameGroup);
      setAdmins(Admin);
      _members = new ArrayList<>();
      _admins = new ArrayList<>();
      _joinRequest = new ArrayList<>();
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
         _members.add(findMemberById(idMember));
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
         if(idAdmin.equals(admin.getId())) {
            return admin;
         }
      }
      return null;
   }

   @Override
   public boolean leaveGroupMember(String idMember) {
      User user = findMemberById(idMember);
      if(user==null) {
         return false;
      }
      _members.remove(user);
      return true;
   }

   public boolean leaveGroupAdmin(String idAdmin) {
      User admin = findAdminById(idAdmin);
      if(admin==null || _admins.size()==1) {
         return false;
      } 
      _admins.remove(admin);
      return true;
   }

   @Override
   public boolean removeMemberByAdmin(String idMember, String idAdmin) {
      if(isAdmin(idAdmin) && isWithinGroup(idMember)) {
         leaveGroupMember(idMember);
      }
      return false;
   }

   @Override
   public boolean approveJoinReqeust(String idRequest, String idAdmin, boolean isApproval) {
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
