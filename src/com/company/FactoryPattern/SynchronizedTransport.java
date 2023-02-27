package com.company.FactoryPattern;

import com.company.FactoryPattern.Exceptions.DuplicateModelNameException;
import com.company.FactoryPattern.Exceptions.NoSuchModelNameException;
import com.company.FactoryPattern.Vehicle;

public class SynchronizedTransport implements Vehicle {
    private Vehicle transport;

    public SynchronizedTransport(Vehicle vehicle){
        this.transport = vehicle;
    }
    @Override
    public synchronized String getMark() {
        return transport.getMark();
    }

    @Override
    public synchronized void setMark(String name) {
        transport.setMark(name);
    }

    @Override
    public synchronized void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setModelName(oldName,newName);
    }

    @Override
    public synchronized String[] getModelNames() {
        return transport.getModelNames();
    }

    @Override
    public synchronized double getPriceByName(String name) throws NoSuchModelNameException {
        return transport.getPriceByName(name);
    }

    @Override
    public synchronized void setPriceByName(String name, double price) throws NoSuchModelNameException {
        transport.setPriceByName(name,price);
    }

    @Override
    public synchronized double[] getModelPriceArray() {
        return transport.getModelPriceArray();
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException {
        transport.addModel(name,price);
    }

    @Override
    public synchronized void deleteModel(String name, double price) throws NoSuchModelNameException {
        transport.deleteModel(name, price);
    }

    @Override
    public synchronized int getModelsArraySize() {
        return transport.getModelsArraySize();
    }
}
