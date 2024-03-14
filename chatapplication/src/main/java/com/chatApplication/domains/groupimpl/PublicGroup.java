// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatApplication.domains.groupimpl;

import java.util.UUID;

import com.chatApplication.domains.Group;
import com.chatApplication.domains.User;

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

   public PublicGroup(String name) {
      super(name);
      _joinCode =  UUID.randomUUID().toString();
      _members = new ArrayList<>();
      _joinRequest = new ArrayList<>();
   }

   public void addJoinRequest(User userRequest) {
      _joinRequest.add(userRequest);
   }

   public List<User> getJoinRequest() {
      return _joinRequest;
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

   public void addMember(User member) {
      _members.add(member);
   }

   public enum Status {
         Successed,Failed;
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
         _members.add(findMemberById(idMember));
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

   @Override
   public boolean leaveGroupMember(String idMember) {
      User user = findMemberById(idMember);
      if(user==null) {
         return false;
      }
      _members.remove(user);
      return true;
   }

   @Override
   public boolean removeMemberByAdmin(String idMember, String idAdmin) {
      if(isWithinGroup(idAdmin) && isWithinGroup(idMember)) {
         leaveGroupMember(idMember);
      }
      return false;
   }

   public User findJoinRequestById(String idRequest) {
      for(User request : _joinRequest) {
         if(idRequest.equals(request.getID())) {
            return request;
         }
      }
      return null;
   }   

   @Override
   public boolean approveJoinReqeust(String idRequest, String idAdmin, boolean isApproval) {
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
