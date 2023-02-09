package com.company.FactoryTask;

import java.util.Objects;

public class Motorcycle {
    private class Model{
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;
        public Model(){

        }
        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
    }
    String name = null;
    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }
    private int size = 0;

    public Motorcycle(String name, int modelCount){
        this.name = name;
        this.size = modelCount;
    }

    public void setModelName(String oldName, String newName){
        //no such model
        getModelByName(oldName).name = newName;
    }

    public String[] getModelNames(){
        String[] resArray = new String[size];
        Model pointer = head.next;
        int i = 0;
        while(pointer != head){
            resArray[i] = pointer.name;
            pointer = pointer.next;
            i++;
        }
        return resArray;
    }

    public double getPriceByName(String name){
        // no such model
        return getModelByName(name).price;
    }

    public void setPriceByName(String name, double price){
        //no such model
        getModelByName(name).price = price;
    }

    public double[] getModelPriceArray(){
        double[] resArray = new double[size];
        Model pointer = head.next;
        int i = 0;
        while(pointer != head){
            resArray[i] = pointer.price;
            pointer = pointer.next;
            i++;
        }
        return resArray;
    }

    public void addModel(String name, double price){
        Model newModel = new Model(name, price);
        head.prev.next = newModel;
        newModel.prev = head.prev;
        newModel.next = head;
        head.prev = newModel;
        this.size++;
    }

    public void deleteModel(String name, double price){
        // no such model
        Model modelToDelete = getModelByNameAndPrice(name,price);
        modelToDelete.next.prev = modelToDelete.prev;
        modelToDelete.prev.next = modelToDelete.next;
        modelToDelete.next = null;
        modelToDelete.prev = null;
        this.size--;
    }
    public int getSize(){
        return this.size;
    }

    private Model getModelByName(String name){
        // no such model
        Model pointer = head.next;
        while(pointer != head){
            if(Objects.equals(pointer.name, name))
                return pointer;
            pointer = pointer.next;
        }
        return null;
    }

    private Model getModelByNameAndPrice(String name, double price){
        // no such model
        Model pointer = head.next;
        while(pointer != head){
            if(Objects.equals(pointer.name, name) && pointer.price == price)
                return pointer;
            pointer = pointer.next;
        }
        return null;
    }


}
