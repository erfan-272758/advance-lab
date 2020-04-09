package com.islam;

public class Rectangle extends Polygon {
    public Rectangle(double ... sides){
        super(sides);
    }
    public boolean IsSquare(){
        return getSides()[0] == getSides()[1] &&
                getSides()[1] == getSides()[2];
    }

    @Override
    public void draw() {
        System.out.print("Rectangle : ");
        super.draw();
    }
    public double calculatePerimeter(){
        return getSides()[0] + getSides()[1] +
                getSides()[2] + getSides()[3];
    }

    public double calculateArea(){
        return getSides()[0] * getSides()[1];
    }
}
