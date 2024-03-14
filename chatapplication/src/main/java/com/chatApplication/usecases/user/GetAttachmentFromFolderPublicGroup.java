package com.chatApplication.usecases.user;

import java.util.ArrayList;
import java.util.List;

import com.chatApplication.domains.Group;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.FileFolder;
import com.chatApplication.usecases.adapters.PublicGroupStorage;

public class GetAttachmentFromFolderPublicGroup extends UseCase<GetAttachmentFromFolderPublicGroup.InputValues,GetAttachmentFromFolderPublicGroup.OutputValues> {
    private PublicGroupStorage _publicGroupStorage;
    private FileFolder _fileFolder;

    public GetAttachmentFromFolderPublicGroup(PublicGroupStorage publicGroupStorage,FileFolder fileFolder) {
        _publicGroupStorage = publicGroupStorage;
        _fileFolder = fileFolder;
    }

    public class InputValues {
        private String _idGroup;

        InputValues(String idFile) {
            _idGroup =idFile;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final List<byte[]> _files;

        OutputValues(Result result, String message,List<byte[]> files) {
            _result = result;
            _message = message;
            _files = files;
        }

        public Result getResult() {
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
    public OutputValues excute(InputValues input) {
        Group publicGroup = _publicGroupStorage.getPublicGroup().getFirst(g->g.getID().equals(input._idGroup));
        if(publicGroup == null) {
            return new OutputValues(Result.Failed, "Wrong id",null);
        } else {
            List<String> idFiles = publicGroup.getIdFiles();
            List<byte[]> files = new ArrayList<>();
            for(String idFile:idFiles) {
                files.add(_fileFolder.getAttachment(idFile));
            }
            return new OutputValues(Result.Successed, "Successed",files);
        }
    }
}
