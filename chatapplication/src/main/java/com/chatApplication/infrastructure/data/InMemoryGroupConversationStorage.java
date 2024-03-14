package com.chatapplication.infrastructure.data;

import com.chatApplication.domains.GroupConversation;
import com.chatApplication.infrastructure.repositories.InMemoryRepository;
import com.chatApplication.usecases.adapters.GroupConversationStorage;
import com.chatApplication.usecases.adapters.Repository;

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
