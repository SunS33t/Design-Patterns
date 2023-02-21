package com.company.FactoryPattern.Factory;

import com.company.FactoryPattern.Motorcycle;
import com.company.FactoryPattern.Vehicle;

public class MotorcycleFactory implements TransportFactory{

    public Vehicle createInstance(String name, int modelsCount) {
        return new Motorcycle(name,modelsCount);
    }
}
