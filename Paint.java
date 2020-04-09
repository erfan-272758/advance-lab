package com.islam;

import java.util.ArrayList;

public class Paint {
    private ArrayList<Shape> shapes = new ArrayList<>();
    public void addShape(Shape shape){
        shapes.add(shape);
    }
    public void drawAll(){
        for (Shape s:shapes) {
            s.draw();
        }
    }

    public void printAll(){
        StringBuilder sb= new StringBuilder();
        for (Shape s:shapes) {
            sb.append(s.toString());
        }
        System.out.println(sb.toString());
    }
}
