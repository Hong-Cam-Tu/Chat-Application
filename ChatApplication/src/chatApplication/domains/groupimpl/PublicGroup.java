// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains.groupimpl;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.Group;
import chatApplication.domains.User;
import chatApplication.files.Audio;
import chatApplication.files.Image;
import chatApplication.files.Video;
import chatApplication.usecases.user.LoginUseCase.LoginResult;

public class PublicGroup extends Group {
   private List<User> _users = new ArrayList();
   private List<Video> _videos = new ArrayList<>();
   private List<Audio> _audios = new ArrayList<>();
   private List<Image> _images = new ArrayList<>();
   private String _joinCode;

   public String getJoinCode() {
      return _joinCode;
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
         for(User user : _users) {
            if(user.getFullName().contains(name)) {
               getUsers.add(user);
            }
         }
         return getUsers;
   }

   public OutputValues addUser(User user,String joincode) {
      if(this.getJoinCode().equals(joincode)) {
         _users.add(user);
         return new OutputValues(Status.Successed, "");
      }
         return new OutputValues(Status.Failed, "");
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
}
