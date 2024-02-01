// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains;

import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.groupimpl.PrivateGroup;
import chatApplication.domains.groupimpl.PublicGroup;


public abstract class Group {
    private String _idFile;
    private String _nameGroup;
    private List<PublicGroup> _publicGroups = new ArrayList<>();
    private List<PrivateGroup> _privateGroups = new ArrayList<>();

    public List<PublicGroup> getPublicGroups() {
        return _publicGroups;
    }

    public void set_publicGroups(PublicGroup publicGroup) {
        this._publicGroups.add(publicGroup);
    }

    public List<PrivateGroup> getPrivateGroups() {
        return _privateGroups;
    }

    public void addPrivateGroups(PrivateGroup privateGroup) {
        this._privateGroups.add(privateGroup);
    }

    public String getNameGroup() {
        return _nameGroup;
    }
    
    public String getIdFile() {
        return _idFile;
    }

    public void setIdFile(String idFile) {
        this._idFile = idFile;
    }

    public void setNameGroup(String nameGroup) {
        this._nameGroup = nameGroup;
    }

    public Group(String nameGroup) {
        _nameGroup = nameGroup;
    }
    
    public abstract List<User> getUsersByName(String name);
}
