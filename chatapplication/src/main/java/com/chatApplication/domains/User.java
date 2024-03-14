// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.domains;

import java.util.Date;

public class User extends BaseEntity {
   private String _username;
   private String _hashedPassword;
   private String _email;
   private String _lastName;
   private String _firstName;
   private Gender _gender;
   private Date _dateOfBirth;

   public enum Gender {
      MALE, FEMALE, OTHER
   }

   public User(String hashedPassword, String username) {
      super();
      this._hashedPassword = hashedPassword;
      this._username = username;
   }

   public String getUsername() {
      return _username;
   }

   public void setUsername(String username) {
      this._username = username;
   }

   public String getEmail() {
      return this._email;
   }

   public void setEmail(String email) {
      this._email = email;
   }

   public String getLastName() {
      return this._lastName;
   }

   public void setLastName(String lastName) {
      this._lastName = lastName;
   }

   public String getFirstName() {
      return this._firstName;
   }

   public void setFirstName(String firstName) {
      this._firstName = firstName;
   }

   public Gender getGender() {
      return this._gender;
   }

   public void setGender(Gender gender) {
      this._gender = gender;
   }

   public Date getDateOfBirth() {
      return this._dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth) {
      this._dateOfBirth = dateOfBirth;
   }

   public String getFullName() {
      return this._firstName + " " + this._lastName;
   }

   public boolean checkPassword(String password) {
      return password.equals(this._hashedPassword);
   }

   public static class UserBuilder {
      private final String _password;
      private final String _username;
      private String _email;
      private String _lastName;
      private String _firstName;
      private Gender _gender;
      private Date _dateOfBirth;

      public UserBuilder(String username, String password) {
         _username = username;
         _password = password;
      }

      public UserBuilder email(String email) {
         _email = email;
         return this;
      }

      public UserBuilder firstName(String firstName) {
         _firstName = firstName;
         return this;
      }

      public UserBuilder lastName(String lastName) {
         _lastName = lastName;
         return this;
      }

      public UserBuilder gender(Gender gender) {
         _gender = gender;
         return this;
      }

      public UserBuilder dateOfBirth(Date dateOfBirth) {
         _dateOfBirth = dateOfBirth;
         return this;
      }

      public User build() {
         User user = new User(_username, _password);
         return user;
      }
   }
}
