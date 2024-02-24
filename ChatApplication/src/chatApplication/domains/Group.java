// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains;

import java.util.ArrayList;
import java.util.List;

import chatApplication.domains.groupimpl.PrivateGroup;
import chatApplication.domains.groupimpl.PublicGroup;

public abstract class Group {
    private final String _idConversation;
    private final String _idFile;
    private String _nameGroup;
    private List<PublicGroup> _publicGroups = new ArrayList<>();
    private List<PrivateGroup> _privateGroups = new ArrayList<>();

    public List<PublicGroup> getPublicGroups() {
        return _publicGroups;
    }
    
    public String getIdConversation() {
        return _idConversation;
    }


    public void setPublicGroups(PublicGroup publicGroup) {
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

    public void setNameGroup(String nameGroup) {
        this._nameGroup = nameGroup;
    }

    public Group(String nameGroup,String idConversation,String idFile) {
        _nameGroup = nameGroup;
        _idConversation = idConversation;
        _idFile = idFile;
    }
    
    public List<String> getAllGroupByIdUser(String id) {
        List<String> groups = new ArrayList<>();
        for(PublicGroup publicGroup : _publicGroups) {
            if(publicGroup.isWithinGroup(id)){
                groups.add(publicGroup.getNameGroup());
            }
        }
        return groups;
    }
    public abstract List<User> getUsersByName(String name);
    
    public abstract boolean isWithinGroup(String id);

    public abstract void inviteMember(String idAdmin,String  idMember);

    public abstract void removeMember(String idAdmin,String idMember);

    public abstract User findMemberById(String idMember);
}
