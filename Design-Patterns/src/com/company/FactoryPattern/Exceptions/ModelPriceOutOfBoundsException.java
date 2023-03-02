package com.company.FactoryPattern.Exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    public ModelPriceOutOfBoundsException(){
        super("Attempt to enter a negative price");
    }
}
