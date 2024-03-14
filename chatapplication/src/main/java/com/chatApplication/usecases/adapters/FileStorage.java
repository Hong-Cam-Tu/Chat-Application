package com.chatapplication.usecases.adapters;

import com.chatapplication.domains.FileBase;

public interface FileStorage {
    Repository<FileBase> getFile();

    void cleanAll();
}
