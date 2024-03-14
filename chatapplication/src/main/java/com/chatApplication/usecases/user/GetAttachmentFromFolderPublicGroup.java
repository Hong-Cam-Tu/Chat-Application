package com.chatapplication.usecases.user;

import java.util.ArrayList;
import java.util.List;

import com.chatapplication.domains.Group;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.FileFolder;
import com.chatapplication.usecases.adapters.PublicGroupStorage;

public class GetAttachmentFromFolderPublicGroup 
extends UseCase<GetAttachmentFromFolderPublicGroup.InputGetAttachmentFromFolderPublicGroup,
GetAttachmentFromFolderPublicGroup.OutputGetAttachmentFromFolderPublicGroup> {
    private PublicGroupStorage _publicGroupStorage;
    private FileFolder _fileFolder;

    public GetAttachmentFromFolderPublicGroup(PublicGroupStorage publicGroupStorage,FileFolder fileFolder) {
        _publicGroupStorage = publicGroupStorage;
        _fileFolder = fileFolder;
    }

    public class InputGetAttachmentFromFolderPublicGroup {
        private String _idGroup;

        InputGetAttachmentFromFolderPublicGroup(String idFile) {
            _idGroup =idFile;
        }
    }

    public enum GetAttachmentFromFolderPublicGroupResult {
        Successed,
        Failed;
     }

    public class OutputGetAttachmentFromFolderPublicGroup {
        private final GetAttachmentFromFolderPublicGroupResult _result;
        private final String _message;
        private final List<byte[]> _files;

        OutputGetAttachmentFromFolderPublicGroup(GetAttachmentFromFolderPublicGroupResult result, String message,List<byte[]> files) {
            _result = result;
            _message = message;
            _files = files;
        }

        public GetAttachmentFromFolderPublicGroupResult getResult() {
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
    public OutputGetAttachmentFromFolderPublicGroup excute(InputGetAttachmentFromFolderPublicGroup input) {
        Group publicGroup = _publicGroupStorage.getPublicGroup().getFirst(g->g.getID().equals(input._idGroup));
        if(publicGroup == null) {
            return new OutputGetAttachmentFromFolderPublicGroup(GetAttachmentFromFolderPublicGroupResult.Failed, "Wrong id",null);
        } else {
            List<String> idFiles = publicGroup.getIdFiles();
            List<byte[]> files = new ArrayList<>();
            for(String idFile:idFiles) {
                files.add(_fileFolder.getAttachment(idFile));
            }
            return new OutputGetAttachmentFromFolderPublicGroup(GetAttachmentFromFolderPublicGroupResult.Successed, "Successed",files);
        }
    }
}
