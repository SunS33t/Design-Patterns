package com.company.SingletonPattern;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Singleton {
    private static Singleton _singleton_instance = null;
    private static Properties singletonData;

    private Singleton(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/com/company/config.properties")) {
            prop.load(input);
            singletonData = prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static synchronized Singleton getInstance(){
        if(_singleton_instance == null)
            _singleton_instance = new Singleton();
        return _singleton_instance;
    }
    public Properties getProperties(){
        return singletonData;
    }
}
