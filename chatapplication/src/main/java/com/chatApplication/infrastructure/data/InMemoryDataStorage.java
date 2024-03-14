package com.chatapplication.infrastructure.data;

import com.chatApplication.domains.User;
import com.chatApplication.infrastructure.repositories.InMemoryRepository;
import com.chatApplication.usecases.adapters.DataStorage;
import com.chatApplication.usecases.adapters.Repository;

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
