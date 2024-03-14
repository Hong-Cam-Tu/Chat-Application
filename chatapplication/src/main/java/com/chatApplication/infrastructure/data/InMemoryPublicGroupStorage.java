package com.chatapplication.infrastructure.data;

import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.infrastructure.repositories.InMemoryRepository;
import com.chatapplication.usecases.adapters.PublicGroupStorage;
import com.chatapplication.usecases.adapters.Repository;

public class InMemoryPublicGroupStorage implements PublicGroupStorage  {
    private Repository<PublicGroup> _publicGroups;
    private static InMemoryPublicGroupStorage _storage;

    public InMemoryPublicGroupStorage() {
            _publicGroups = new InMemoryRepository<PublicGroup>();
    }
    @Override
    public Repository<PublicGroup> getPublicGroup() {
        return _publicGroups;
    }

    public static InMemoryPublicGroupStorage getInstance() {
        if (_storage == null) {
            _storage = new InMemoryPublicGroupStorage();
        }
        return _storage;
    }

    @Override
    public void cleanAll() {
        _publicGroups.deleteAll();
    }
}
