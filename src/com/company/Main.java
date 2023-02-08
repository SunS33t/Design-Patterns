package com.company;

import com.company.SingletonTask.Singleton;

public class Main {

    public static void main(String[] args) {
	    Singleton singleton = Singleton.getInstance();

	    System.out.println(singleton.getProperties().toString());

	    System.out.println(singleton.hashCode());
	    singleton = Singleton.getInstance();
        System.out.println(singleton.hashCode());
    }
}
