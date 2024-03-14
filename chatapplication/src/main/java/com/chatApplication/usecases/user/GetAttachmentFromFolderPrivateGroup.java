package com.chatapplication.usecases.user;

import java.util.ArrayList;
import java.util.List;

import com.chatapplication.domains.Group;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.FileFolder;
import com.chatapplication.usecases.adapters.PrivateGroupStorage;

public class GetAttachmentFromFolderPrivateGroup 
extends UseCase<GetAttachmentFromFolderPrivateGroup.InputGetAttachmentFromFolderPrivateGroup,
GetAttachmentFromFolderPrivateGroup.OutputGetAttachmentFromFolderPrivateGroup> {
    private PrivateGroupStorage _privateGroupStorage;
    private FileFolder _fileFolder;

    public GetAttachmentFromFolderPrivateGroup(PrivateGroupStorage privateGroupStorage,FileFolder fileFolder) {
        _privateGroupStorage = privateGroupStorage;
        _fileFolder = fileFolder;
    }

    public class InputGetAttachmentFromFolderPrivateGroup {
        private String _idGroup;

        InputGetAttachmentFromFolderPrivateGroup(String idGroup) {
            _idGroup =idGroup;
        }
    }

    public enum GetAttachmentFromFolderPrivateGroupResult {
        Successed,
        Failed;
     }

    public class OutputGetAttachmentFromFolderPrivateGroup {
        private final GetAttachmentFromFolderPrivateGroupResult _result;
        private final String _message;
        private final List<byte[]> _files;

        OutputGetAttachmentFromFolderPrivateGroup(GetAttachmentFromFolderPrivateGroupResult result, String message,List<byte[]> files) {
            _result = result;
            _message = message;
            _files = files;
        }

        public GetAttachmentFromFolderPrivateGroupResult getResult() {
            return _result;
        }

        public String getMessage() {
            return _message;
        }

        public List<byte[]> getFiles() {
            return _files;
        }
    }

    @Override
    public OutputGetAttachmentFromFolderPrivateGroup excute(InputGetAttachmentFromFolderPrivateGroup input) {
        Group publicGroup = _privateGroupStorage.getPrivateGroup().getFirst(g->g.getID().equals(input._idGroup));
        if(publicGroup == null) {
            return new OutputGetAttachmentFromFolderPrivateGroup(GetAttachmentFromFolderPrivateGroupResult.Failed, "Wrong id",null);
        } else {
            List<String> idFiles = publicGroup.getIdFiles();
            List<byte[]> files = new ArrayList<>();
            for(String idFile:idFiles) {
                files.add(_fileFolder.getAttachment(idFile));
            }
            return new OutputGetAttachmentFromFolderPrivateGroup(GetAttachmentFromFolderPrivateGroupResult.Successed, "Successed",files);
        }
    }
}
