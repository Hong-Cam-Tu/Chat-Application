package com.chatapplication.usecases.adapters;

import com.chatApplication.domains.groupimpl.PublicGroup;

public interface PublicGroupStorage {
    Repository<PublicGroup> getPublicGroup();

        void cleanAll();
}
