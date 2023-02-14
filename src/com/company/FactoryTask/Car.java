package com.company.FactoryTask;

import com.company.FactoryTask.Exceptions.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Car implements Vehicle, Cloneable {
    private class Model{
        private String modelName;
        private double price;

        public Model(String modelName,double price){
            this.modelName = modelName;
            this.price = price;
        }

        public String getModelName(){
            return this.modelName;
        }
        public void setModelName(String modelName){
            this.modelName = modelName;
        }
        public double getPrice(){
            return this.price;
        }
        public void setPrice(double price){
            this.price = price;
        }
    }
    private static final double DEFAULT_PRICE = 100;
    private String mark;
    private Model[] modelsArray;
    public Car(String markName, int arraySize){
        this.mark = markName;
        this.modelsArray = new Model[arraySize];
        for(int i = 0; i < arraySize; i++){
            modelsArray[i] = new Model("CarTestModel_" + (i + 1), DEFAULT_PRICE);
        }
    }
    public String getMark(){
        return this.mark;
    }
    public void setMark(String mark){
        this.mark = mark;
    }
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(indexOfModel(oldName) < 0){
            throw new NoSuchModelNameException(oldName);
        }
        if(indexOfModel(newName) > 0){
            throw new DuplicateModelNameException(newName);
        }
        Stream.of(modelsArray).filter(model -> Objects.equals(model.getModelName(), oldName)).findFirst().get().setModelName(newName);
    }
    public String[] getModelNames(){
        return  Arrays.stream(modelsArray).map(Model::getModelName).toArray(String[]::new);
    }
    public double getPriceByName(String name) throws NoSuchModelNameException {
        if(indexOfModel(name) < 0){
            throw new NoSuchModelNameException(name);
        }
        return Stream.of(modelsArray).filter(model -> Objects.equals(model.getModelName(), name)).findFirst().get().getPrice();
    }
    public void setPriceByName(String name, double price) throws NoSuchModelNameException {
        if(price < 0){
            throw new ModelPriceOutOfBoundsException();
        }
        if(indexOfModel(name) < 0){
            throw new NoSuchModelNameException(name);
        }
        Stream.of(modelsArray).filter(model -> Objects.equals(model.getModelName(), name)).findFirst().get().setPrice(price);
    }
    public double[] getModelPriceArray(){
        return Stream.of(modelsArray).mapToDouble(Model::getPrice).toArray();
    }
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if(price < 0){
            throw new ModelPriceOutOfBoundsException();
        }
        if(indexOfModel(name) > 0){
            throw new DuplicateModelNameException(name);
        }
        modelsArray = Arrays.copyOf(modelsArray,modelsArray.length + 1);
        modelsArray[modelsArray.length - 1] = new Model(name,price);
    }

    public void deleteModel(String name, double price) throws NoSuchModelNameException {
        if(indexOfModel(name,price) < 0){
            throw new NoSuchModelNameException(name);
        }
        int index = indexOfModel(name, price);
        System.arraycopy(modelsArray, index + 1, modelsArray, index, modelsArray.length - index - 1);
        modelsArray = Arrays.copyOf(modelsArray,modelsArray.length - 1);
    }
    public int getModelsArraySize(){
        return modelsArray.length;
    }
    private int indexOfModel(String name, double price){
        for (int i = 0; i < modelsArray.length; i++) {
            if(Objects.equals(modelsArray[i].getModelName(), name) && modelsArray[i].getPrice() == price)
                return i;
        }
        return -1;
    }
    private int indexOfModel(String name){
        for (int i = 0; i < modelsArray.length; i++) {
            if(Objects.equals(modelsArray[i].getModelName(), name))
                return i;
        }
        return -1;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Car result = (Car)super.clone();
        result.modelsArray = this.modelsArray.clone();
        for(int i = 0; i < getModelsArraySize(); i++){
            result.modelsArray[i] = new Model(modelsArray[i].modelName, modelsArray[i].price);
        }
        return result;
    }
}