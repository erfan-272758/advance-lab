package com.islam;

public class Triangle extends Polygon {
    public Triangle(double ... sides){
        super(sides);
    }

    public boolean IsEquilateral(){
        return getSides()[0] == getSides()[1] &&
                getSides()[1] == getSides()[2];
    }

    @Override
    public void draw() {
        System.out.print("Triangle : ");
        super.draw();
    }
    public double calculatePerimeter(){
        return getSides()[0] + getSides()[1] + getSides()[2];
    }
    public double calculateArea(){
        double p = calculatePerimeter()/2;
        return Math.sqrt(p*(p-getSides()[0])*(p-getSides()[1])*(p-getSides()[2]));
    }

}
