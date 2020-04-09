package com.islam;

public class Shape {
    protected double[] sides;
    public Shape(double ... sides){
        this.sides = sides;
    }
    public void draw(){
        System.out.print("{");
        for (double node:sides) {
            System.out.print(node + " , ");
        }
        System.out.println("}");
    }

    public boolean equals(Shape shape){
        return shape.toString().equals(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (double node:sides) {
            sb.append(node).append(" , ");
        }
        sb.append("}\n");
        return sb.toString();
    }

}
