package com.example.demo;

public class Coordinate {
    private double x;
    private double y;
    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Coordinate(double x){
        this.x = x;
        this.y = CalculateFunction.calculate(x);
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
}
