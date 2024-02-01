// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.files;

import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.groupimpl.EntityGroup;
public abstract class File {
    private double _capacity;
    private List<EntityGroup> files = new ArrayList<>();
    
    public File(double _capacity) {
        this._capacity = _capacity;
    }

    public abstract File build();

    public double getCapacity() {
        return _capacity;
    }

    public void setCapacity(double capacity) {
        _capacity = capacity;
    }

    public EntityGroup getFileById(String id) {
        for(EntityGroup file : files) {
            if(file.getId().equals(id)) {
                return file;
            }
        }
        return null;
    }
}
