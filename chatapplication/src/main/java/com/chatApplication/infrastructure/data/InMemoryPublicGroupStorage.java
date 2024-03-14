package com.chatApplication.infrastructure.data;

import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.infrastructure.repositories.InMemoryRepository;
import com.chatApplication.usecases.adapters.PublicGroupStorage;
import com.chatApplication.usecases.adapters.Repository;

public class InMemoryPublicGroupStorage implements PublicGroupStorage  {
    private Repository<PublicGroup> _publicGroups;
    private static InMemoryPrivateGroupStorage _storage;

    public InMemoryPublicGroupStorage() {
            _publicGroups = new InMemoryRepository<PublicGroup>();
    }
    @Override
    public Repository<PublicGroup> getPublicGroup() {
        return _publicGroups;
    }

    public static InMemoryPrivateGroupStorage getInstance() {
        if (_storage == null) {
            _storage = new InMemoryPrivateGroupStorage();
        }
        return _storage;
    }

    @Override
    public void cleanAll() {
        _publicGroups.deleteAll();
    }
}
