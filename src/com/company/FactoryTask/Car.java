package com.company.FactoryTask;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    public String getMark(){
        return this.mark;
    }

    public void setMark(String mark){
        this.mark = mark;
    }

    public String[] getModelNamesArray(){
        return  (String[])Stream.of(modelsArray).map(model -> model.getModelName()).toArray();
    }

    public int getPriceByModelName(String name){
        return Stream.of(modelsArray).filter(model -> model.getModelName() == name).findFirst().get().getPrice();
    }

    public void setPriceByModelName(String name, int price){
        Stream.of(modelsArray).filter(model -> model.getModelName() == name).findFirst().get().setPrice(price);
    }

    public int[] getModelPriceArray(){
        return Stream.of(modelsArray).mapToInt(model -> model.getPrice()).toArray();
    }

    public void addModel(String name, int price){
       modelsArray = Arrays.copyOf(modelsArray,modelsArray.length + 1);
       modelsArray[modelsArray.length - 1] = new Model(name,price);
    }
    public void deleteModel(String name, int price){
        int index = getModelNamesArray().toString().indexOf(name);

    }
}
