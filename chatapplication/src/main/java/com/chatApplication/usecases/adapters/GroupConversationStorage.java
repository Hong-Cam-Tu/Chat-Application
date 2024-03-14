package com.chatapplication.usecases.adapters;

import com.chatApplication.domains.GroupConversation;

public interface GroupConversationStorage {
    Repository<GroupConversation> getConversation();
    
    void cleanAll();
}
