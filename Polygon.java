package com.islam;

public class Polygon extends Shape {
    private double[] sides;
    public Polygon(double ... sides){
        super(sides);
        this.sides = sides;
    }

    public double[] getSides() {
        return sides;
    }
}
