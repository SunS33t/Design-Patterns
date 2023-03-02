package com.company.AdapterPattern;

import java.io.*;

public class Adapter {
    public static OutputStream stringArrayToOutputStream(String[] stringArray) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(stringArray);
        objectOutputStream.close();
        return byteArrayOutputStream;
    }

    public static String[] outputStreamToStringArray(OutputStream outputStream) throws IOException, ClassNotFoundException {
        byte[] bytes = ((ByteArrayOutputStream) outputStream).toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        String[] stringArray = (String[]) objectInputStream.readObject();
        objectInputStream.close();
        return stringArray;
    }
}
