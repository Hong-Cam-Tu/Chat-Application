// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.usecases.adapters;

import chatapplication.domains.BaseEntity;
import java.util.function.Predicate;

public interface Repository<T extends BaseEntity> {
   T getById(String var1);

   boolean add(T var1);

   void deleteAll();

   T getFirst(Predicate<T> var1);
}
