package com.chatapplication.usecases.adapters;

import com.chatapplication.domains.groupimpl.PublicGroup;

public interface PublicGroupStorage {
    Repository<PublicGroup> getPublicGroup();

        void cleanAll();
}
