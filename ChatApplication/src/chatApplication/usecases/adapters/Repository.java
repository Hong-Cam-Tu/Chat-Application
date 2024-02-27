// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.usecases.adapters;


import java.util.function.Predicate;

import chatApplication.domains.BaseEntity;

public interface Repository<T extends BaseEntity> {
   T getById(String id);

   boolean add(T addingEntity);

   void deleteAll();

   T getFirst(Predicate<T> predicate);

   T getUsersByName(String name);
}
