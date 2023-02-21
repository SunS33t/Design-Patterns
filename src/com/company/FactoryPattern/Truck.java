package com.company.FactoryPattern;

import com.company.FactoryPattern.Exceptions.DuplicateModelNameException;
import com.company.FactoryPattern.Exceptions.NoSuchModelNameException;

public class Truck implements Vehicle {



    @Override
    public String getMark() {
        return null;
    }

    @Override
    public void setMark(String name) {

    }

    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {

    }

    @Override
    public String[] getModelNames() {
        return new String[0];
    }

    @Override
    public double getPriceByName(String name) throws NoSuchModelNameException {
        return 0;
    }

    @Override
    public void setPriceByName(String name, double price) throws NoSuchModelNameException {

    }

    @Override
    public double[] getModelPriceArray() {
        return new double[0];
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {

    }

    @Override
    public void deleteModel(String name, double price) throws NoSuchModelNameException {

    }

    @Override
    public int getModelsArraySize() {
        return 0;
    }
}
