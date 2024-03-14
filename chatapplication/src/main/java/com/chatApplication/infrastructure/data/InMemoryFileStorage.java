package com.chatapplication.infrastructure.data;

import com.chatapplication.domains.FileBase;
import com.chatapplication.infrastructure.repositories.InMemoryRepository;
import com.chatapplication.usecases.adapters.FileStorage;
import com.chatapplication.usecases.adapters.Repository;

public class InMemoryFileStorage implements FileStorage {
    private Repository<FileBase> _files;
    private static InMemoryFileStorage _storage;

    private InMemoryFileStorage() {
        _files = new InMemoryRepository<FileBase>();
    }

    public static InMemoryFileStorage getInstance() {
        if (_storage == null) {
            _storage = new InMemoryFileStorage();
        }
        return _storage;
    }

    @Override
    public void cleanAll() {
        _files.deleteAll();
    }

    @Override
    public Repository<FileBase> getFile() {
        return _files;
    }
}
