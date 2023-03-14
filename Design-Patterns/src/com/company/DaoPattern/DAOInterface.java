package com.company.DaoPattern;

import com.company.FactoryPattern.Vehicle;

public interface DAOInterface {
    public Vehicle readFromFile(String fileName);
    public void writeInFile(Vehicle vehicle);
}