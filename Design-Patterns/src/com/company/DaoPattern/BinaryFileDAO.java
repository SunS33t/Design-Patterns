package com.company.DaoPattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.company.FactoryPattern.Vehicle;

public class BinaryFileDAO implements DAOInterface{

    public Vehicle readFromFile(String fileName) {
        Vehicle resultVehicle = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            resultVehicle = (Vehicle) ois.readObject();
        } 
        catch(Exception ex){
            System.out.println("Error while reading object from file");
        }
        return resultVehicle;
    }

    public void writeInFile(Vehicle vehicle) {
        try(
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/com/company/Assets/daoFiles/" + vehicle.getMark() + "_info"))
            ){
            out.writeObject(vehicle);
            out.close();
        } 
        catch(Exception ex){

        }
    }
    
}
