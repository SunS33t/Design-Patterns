package com.company.FactoryPattern.Factory;

import com.company.FactoryPattern.Car;
import com.company.FactoryPattern.Vehicle;

public class CarFactory implements TransportFactory{
    public Vehicle createInstance(String name, int modelsCount) {
        return new Car(name,modelsCount);
    }
}
