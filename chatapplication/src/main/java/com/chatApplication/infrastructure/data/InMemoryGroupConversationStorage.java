package com.chatapplication.infrastructure.data;

import com.chatapplication.domains.GroupConversation;
import com.chatapplication.infrastructure.repositories.InMemoryRepository;
import com.chatapplication.usecases.adapters.GroupConversationStorage;
import com.chatapplication.usecases.adapters.Repository;

public class InMemoryGroupConversationStorage implements GroupConversationStorage {
    private Repository<GroupConversation> _conversation;
    private static InMemoryGroupConversationStorage _storage;

    public InMemoryGroupConversationStorage() {
        _conversation = new InMemoryRepository<>();
    }

    public static InMemoryGroupConversationStorage getInstance() {
        if(_storage == null) {
            _storage = new InMemoryGroupConversationStorage();
        }
        return _storage;
    }

    @Override
    public Repository<GroupConversation> getConversation() {
        return _conversation;
    }

    @Override
    public void cleanAll() {
        _conversation.deleteAll();
    }
    
    
}
