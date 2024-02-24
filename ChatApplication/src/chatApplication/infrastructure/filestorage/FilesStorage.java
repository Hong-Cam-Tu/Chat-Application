package chatApplication.infrastructure.filestorage;

import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.groupimpl.EntityGroup;

public class FilesStorage {
    private List<EntityGroup> _files = new ArrayList<>();

    public EntityGroup getFileById(String id) {
        for(EntityGroup file : _files) {
            if(file.getId().equals(id)) {
                return file;
            }
        }
        return null;
    }
}