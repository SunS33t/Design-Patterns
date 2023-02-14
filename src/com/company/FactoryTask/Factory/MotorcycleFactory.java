package com.company.FactoryTask.Factory;

import com.company.FactoryTask.Motorcycle;
import com.company.FactoryTask.Vehicle;

public class MotorcycleFactory implements TransportFactory{

    public Vehicle createInstance(String name, int modelsCount) {
        return new Motorcycle(name,modelsCount);
    }
}
