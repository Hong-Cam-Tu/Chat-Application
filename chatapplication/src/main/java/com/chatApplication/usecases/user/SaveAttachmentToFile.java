package com.chatApplication.usecases.user;

import com.chatApplication.domains.FileBase;
import com.chatApplication.infrastructure.data.InMemoryFileStorage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.FileFolder;

public class SaveAttachmentToFile extends UseCase<SaveAttachmentToFile.InputValues,SaveAttachmentToFile.OutputValues> {
    private InMemoryFileStorage _fileStorage;
    private FileFolder _fileFolder;

    public SaveAttachmentToFile(InMemoryFileStorage fileStorage,FileFolder fileFolder) {
        _fileStorage = fileStorage;
        _fileFolder = fileFolder;
    }

    public class InputValues {
        private String _idFile;
        InputValues(String idFile) {
            _idFile =idFile;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;

        OutputValues(Result result, String message) {
            _result = result;
            _message = message;
        }

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        FileBase file = _fileStorage.getFile().getFirst(f->f.getID().equals(input._idFile));
        if(file == null) {
            return new OutputValues(Result.Failed, "wrong id");
        } else {
            _fileFolder.saveAttachment(file.getFileByte(), input._idFile);
            return new OutputValues(Result.Successed, "success");
        }
    }
}
