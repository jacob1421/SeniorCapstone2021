/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanon.DataClasses;

/**
 *
 * @author jakeb
 */
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
    
}
