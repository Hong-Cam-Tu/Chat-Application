// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.infrastructure.repositories;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import chatApplication.domains.BaseEntity;
import chatApplication.usecases.adapters.Repository;

public class InMemoryRepository<T extends BaseEntity> implements Repository<T> {
   private List<T> enities = new ArrayList<>();
   public static int idCounter = 1;

   public InMemoryRepository() {
   }

   public T getById(String id) {
      Optional<T> entity = this.enities.stream().filter((e) -> {
         return e.getId().equals(id);
      }).findFirst();
      return entity.get();
   }

   public boolean add(T entity) {
      if (entity == null) {
         return false;
      } else {
         this.enities.add(entity);
         return true;
      }
   }

   public void deleteAll() {
      this.enities.clear();
   }

   public T getFirst(Predicate<T> predicate) {
      Optional<T> entity = this.enities.stream().filter(predicate).findFirst();
      return entity.isPresent() ? entity.get() : null;
   }

   @Override
   public T getUsersByName(String name) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getUsersByName'");
   }
}
