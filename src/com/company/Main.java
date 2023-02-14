package com.company;

import com.company.FactoryTask.Car;
import com.company.FactoryTask.Enums.TransportType;
import com.company.FactoryTask.Motorcycle;
import com.company.FactoryTask.Vehicle;
import com.company.FactoryTask.VehicleController;
import com.company.SingletonTask.Singleton;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
		//----------------Singleton start----------------------
		System.out.println("\u001B[32m" + "--------Singleton-----------" + "\u001B[0m");

	    Singleton singleton = Singleton.getInstance();
	    System.out.println(singleton.getProperties().toString());
	    System.out.println("Singleton object hash is: " + singleton.hashCode());
	    singleton = Singleton.getInstance();
        System.out.println("Singleton object hash after get on more instance is: " + singleton.hashCode());

		System.out.println("\u001B[32m" + "-------------------------------" + "\u001B[0m");
		//-----------------Singleton end-----------------------

		//-----------------Factory start-----------------------
			//Car methods check
		System.out.println("\u001B[32m" + "--------Factory-----------" + "\u001B[0m");
		Car car = new Car("Landa",2);
		System.out.println("Mark name is " + car.getMark());
		car.addModel("Priora",500);
		car.addModel("Granta",603);
		car.addModel("Kalina",550);
		car.addModel("Vesta",700);
		car.addModel("2108",200);
		car.setMark("Lada");
		System.out.println("Updated mark name is " + car.getMark());
		System.out.println("Models names array is " + Arrays.toString(car.getModelNames()));
		System.out.println("Priora price is: " +  car.getPriceByName("Priora"));
		car.setPriceByName("Priora",555);
		System.out.println("Priora new price is: " + car.getPriceByName("Priora"));
		System.out.println("Price array is: " + Arrays.toString(car.getModelPriceArray()));
		System.out.println("Car array size is: " + car.getModelsArraySize());
		car.deleteModel("Priora", 555);
		System.out.println("Models names array after delete priora is " + Arrays.toString(car.getModelNames()));
		car.setModelName("2108","vosmyorka");
		System.out.println("Models array after change model name: " + Arrays.toString(car.getModelNames()));
			//Car methods check end
			//Motorcycle methods check
		System.out.println("-------------------------------");
		Motorcycle motorcycle = new Motorcycle("Yamaha",2);
		System.out.println("Models names array is " + Arrays.toString(motorcycle.getModelNames()));
		motorcycle.addModel("test3",400);
		System.out.println("Updated models names array is " + Arrays.toString(motorcycle.getModelNames()));
		System.out.println("Test3 price is: " +  motorcycle.getPriceByName("test3"));
		motorcycle.setPriceByName("test3",555);
		System.out.println("Test3 new price is: " + motorcycle.getPriceByName("test3"));
		System.out.println("Price array is: " + Arrays.toString(motorcycle.getModelPriceArray()));
		System.out.println("Motorcycle array size is: " + motorcycle.getModelsArraySize());
		motorcycle.deleteModel("test3", 555);
		System.out.println("Models names array after delete test3 is " + Arrays.toString(motorcycle.getModelNames()));
		motorcycle.setModelName("MotorcycleTestModel_1","TestModel_0");
		System.out.println("Models array after change model name: " + Arrays.toString(motorcycle.getModelNames()));
		System.out.println("-------------------------------");
			//Motorcycle methods check end
			//Class with static methods check
		VehicleController.printModelsPrices(motorcycle);
		VehicleController.printModelsNames(motorcycle);
		VehicleController.printModelsNamesAndPrices(motorcycle);
		System.out.println("-------------------------------");
			//Class with static methods check end
			//Factory method check
		VehicleController.setTransportFactory(TransportType.CAR);
		Vehicle probablyACar = VehicleController.createInstance("BMW",3);
		System.out.println(probablyACar.getClass().toString());

		VehicleController.setTransportFactory(TransportType.MOTORCYCLE);
		Vehicle probablyAMotorcycle = VehicleController.createInstance("Harley-Davidson",3);
		System.out.println(probablyAMotorcycle.getClass().toString());
			//Factory method check end
		System.out.println("\u001B[32m" + "-------------------------------" + "\u001B[0m");
		//------------------Factory end------------------------
		//-----------------Prototype start---------------------
		System.out.println("\u001B[32m" + "--------Prototype-----------" + "\u001B[0m");
		Car carClone = (Car) car.clone();
		car.setModelName("Kalina","Kalimalina");
		System.out.println("Car: ");
		VehicleController.printModelsNamesAndPrices(car);
		System.out.println("CarClone: ");
		VehicleController.printModelsNamesAndPrices(carClone);

		Motorcycle motoClone = (Motorcycle) motorcycle.clone();
		motorcycle.setModelName("MotorcycleTestModel_2","moto_2");
		System.out.println("Moto: ");
		VehicleController.printModelsNamesAndPrices(motorcycle);
		System.out.println("Cloned moto: ");
		VehicleController.printModelsNamesAndPrices(motoClone);

		System.out.println("\u001B[32m" + "-------------------------------" + "\u001B[0m");
		//-----------------Prototype end-----------------------
    }
}
