// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.usecases.adapters;


import java.util.function.Predicate;

import com.chatApplication.domains.BaseEntity;

import java.util.List;
public interface Repository<T extends BaseEntity> {
   T getById(String id);

   boolean add(T addingEntity);

   void deleteAll();

   T getFirst(Predicate<T> predicate);

   List<T> getAll(Predicate<T> predicate);

   boolean remove(T removeEntity);
}
