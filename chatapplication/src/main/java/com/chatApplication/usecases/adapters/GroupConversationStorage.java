package com.chatapplication.usecases.adapters;

import com.chatapplication.domains.GroupConversation;

public interface GroupConversationStorage {
    Repository<GroupConversation> getConversation();
    
    void cleanAll();
}
