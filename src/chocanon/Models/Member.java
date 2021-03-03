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
    Updated: 2/25/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: A class to model the member data coming from the database/
 */

package chocanon.Models;


public class Member extends Person{
    //Data Attributes
    private int cardNumber = 0; //9 Digit Card Number - Page 627
    private boolean activeMember = false;
    
    //Constructors
    public Member(){
    }
    
    public Member(String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int cardNumber, String emailAddress, boolean activeMember){
        super(firstName, lastName, streetAddress, city, state, zipCode, emailAddress);
        this.cardNumber = cardNumber;
        this.activeMember = activeMember;
    }
    
    //Getters
    public int getCardNumber(){
        return this.cardNumber;
    }
    
    public boolean getMembershipStatus(){
        return this.activeMember;
    }
    
    //Setters
    public void setCardNumber(int cardNumber){
        this.cardNumber = cardNumber;
    }
    
    public void setMembershipStatus(boolean membershipStatus){
        this.activeMember = membershipStatus;
    }
    
    @Override
    public String toString(){
        return ("First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Card Number: " + this.getCardNumber() + " Membership Status: " + this.getMembershipStatus() + "\n");
    }
}
