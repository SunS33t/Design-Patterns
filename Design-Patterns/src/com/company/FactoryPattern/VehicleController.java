package com.company.FactoryPattern;

import com.company.FactoryPattern.Factory.CarFactory;
import com.company.FactoryPattern.Factory.MotorcycleFactory;
import com.company.FactoryPattern.Factory.TransportFactory;
import com.company.FactoryPattern.Enums.TransportType;

import java.util.Arrays;

public class VehicleController {

    private static TransportFactory factory = new CarFactory();

    public synchronized Vehicle synchronizedTransport(Vehicle v){
        return new SynchronizedTransport(v);
    }

    public static void setTransportFactory(TransportType type){
        if(type == TransportType.CAR){
            factory = new CarFactory();
        }
        else{
            factory = new MotorcycleFactory();
        }
    }

    public static void setTransportFactory(TransportFactory _factory){
        factory = _factory;
    }
    public static Vehicle createInstance(String name, int modelsCount){
        return factory.createInstance(name,modelsCount);
    }
    public static double getAveragePrice(Vehicle m){
        double[] arr = m.getModelPriceArray();
        return Arrays.stream(arr).sum() / m.getModelsArraySize();
    }
    public static void printModelsNames(Vehicle m){
        String[] arr =  m.getModelNames();
        Arrays.stream(arr).forEach(System.out::println);
    }
    public static void printModelsPrices(Vehicle m){
        double[] arr =  m.getModelPriceArray();
        Arrays.stream(arr).forEach(System.out::println);
    }
    public static void printModelsNamesAndPrices(Vehicle m){
        double[] arrP =  m.getModelPriceArray();
        String[] arrN =  m.getModelNames();
        for(int i = 0; i < arrN.length; i++){
            System.out.println(arrN[i] + " - " + arrP[i]);
        }
    }
}
