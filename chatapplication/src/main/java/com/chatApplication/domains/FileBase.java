// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.domains;

public abstract class FileBase extends BaseEntity{
    private double _capacity;
    private byte[] _fileByte;
    
    public FileBase(double _capacity,byte[] fileByte) {
        super();
        this._capacity = _capacity;
        _fileByte = fileByte;
    }

    public abstract FileBase build();

    public double getCapacity() {
        return _capacity;
    }

    public void setCapacity(double capacity) {
        _capacity = capacity;
    }

    public byte[] getFileByte() {
        return _fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this._fileByte = fileByte;
    }
}
