// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains;

import java.util.UUID;

public class BaseEntity {
   private String _id = UUID.randomUUID().toString();

   public String getID() {
      return this._id;
   }

   public BaseEntity() {
   }

   public Object getId() {
      return null;
   }
}
