package com.company.FactoryPattern.Factory;

import com.company.FactoryPattern.Vehicle;

public interface TransportFactory {
    Vehicle createInstance(String name, int modelsCount);
}
