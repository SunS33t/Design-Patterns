package com.company;

import com.company.FactoryTask.Car;
import com.company.FactoryTask.Motorcycle;
import com.company.SingletonTask.Singleton;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
		//----------------Singleton start----------------------
	    Singleton singleton = Singleton.getInstance();

	    System.out.println(singleton.getProperties().toString());

	    System.out.println("Singleton object hash is: " + singleton.hashCode());
	    singleton = Singleton.getInstance();
        System.out.println("Singleton object hash after get on more instance is: " + singleton.hashCode());
		System.out.println("-------------------------------");

		//-----------------Singleton end-----------------------
		//-----------------Factory start-----------------------
			//Car methods check
		Car car = new Car("Landa",2);
		System.out.println("Mark name is " + car.getMark());
		car.addModel("Priora",500);
		car.addModel("Granta",600);
		car.addModel("Kalina",550);
		car.addModel("Vesta",700);
		car.addModel("2108",200);
		car.setMark("Lada");
		System.out.println("Updated mark name is " + car.getMark());
		System.out.println("Models names array is " + Arrays.toString(car.getModelNamesArray()));
		System.out.println("Priora price is: " +  car.getPriceByModelName("Priora"));
		car.setPriceByModelName("Priora",555);
		System.out.println("Priora new price is: " + car.getPriceByModelName("Priora"));
		System.out.println("Price array is: " + Arrays.toString(car.getModelPriceArray()));
		System.out.println("Car array size is: " + car.getModelsArraySize());
		car.deleteModel("Priora", 555);
		System.out.println("Models names array after delete priora is " + Arrays.toString(car.getModelNamesArray()));
		car.setModelName("2108","vosmyorka");
		System.out.println("Models array after change model name: " + Arrays.toString(car.getModelNamesArray()));
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
		System.out.println("Motorcycle array size is: " + motorcycle.getSize());
		motorcycle.deleteModel("test3", 555);
		System.out.println("Models names array after delete test3 is " + Arrays.toString(motorcycle.getModelNames()));
		motorcycle.setModelName("TestModel_1","TestModel_0");
		System.out.println("Models array after change model name: " + Arrays.toString(motorcycle.getModelNames()));
		System.out.println("-------------------------------");
			//Motorcycle methods check end

		//------------------Factory end------------------------
    }
}
