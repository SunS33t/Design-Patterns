package com.company;

import com.company.AdapterPattern.Adapter;
import com.company.DaoPattern.CarDao;
import com.company.FacadePattern.TrafficController;
import com.company.FactoryPattern.Car;
import com.company.FactoryPattern.Enums.TransportType;
import com.company.FactoryPattern.Motorcycle;
import com.company.FactoryPattern.Vehicle;
import com.company.FactoryPattern.VehicleController;
import com.company.SingletonPattern.Singleton;

import java.io.OutputStream;
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

  		//Lab2
		System.out.println("\u001B[32m" + "--------Adapter-----------" + "\u001B[0m");
		OutputStream os = Adapter.stringArrayToOutputStream(new String[] {"den","men","sen"});
		System.out.println(os);
		System.out.println(Arrays.toString(Adapter.outputStreamToStringArray(os)));
		System.out.println("\u001B[32m" + "-------------------------------" + "\u001B[0m");

		System.out.println("\u001B[32m" + "--------Facade-----------" + "\u001B[0m");


		// TrafficController tc = new TrafficController();
		// tc.start();

		Car carFromFile = CarDao.getCarFromTextFile("src/com/company/Assets/daoFiles/carInfo.txt");
		System.out.println(carFromFile.getMark());
		VehicleController.printModelsNamesAndPrices(carFromFile);

		CarDao.serializeCarIntoFile(carFromFile);
		carFromFile = CarDao.getCarFromSerializeFile("src/com/company/Assets/daoFiles/Subaru_info");
		VehicleController.printModelsNamesAndPrices(carFromFile);
		CarDao.createTextFileFromCar(car);
    }
}
