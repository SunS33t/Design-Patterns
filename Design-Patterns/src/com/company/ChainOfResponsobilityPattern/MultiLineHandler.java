package com.company.ChainOfResponsobilityPattern;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.company.FactoryPattern.Vehicle;

//"src/com/company/Assets/chainOfResponsobilityFiles/" + vehicle.getMark() +"_inLineInfo.txt"
public class MultiLineHandler implements ChainInterface {

    private ChainInterface nextHandler;

    public void setNextHandler(ChainInterface handler) {
        this.nextHandler = handler;
    }

    public void vehicleToFile(Vehicle vehicle) {
        if (vehicle.getModelsArraySize() > 3) {
            try (
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("src/com/company/Assets/chainOfResponsobilityFiles/" +
                                    vehicle.getMark() + "_multiLineInfo.txt"))) {
                writer.write(vehicle.getMark());
                writer.newLine();
                String[] names = vehicle.getModelNames();
                double[] prices = vehicle.getModelPriceArray();
                for(int i = 0; i < vehicle.getModelsArraySize(); i++){
                    writer.write(String.format("%s - %s", names[i], prices[i]));
                    writer.newLine();
                }
                writer.flush();
                writer.close();

            } catch (Exception ex) {

            }
        }
        else if(nextHandler != null){
            nextHandler.vehicleToFile(vehicle);
        }
    }
}