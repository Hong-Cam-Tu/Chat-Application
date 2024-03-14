// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.domains;

import java.util.List;

public abstract class Group extends BaseEntity {
    private String _idConversation;
    private List<String> _idFiles;
    private String _nameGroup; 

    public String getIdConversation() {
        return _idConversation;
    }

    public void setIdConversation(String _idConversation) {
        this._idConversation = _idConversation;
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
    }

    public abstract List<User> getUsersByName(String name);
    
    public abstract boolean isWithinGroup(String id);

    public abstract void inviteMember(String idAdmin,String  idMember);

    public abstract void removeMember(String idAdmin,String idMember);

    public abstract User findMemberById(String idMember);

    public abstract boolean leaveGroupMember(String idMember);

    public abstract boolean removeMemberByAdmin(String idMember,String idAdmin);

    public abstract boolean approveJoinReqeust(String idRequest,String idAdmin,boolean isApproval);
}
