package com.company.ChainOfResponsobilityPattern;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.company.FactoryPattern.Vehicle;

public class InLineHandler implements ChainInterface {

    private ChainInterface nextHandler;

    public void setNextHandler(ChainInterface handler) {
        this.nextHandler = handler;
    }

    public void vehicleToFile(Vehicle vehicle) {
        if (vehicle.getModelsArraySize() <= 3) {
            try (
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("src/com/company/Assets/chainOfResponsobilityFiles/" +
                                    vehicle.getMark() + "_inLineInfo.txt"))) {
                                        
                writer.write(vehicle.getMark() + ";");
                String[] names = vehicle.getModelNames();
                double[] prices = vehicle.getModelPriceArray();
                for (int i = 0; i < vehicle.getModelsArraySize(); i++) {
                    writer.write(String.format("%s - %s;", names[i], prices[i]));
                }
                writer.flush();
                writer.close();
            } catch (Exception ex) {

            }
        } else if (this.nextHandler != null) {
            nextHandler.vehicleToFile(vehicle);
        }
    }
}
