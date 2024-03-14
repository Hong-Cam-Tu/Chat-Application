package com.chatapplication.usecases.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.chatapplication.domains.Group;
import com.chatapplication.domains.groupimpl.PrivateGroup;
import com.chatapplication.domains.groupimpl.PublicGroup;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class GetAllAttendedGroups extends UseCase<GetAllAttendedGroups.InputGetAllAttendedGroups,GetAllAttendedGroups.OutputGetAllAttendedGroups> { 
    private PrivateGroupStorage _privateGroupStorage;
    private PublicGroupStorage _publicGroupStorage;

    public GetAllAttendedGroups(PublicGroupStorage publicGroupStorage, PrivateGroupStorage privateGroupStorage ) {
        _privateGroupStorage = privateGroupStorage;
        _publicGroupStorage = publicGroupStorage;
    }

    public static class InputGetAllAttendedGroups {
        private String _idUser;

        public InputGetAllAttendedGroups(String idUser) {
            _idUser = idUser;
        }
    }

    public static class OutputGetAllAttendedGroups {
        private final GetAllAttendedGroupsResult _result;
        private final String _message;
        private final List<Group> _groups;

        public OutputGetAllAttendedGroups(GetAllAttendedGroupsResult result, String message,List<Group> groups) {
            _message = message;
            _result = result;
            _groups = groups;
        }

        public GetAllAttendedGroupsResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }

        public List<Group> getGroups() {
            return _groups;
        }
    }

    public static enum GetAllAttendedGroupsResult {
        Successed, Failed
    }

    @Override
    public OutputGetAllAttendedGroups excute(InputGetAllAttendedGroups input) {
        List<PrivateGroup> privateGroup = _privateGroupStorage.getPrivateGroup().getAll(group-> group.isWithinGroup(input._idUser));
        List<PublicGroup> publicGroup = _publicGroupStorage.getPublicGroup().getAll(group->group.isWithinGroup(input._idUser));

        List<Group> groups = Stream.of(privateGroup,publicGroup)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
        if(groups.size() == 0) {
            return new OutputGetAllAttendedGroups(GetAllAttendedGroupsResult.Failed, "There are no group attended", null);
        } else {
            return new OutputGetAllAttendedGroups(GetAllAttendedGroupsResult.Successed, "Successed", groups);
        }
    }

}
