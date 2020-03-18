package com.company;

/**
 * @author erfan omid seyed
 * this class has 5 fields and it's about student of a school
 * @since 2020-01-24
 */
public class lab {
    private Student[] students=new Student[1];
    private int avg;
    private String day;
    private int capacity;
    private int currentSize;

    public lab(int cap,String d){ }
    public lab(Student student,int avg,String day,int capacity,int currentSize){
        int index = students.length;
        students = new Student[index+1];
        students [index - 1] =student;
        this.avg=avg;
        this.capacity=capacity;
        this.currentSize = currentSize;
        this.day = day;
    }
    /**
     * this is method for lab class
     * @param std this is the first parametr
     */
    public void enrollStudent(Student std){
        if(currentSize < capacity){
            students[currentSize] = std;
            currentSize++;
        }else{
            System.out.println("Lab is full!!!");
        }
    }
    public void print(){}

    /**
     *
     * @return students field
     */
    public Student[] getStudents(){
       return students;
    }

    /**
     *
     * @return avg filed
     */
    public int getAvg() {
        return avg;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public String getDay() {
        return day;
    }

    /**
     *
     * @param avg setiing
     */
    public void setAvg(int avg) {
        this.avg = avg;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
