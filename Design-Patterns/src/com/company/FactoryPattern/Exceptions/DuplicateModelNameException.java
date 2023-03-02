package com.company.FactoryPattern.Exceptions;

public class DuplicateModelNameException extends Exception{
    public DuplicateModelNameException(String name){
        super("Model with name \"" + name + "\" already exist");
    }
}
