package com.chatapplication.usecases.adapters;

import com.chatapplication.domains.IndividualConversation;

public interface IndividualConversationStorage {
    Repository<IndividualConversation> getConversation();
    
    void cleanAll();
}
