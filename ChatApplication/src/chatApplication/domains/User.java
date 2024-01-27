// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains;

import chatApplication.infracstructure.services.MD5Hasher;

public class User extends BaseEntity {
   private String _hashedPassword;
   private String _email;
   private String _lastName;
   private String _firstName;
   private Gender _gender;
   private String _dateOfBirth;
   private MD5Hasher md5Hasher;

   public enum Gender {
      MALE, FEMALE, OTHER
  }
   public User(String hashedPassword, String email) {
      this._hashedPassword = hashedPassword;
      this._email = email;
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

   public String getDateOfBirth() {
      return this._dateOfBirth;
   }

   public void setDateOfBirth(String dateOfBirth) {
      this._dateOfBirth = dateOfBirth;
   }

   public String getFullName() {
      return this._firstName + " " + this._lastName;
   }

   public boolean checkPassword(String password) {
      String hashedPassword = this.md5Hasher.hash(password);
      return hashedPassword.equals(this._hashedPassword);
   }

   public class User$UserBuilder {
    private String _hashedPassword;
    private String _email;
    private String _lastName;
    private String _firstName;
    private User.UserBuilder.Gender _gender;
    private String _dateOfBirth;
    private MD5Hasher md5Hasher;
 
    public User$UserBuilder() {
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
 
    public String getDateOfBirth() {
       return this._dateOfBirth;
    }
 
    public void setDateOfBirth(String dateOfBirth) {
       this._dateOfBirth = dateOfBirth;
    }
 
    public String getFullName() {
       return this._firstName + " " + this._lastName;
    }
 
    public User build() {
       User user = new User(this._email, this._hashedPassword);
       return user;
    }
    }
}
