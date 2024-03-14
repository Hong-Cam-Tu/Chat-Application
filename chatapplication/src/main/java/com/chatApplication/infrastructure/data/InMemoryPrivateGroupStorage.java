package com.chatapplication.infrastructure.data;

import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.infrastructure.repositories.InMemoryRepository;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;
import com.chatapplication.usecases.adapters.Repository;

public class InMemoryPrivateGroupStorage implements PrivateGroupStorage {
    private Repository<PrivateGroup> _privateGroups;
    private static InMemoryPrivateGroupStorage _storage;

    public InMemoryPrivateGroupStorage() {
            _privateGroups = new InMemoryRepository<PrivateGroup>();
    }
    @Override
    public Repository<PrivateGroup> getPrivateGroup() {
        return _privateGroups;
    }

    public static InMemoryPrivateGroupStorage getInstance() {
        if (_storage == null) {
            _storage = new InMemoryPrivateGroupStorage();
        }
        return _storage;
    }

    @Override
    public void cleanAll() {
        _privateGroups.deleteAll();
    }
    
}
