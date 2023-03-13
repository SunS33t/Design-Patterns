package com.company.DaoPattern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.company.FactoryPattern.Car;
import com.company.FactoryPattern.Exceptions.DuplicateModelNameException;

public class CarDao {

    public static Car getCarFromTextFile(String fileName) throws FileNotFoundException, IOException{
        Car resultCar = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");
                String name = parts[0];
                //int modelsCount = Integer.parseInt(parts[1]);
                resultCar = new Car(name,0);
                for(int i = 2; i < parts.length; i+=2){
                    String modelName = parts[i];
                    double price = Double.parseDouble(parts[i+1]);
                    resultCar.addModel(modelName, price);
                }
            }
        }
        catch(DuplicateModelNameException ex){

        }
        return resultCar;
    }

    public static void createTextFileFromCar(Car car){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/company/Assets/daoFiles/" + car.getMark() + "_info.txt"))){
            bw.write(car.getMark() + ";");
            bw.write(car.getModelsArraySize() + ";");
            String[] namesArr = car.getModelNames();
            double[] pirceArr = car.getModelPriceArray();
            for(int i = 0; i <car.getModelsArraySize(); i++){
                bw.write(namesArr[i] + ";" + pirceArr[i] + ";");
            }
            bw.flush();
            bw.close();
        }
        catch(Exception ex){

        }
    }

    public static Car getCarFromSerializeFile(String fileName){
        Car resultCar = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            resultCar = (Car) ois.readObject();
        } 
        catch(Exception ex){
            System.out.println("Error while reading object from file");
        }
        return resultCar;
    }

    public static void serializeCarIntoFile(Car car){
        try(
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/com/company/Assets/daoFiles/" + car.getMark() + "_info"))
            ){
            out.writeObject(car);
            out.close();
        } 
        catch(Exception ex){

        }
    }

}
