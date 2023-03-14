package com.company.DaoPattern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import com.company.FactoryPattern.Vehicle;
import com.company.FactoryPattern.Factory.CarFactory;
import com.company.FactoryPattern.Factory.TransportFactory;

public class TextFileDAO implements DAOInterface {

    private TransportFactory factory = new CarFactory();

    public void setTransportFactory(TransportFactory tf){
        this.factory = tf;
    }

    public Vehicle readFromFile(String fileName) {
        Vehicle resultVehicle = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");
                String name = parts[0];
                resultVehicle = factory.createInstance(name, 0);
                for(int i = 2; i < parts.length; i+=2){
                    String modelName = parts[i];
                    double price = Double.parseDouble(parts[i+1]);
                    resultVehicle.addModel(modelName, price);
                }
            }
        }
        catch(Exception ex){

        }
        return resultVehicle;
    }

    public void writeInFile(Vehicle vehicle) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/company/Assets/daoFiles/" + vehicle.getMark() + "_info.txt"))){
            bw.write(vehicle.getMark() + ";");
            bw.write(vehicle.getModelsArraySize() + ";");
            String[] namesArr = vehicle.getModelNames();
            double[] pirceArr = vehicle.getModelPriceArray();
            for(int i = 0; i <vehicle.getModelsArraySize(); i++){
                bw.write(namesArr[i] + ";" + pirceArr[i] + ";");
            }
            bw.flush();
            bw.close();
        }
        catch(Exception ex){

        }
    }
    
}
