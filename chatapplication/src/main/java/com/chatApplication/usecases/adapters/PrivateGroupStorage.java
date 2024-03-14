package com.chatapplication.usecases.adapters;

import com.chatapplication.domains.groupimpl.PrivateGroup;

public interface PrivateGroupStorage {
        Repository<PrivateGroup> getPrivateGroup();

        void cleanAll();
}
