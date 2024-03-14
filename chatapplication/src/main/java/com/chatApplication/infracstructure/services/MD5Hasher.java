// Source code is decompiled from a .class file using FernFlower decompiler.
package com.chatapplication.infracstructure.services;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chatapplication.usecases.adapters.Hasher;

public class MD5Hasher implements Hasher {
   public MD5Hasher() {
   }

   public String hash(String string) {
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte[] messageDigest = md.digest(string.getBytes());
         BigInteger no = new BigInteger(1, messageDigest);

         String hashText;
         for(hashText = no.toString(16); hashText.length() < 32; hashText = "0" + hashText) {
         }

         return hashText;
      } catch (NoSuchAlgorithmException var6) {
         throw new RuntimeException(var6);
      }
   }
}
