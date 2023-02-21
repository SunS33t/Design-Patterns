package com.company.FactoryPattern;

import com.company.FactoryPattern.Exceptions.DuplicateModelNameException;
import com.company.FactoryPattern.Exceptions.NoSuchModelNameException;

public interface Vehicle {
    String getMark();
    void setMark(String name);
    void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    String[] getModelNames();
    double getPriceByName(String name) throws NoSuchModelNameException;
    void setPriceByName(String name, double price) throws NoSuchModelNameException;
    double[] getModelPriceArray();
    void addModel(String name, double price) throws DuplicateModelNameException;
    void deleteModel(String name, double price) throws NoSuchModelNameException;
    int getModelsArraySize();
}
