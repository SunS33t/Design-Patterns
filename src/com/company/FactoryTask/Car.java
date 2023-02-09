package com.company.FactoryTask;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Car {
    private class Model{
        private String modelName;
        private int price;

        public Model(String modelName,int price){
            this.modelName = modelName;
            this.price = price;
        }

        public String getModelName(){
            return this.modelName;
        }
        public void setModelName(String modelName){
            this.modelName = modelName;
        }
        public int getPrice(){
            return this.price;
        }
        public void setPrice(int price){
            this.price = price;
        }
    }
    private String mark;
    private Model[] modelsArray;
    public Car(String markName, int arraySize){
        this.mark = markName;
        this.modelsArray = new Model[arraySize];
    }
    public String getMark(){
        return this.mark;
    }
    public void setMark(String mark){
        this.mark = mark;
    }
    public void setModelName(String oldName, String newName){
        Stream.of(modelsArray).filter(model -> Objects.equals(model.getModelName(), oldName)).findFirst().get().setModelName(newName);
    }
    public String[] getModelNamesArray(){
        return  Arrays.stream(modelsArray).map(Model::getModelName).toArray(String[]::new);
    }
    public int getPriceByModelName(String name){
        return Stream.of(modelsArray).filter(model -> Objects.equals(model.getModelName(), name)).findFirst().get().getPrice();
    }
    public void setPriceByModelName(String name, int price){
        Stream.of(modelsArray).filter(model -> Objects.equals(model.getModelName(), name)).findFirst().get().setPrice(price);
    }
    public int[] getModelPriceArray(){
        return Stream.of(modelsArray).mapToInt(Model::getPrice).toArray();
    }
    public void addModel(String name, int price){
       modelsArray = Arrays.copyOf(modelsArray,modelsArray.length + 1);
       modelsArray[modelsArray.length - 1] = new Model(name,price);
    }
    public void deleteModel(String name, int price) throws Exception {
        int index = indexOfModel(name, price);
        if(index < 0){
            throw new Exception();
        }
        Model[] resultArray =  new Model[modelsArray.length - 1];
        System.arraycopy(modelsArray, 0, resultArray, 0, index);
        System.arraycopy(modelsArray,index + 1,resultArray,index,modelsArray.length - index - 1);
        this.modelsArray = resultArray;
    }
    public int getModelsArraySize(){
        return modelsArray.length;
    }
    private int indexOfModel(String name, int price){
        for (int i = 0; i < modelsArray.length; i++) {
            if(Objects.equals(modelsArray[i].getModelName(), name) && modelsArray[i].getPrice() == price)
                return i;
        }
        return -1;
    }
}
