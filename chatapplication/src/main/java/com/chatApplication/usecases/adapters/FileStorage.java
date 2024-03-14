package com.chatapplication.usecases.adapters;

import com.chatApplication.domains.FileBase;

public interface FileStorage {
    Repository<FileBase> getFile();

    void cleanAll();
}
