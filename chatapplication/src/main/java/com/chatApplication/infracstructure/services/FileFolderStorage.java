package com.chatapplication.infracstructure.services;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileOutputStream;
import com.chatApplication.usecases.adapters.FileFolder;

public class FileFolderStorage implements FileFolder {

    @Override
    public byte[] getAttachment(String idFile) {
        String filePath = "C:/Study/CSE422/Project/" + idFile;

        try {
            Path path = Path.of(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    } 

    @Override
    public String saveAttachment(byte[] byteArray,String idFile) {

        String saveDirectory = "C:/Study/CSE422/Project/";
        File directory = new File(saveDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filePath = saveDirectory + idFile + ".bin";

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(byteArray);

            System.out.println("Attachment saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idFile;
    }
    
}
