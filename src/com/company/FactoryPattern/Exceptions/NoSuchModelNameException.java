package com.company.FactoryPattern.Exceptions;

public class NoSuchModelNameException extends Exception{
    public NoSuchModelNameException(String name){
        super("Model with name \"" + name + "\" was not found in models array");
    }
}
