package com.islam;

public class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        super(radius);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void draw() {
        System.out.print("Circle : ");
        super.draw();
    }
    public double calculatePerimeter(){
        return 2*3.14*radius;
    }
    public double calculateArea(){
        return 3.14*radius*radius;
    }

}
