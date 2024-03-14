package com.project;

import static org.junit.Assert.assertTrue;
import org.junit.Assert.assertEquals;

import org.junit.Test;

import com.chatApplication.infracstructure.services.MD5Hasher;
import com.chatApplication.infrastructure.data.InMemoryDataStorage;
import com.chatApplication.usecases.adapters.DataStorage;
import com.chatApplication.usecases.user.UserRegistration;
import com.chatApplication.usecases.user.UserRegistration.InputValues;
import com.chatApplication.usecases.user.UserRegistration.OutputValues;

/**
 * Unit test for simple App.
 */
public class AppTest 
{   
    UserRegistration userRegistration;
    InMemoryDataStorage dataStorage;
    MD5Hasher hasher;

    public static enum Result {
        Successed, Failed
    }

    @BeforeAll
    public void setUp() {
        hasher = new MD5Hasher();
        dataStorage = dataStorage.getInstance();
    }

    @Test 
    void testRegister() {
        userRegistration= new UserRegistration(dataStorage, hasher);
       OutputValues output = userRegistration.excute(new InputValues("tuan","tuan"));
        
    }
}
