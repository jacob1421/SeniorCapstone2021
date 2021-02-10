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
public class Provider extends Person{
    //Data Attributes
    private int providerNumber = 0;
    private String providerType = "";
    
    //Constructor
    public Provider(String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int providerNumber, String providerType){
        super(firstName, lastName, streetAddress, city, state, zipCode);
        this.providerNumber = providerNumber;
        this.providerType = providerType;
    }
    
    //Getters
    public int getProviderNumber(){
        return this.providerNumber;
    }
    
    public String getProviderType(){
        return this.providerType;
    }
    
}