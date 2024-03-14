package com.chatapplication.usecases.user;

import java.util.List;

import com.chatapplication.domains.User;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.DataStorage;

public class FindUsersByGivenName 
extends UseCase<FindUsersByGivenName.InputFindUsersByGivenName,
FindUsersByGivenName.OutputFindUsersByGivenName> {
    private DataStorage _dataStorage;

    public FindUsersByGivenName(DataStorage dataStorage) {
        _dataStorage = dataStorage;
    }

    public static class InputFindUsersByGivenName {
        private String _name;

        public InputFindUsersByGivenName(String name) {
            _name = name;
        }
    }

    public static class OutputFindUsersByGivenName {
        private final FindUsersByGivenNameResult _result;
        private final String _message;
        private final List<User> _users;

        public OutputFindUsersByGivenName(FindUsersByGivenNameResult result, String message,List<User> users) {
            _message = message;
            _result = result;
            _users = users;
        }

        public FindUsersByGivenNameResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<User> getUsers() {
            return _users;
        }
    }

    public static enum FindUsersByGivenNameResult {
        Successed, Failed
    }

   @Override
   public OutputFindUsersByGivenName excute(InputFindUsersByGivenName input) {
        List<User> users = _dataStorage.getUsers().getAll(user -> user.getFullName().contains(input._name));
        if(users.size()==0) {
            return new OutputFindUsersByGivenName(FindUsersByGivenNameResult.Failed, "There are no such User exist",users);
        } else {
            return new OutputFindUsersByGivenName(FindUsersByGivenNameResult.Successed, "Successed",users);
        }
   }
}
