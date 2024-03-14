// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.domains.groupimpl;

import java.util.UUID;

import com.chatapplication.domains.Group;
import com.chatapplication.domains.User;

import java.util.ArrayList;
import java.util.List;

public class PublicGroup extends Group {
   private List<User> _members;
   private String _joinCode;
   private List<User> _joinRequest;

   public String getJoinCode() {
      return _joinCode;
   }

   public List<User> getAllMembers() {
      return _members;
   }

   public PublicGroup(String name,User userCreator) {
      super(name);
      _joinCode =  UUID.randomUUID().toString();
      _members = new ArrayList<>();
      _joinRequest = new ArrayList<>();
      _members.add(userCreator);
   }

   public void addJoinRequest(User userRequest) {
      _joinRequest.add(userRequest);
   }

   public List<User> getJoinRequest() {
      return _joinRequest;
   }
   //get user by name
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
   // add member
   public void addMember(User member) {
      _members.add(member);
   }
   //check whether within group
   @Override
   public boolean isWithinGroup(String id) {
      for(User member : getAllMembers()) {
         if(member.getID().equals(id)) {
            return true;
         }
      }
      return false;
   }
   //invite member
   @Override
   public void inviteMember(String idAdmin, String idMember) {
      if(isWithinGroup(idAdmin)) {
         _members.add(findMemberById(idMember));
      }
   }
   //remove member
   @Override
   public void removeMember(String idAdmin, String idMember) {
      if(isWithinGroup(idAdmin)) {
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
   //remove memver by admin
   @Override
   public boolean removeMemberByAdmin(String idMember, String idAdmin) {
      if(isWithinGroup(idAdmin) && isWithinGroup(idMember)) {
         leaveGroupMember(idMember);
         return true;
      }
      return false;
   }
   //get user from list join request
   public User findJoinRequestById(String idRequest) {
      for(User request : _joinRequest) {
         if(idRequest.equals(request.getID())) {
            return request;
         }
      }
      return null;
   }   
   // accept join request
   @Override
   public boolean approveJoinRequest(String idRequest, String idAdmin, boolean isApproval) {
         User requestUser = findJoinRequestById(idRequest);
         if(requestUser == null || !isWithinGroup(idAdmin)) {
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
