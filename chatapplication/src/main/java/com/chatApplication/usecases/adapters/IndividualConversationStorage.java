package com.chatApplication.usecases.adapters;

import com.chatApplication.domains.IndividualConversation;

public interface IndividualConversationStorage {
    Repository<IndividualConversation> getConversation();
    
    void cleanAll();
}
