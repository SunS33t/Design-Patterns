package com.company.ChainOfResponsobilityPattern;

import com.company.FactoryPattern.Vehicle;

public interface ChainInterface {
    public void vehicleToFile(Vehicle vehicle);
    public void setNextHandler(ChainInterface handler);
}
