package com.company;
/**
 * @author erfan omid seyed
 * this class has 4 fields and it's about student of a school
 * @since 2020-01-24
 */
public class Student {
    private String firstName;
    private String lastName;
    private int grade;
    private String Id;
    /**
     * Create a new student with a given name and ID number.
     * @param fName first name of student
     * @param lname last name of student
     * @param sID student ID */
    public Student(String fName, String lname, String sID){
        firstName=fName;lastName=lname;grade=0;Id=sID;
    }
/**
 * get the first name of student
 * @return firstName field
 */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get the grade
     * @return grade field
     */

    public int getGrade() {
        return grade;
    }

    /**
     * get the last name
     * @return lastName field
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * get the id
     * @return Id field
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param firstName set first name of student
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param grade set grade of student
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     *
     * @param id set id of student
     */
    public void setId(String id) {
        Id = id;
    }

    /**
     *
     * @param lastName set last name of student
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Print the student’s last name and ID number to the
     * output terminal.
     */
    public void print(){
        System.out.println("first name:"+ firstName +"last name" + lastName +"id:" + Id + "grade:"+ grade);
    }
}