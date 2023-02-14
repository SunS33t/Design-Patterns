package com.company.FactoryTask.Factory;

import com.company.FactoryTask.Vehicle;

public interface TransportFactory {
    Vehicle createInstance(String name, int modelsCount);
}
