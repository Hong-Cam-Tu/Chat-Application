package com.chatApplication.usecases.adapters;

import com.chatApplication.domains.groupimpl.PrivateGroup;

public interface PrivateGroupStorage {
        Repository<PrivateGroup> getPrivateGroup();

        void cleanAll();
}
