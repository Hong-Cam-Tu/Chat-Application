package com.chatapplication.usecases.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.chatApplication.domains.Group;
import com.chatApplication.domains.groupimpl.PrivateGroup;
import com.chatApplication.domains.groupimpl.PublicGroup;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.PrivateGroupStorage;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class GetAllAttendedGroups extends UseCase<GetAllAttendedGroups.InputValues,GetAllAttendedGroups.OutputValues> { 
    private PrivateGroupStorage _privateGroupStorage;
    private PublicGroupStorage _publicGroupStorage;

    public GetAllAttendedGroups(PublicGroupStorage publicGroupStorage, PrivateGroupStorage privateGroupStorage ) {
        _privateGroupStorage = privateGroupStorage;
        _publicGroupStorage = publicGroupStorage;
    }

    public static class InputValues {
        private String _idUser;

        public InputValues(String idUser) {
            _idUser = idUser;
        }
    }

    public static class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<Group> _groups;

        public OutputValues(Result result, String message,List<Group> groups) {
            _message = message;
            _result = result;
            _groups = groups;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<Group> getGroups() {
            return _groups;
        }
    }

    public static enum Result {
        Successed, Failed
    }

    @Override
    public OutputValues excute(InputValues input) {
        List<PrivateGroup> privateGroup = _privateGroupStorage.getPrivateGroup().getAll(group-> group.isWithinGroup(input._idUser));
        List<PublicGroup> publicGroup = _publicGroupStorage.getPublicGroup().getAll(group->group.isWithinGroup(input._idUser));

        List<Group> groups = Stream.of(privateGroup,publicGroup)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
        if(groups.size() == 0) {
            return new OutputValues(Result.Failed, "There are no group attended", null);
        } else {
            return new OutputValues(Result.Successed, "Successed", groups);
        }
    }

}
