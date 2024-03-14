package com.chatapplication.usecases.user;

import com.chatapplication.domains.FileBase;
import com.chatapplication.infrastructure.data.InMemoryFileStorage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.FileFolder;

public class SaveAttachmentToFile extends UseCase<SaveAttachmentToFile.InputSaveAttachmentToFile,SaveAttachmentToFile.OutputSaveAttachmentToFile> {
    private InMemoryFileStorage _fileStorage;
    private FileFolder _fileFolder;

    public SaveAttachmentToFile(InMemoryFileStorage fileStorage,FileFolder fileFolder) {
        _fileStorage = fileStorage;
        _fileFolder = fileFolder;
    }

    public class InputSaveAttachmentToFile {
        private String _idFile;
        InputSaveAttachmentToFile(String idFile) {
            _idFile =idFile;
        }
    }

    public enum SaveAttachmentToFileResult {
        Successed,
        Failed;
     }

    public class OutputSaveAttachmentToFile {
        private final SaveAttachmentToFileResult _result;
        private final String _message;

        OutputSaveAttachmentToFile(SaveAttachmentToFileResult result, String message) {
            _result = result;
            _message = message;
        }

        public SaveAttachmentToFileResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputSaveAttachmentToFile excute(InputSaveAttachmentToFile input) {
        FileBase file = _fileStorage.getFile().getFirst(f->f.getID().equals(input._idFile));
        if(file == null) {
            return new OutputSaveAttachmentToFile(SaveAttachmentToFileResult.Failed, "wrong id");
        } else {
            _fileFolder.saveAttachment(file.getFileByte(), input._idFile);
            return new OutputSaveAttachmentToFile(SaveAttachmentToFileResult.Successed, "success");
        }
    }
}
