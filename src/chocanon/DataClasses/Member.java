/* 
    File: Member.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 2/13/2021
    Updated by: Jacob Bremiller
    Updated: 2/13/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: A class to model the member data coming from the database/
 */

package chocanon.DataClasses;


public class Member extends Person{
    //Data Attributes
    private int cardNumber = 0;
    
    //Constructor
    public Member(String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int cardNumber){
        super(firstName, lastName, streetAddress, city, state, zipCode);
        this.cardNumber = cardNumber;
    }
    
    //Getters
    public int getCardNumber(){
        return this.cardNumber;
    }
    

    @Override
    public String toString(){
        return ("First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Card Number: " + this.getCardNumber() + "\n");
    }
}
