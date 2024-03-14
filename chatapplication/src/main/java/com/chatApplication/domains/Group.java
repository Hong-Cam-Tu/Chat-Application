// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.domains;

import java.util.List;

import com.chatapplication.infrastructure.data.InMemoryGroupConversationStorage;

public abstract class Group extends BaseEntity {
    private final String _idConversation;
    private List<String> _idFiles;
    private String _nameGroup; 
    InMemoryGroupConversationStorage inMemoryGroupConversationStorage;

    public String getIdConversation() {
        return _idConversation;
    }

    public String getNameGroup() {
        return _nameGroup;
    }

    public List<String> getIdFiles() {
        return _idFiles;
    }

    public void addIdFiles(String _idFiles) {
        this._idFiles.add(_idFiles);
    }

    public void setNameGroup(String nameGroup) {
        this._nameGroup = nameGroup;
    }

    public Group(String nameGroup) {
        super();
        _nameGroup = nameGroup;
        GroupConversation groupConversation = new GroupConversation();
        inMemoryGroupConversationStorage = inMemoryGroupConversationStorage.getInstance();
        inMemoryGroupConversationStorage.getConversation().add(groupConversation);
        _idConversation = groupConversation.getID();
    }

    public abstract List<User> getUsersByName(String name);
    
    public abstract boolean isWithinGroup(String id);

    public abstract void inviteMember(String idAdmin,String  idMember);

    public abstract void removeMember(String idAdmin,String idMember);

    public abstract User findMemberById(String idMember);

    public abstract boolean leaveGroupMember(String idMember);

    public abstract boolean removeMemberByAdmin(String idMember,String idAdmin);

    public abstract boolean approveJoinRequest(String idRequest,String idAdmin,boolean isApproval);
}
