package com.chatapplication.usecases.adapters;

import com.chatApplication.domains.groupimpl.PrivateGroup;

public interface PrivateGroupStorage {
        Repository<PrivateGroup> getPrivateGroup();

        void cleanAll();
}
