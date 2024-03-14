package com.chatapplication.usecases.adapters;

public interface FileFolder {
    byte[] getAttachment(String idFile);

    String saveAttachment(byte[] byteArray,String idFile);
}
