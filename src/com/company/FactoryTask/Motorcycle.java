package com.company.FactoryTask;

import com.company.FactoryTask.Exceptions.DuplicateModelNameException;
import com.company.FactoryTask.Exceptions.ModelPriceOutOfBoundsException;
import com.company.FactoryTask.Exceptions.NoSuchModelNameException;

import java.util.Objects;

public class Motorcycle implements Vehicle {
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
    private static final double DEFAULT_PRICE = 100;
    private String mark = null;
    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }
    private int size = 0;

    public String getMark(){
        return this.mark;
    }
    public void setMark(String mark){
        this.mark = mark;
    }

    public Motorcycle(String mark, int modelCount){
        this.mark = mark;
        for(int i = 0; i < modelCount; i++){
            try{
                addModel("MotorcycleTestModel_" + (i + 1), DEFAULT_PRICE);
            }
            catch (DuplicateModelNameException e){
                e.printStackTrace();
            }
        }
        this.size = modelCount;
    }

    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(getModelByName(oldName) == null){
            throw new NoSuchModelNameException(oldName);
        }
        if(getModelByName(newName) != null){
            throw new DuplicateModelNameException(newName);
        }
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

    public double getPriceByName(String name) throws NoSuchModelNameException {
        if(getModelByName(name) == null){
            throw new NoSuchModelNameException(name);
        }
        return getModelByName(name).price;
    }

    public void setPriceByName(String name, double price) throws NoSuchModelNameException {
       if(price < 0){
           throw new ModelPriceOutOfBoundsException();
       }
       if(getModelByName(name) == null){
           throw new NoSuchModelNameException(name);
       }
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

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if(price < 0){
            throw new ModelPriceOutOfBoundsException();
        }
        if(getModelByName(name) != null) {
            throw new DuplicateModelNameException(name);
        }
        Model newModel = new Model(name, price);
        head.prev.next = newModel;
        newModel.prev = head.prev;
        newModel.next = head;
        head.prev = newModel;
        this.size++;
    }

    public void deleteModel(String name, double price) throws NoSuchModelNameException {
        Model modelToDelete = getModelByNameAndPrice(name,price);
        if(modelToDelete == null){
            throw new NoSuchModelNameException(name);
        }
        modelToDelete.next.prev = modelToDelete.prev;
        modelToDelete.prev.next = modelToDelete.next;
        modelToDelete.next = null;
        modelToDelete.prev = null;
        this.size--;
    }
    public int getModelsArraySize(){
        return this.size;
    }

    private Model getModelByName(String name){
        Model pointer = head.next;
        while(pointer != head){
            if(Objects.equals(pointer.name, name))
                return pointer;
            pointer = pointer.next;
        }
        return null;
    }

    private Model getModelByNameAndPrice(String name, double price){
        Model pointer = head.next;
        while(pointer != head){
            if(Objects.equals(pointer.name, name) && pointer.price == price)
                return pointer;
            pointer = pointer.next;
        }
        return null;
    }
    @Override
    public Object clone() {
        Motorcycle result = null;
        try{
            result = (Motorcycle) super.clone();
            result.head = new Model();
            result.head.next = result.head;
            result.head.prev = result.head;
            for(Model m = head.next; m != head; m = m.next){
                Model add = new Model();
                add.name = m.name;
                add.price = m.price;
                add.prev = result.head.prev;
                result.head.prev.next = add;
                add.next = result.head;
                result.head.prev = add;
            }
            return result;
        }
        catch (CloneNotSupportedException e ){}
        return result;
    }
}
