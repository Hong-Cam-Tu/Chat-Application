package com.chatapplication.infrastructure.data;

import com.chatapplication.domains.User;
import com.chatapplication.infrastructure.repositories.InMemoryRepository;
import com.chatapplication.usecases.adapters.DataStorage;
import com.chatapplication.usecases.adapters.Repository;

public class InMemoryDataStorage implements DataStorage {
    private Repository<User> _users;
    private static InMemoryDataStorage _storage;

    private InMemoryDataStorage() {
        _users = new InMemoryRepository<User>();
    }

    public static InMemoryDataStorage getInstance() {
        if (_storage == null) {
            _storage = new InMemoryDataStorage();
        }
        return _storage;
    }

    public Repository<User> getUsers(){
        return _users;
    }

    @Override
    public void cleanAll() {
        _users.deleteAll();
    }
}
