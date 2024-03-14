package com.chatapplication.infrastructure.data;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.infrastructure.repositories.InMemoryRepository;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;
import com.chatapplication.usecases.adapters.Repository;

public class InMemoryIndividualConversationStorage implements IndividualConversationStorage {
    private Repository<IndividualConversation> _conversation;
    private static InMemoryIndividualConversationStorage _storage;

    public InMemoryIndividualConversationStorage() {
        _conversation = new InMemoryRepository<>();
    }

    public static InMemoryIndividualConversationStorage getInstance() {
        if(_storage == null) {
            _storage = new InMemoryIndividualConversationStorage();
        }
        return _storage;
    }

    @Override
    public Repository<IndividualConversation> getConversation() {
        return _conversation;
    }

    @Override
    public void cleanAll() {
        _conversation.deleteAll();
    }
    
    
}
