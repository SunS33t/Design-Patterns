package com.company.FactoryTask.Factory;

import com.company.FactoryTask.Car;
import com.company.FactoryTask.Vehicle;

public class CarFactory implements TransportFactory{
    public Vehicle createInstance(String name, int modelsCount) {
        return new Car(name,modelsCount);
    }
}
