/* 
    File: Person.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 2/13/2021
    Updated by: Jacob Bremiller
    Updated: 3/3/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: Base class to model the data for member and provider
 */

package chocanon.Models;

public abstract class Person {
    //Data Attributes
    private String firstName = "";
    private String lastName = "";
    private String streetAddress = "";
    private String city = "";
    private String state = "";
    private int zipCode = 0;
    private String emailAddress = "";
    
    //Constructors
    public Person(){
    }
    
    public Person(String firstName, String lastName, String streetAddress, String city, String state, int zipCode, String emailAddress){
       this.firstName = firstName;
       this.lastName = lastName;
       this.streetAddress = streetAddress;
       this.city = city;
       this.state = state;
       this.zipCode = zipCode;
       this.emailAddress = emailAddress;
    }
    
    //Getters
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getStreetAddress(){
        return this.streetAddress;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public String getState(){
        return this.state;
    }
    
    public int getZipCode(){
        return this.zipCode;
    }
    
    public String getEmailAddress(){
        return this.emailAddress;
    }
    
    
    //Setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setStreetAddress(String streetAddress){
        this.streetAddress = streetAddress;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public void setZipCode(int zipCode){
        this.zipCode = zipCode;
    }
    
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    //Static Methods
    public static String biTranslateState(String currentStateRepresentation, String returnRepresentation){
        String returnState = null;
        String[][] statesMap = new String[][]{
            {"Alabama", "AL"},
            {"Alaska","AK"},
            {"Arizona","AZ"},
            {"Arkansas","AR"},
            {"California","CA"},
            {"Colorado","CO"},
            {"Connecticut","CT"},
            {"Delaware","DE"},
            {"Florida","FL"},
            {"Georgia","GA"},
            {"Hawaii","HI"},
            {"Idaho","ID"},
            {"Illinois","IL"},
            {"Indiana","IN"},
            {"Iowa","IA"},
            {"Kansas","KS"},
            {"Kentucky","KY"},
            {"Louisiana","LA"},
            {"Maine","ME"},
            {"Maryland","MD"},
            {"Massachusetts","MA"},
            {"Michigan","MI"},
            {"Minnesota","MN"},
            {"Mississippi","MS"},
            {"Missouri","MO"},
            {"Montana","MT"},
            {"Nebraska","NE"},
            {"Nevada","NV"},
            {"New Hampshire","NH"},
            {"New Jersey","NJ"},
            {"New Mexico","NM"},
            {"New York","NY"},
            {"North Carolina","NC"},
            {"North Dakota","ND"},
            {"Ohio","OH"},
            {"Oklahoma","OK"},
            {"Oregon","OR"},
            {"Pennsylvania","PA"},
            {"Rhode Island","RI"},
            {"South Carolina","SC"},
            {"South Dakota","SD"},
            {"Tennessee","TN"},
            {"Texas","TX"},
            {"Utah","UT"},
            {"Vermont","VT"},
            {"Virginia","VA"},
            {"Washington","WA"},
            {"West Virginia","WV"},
            {"Wisconsin","WI"},
            {"Wyoming","WY"}
        };
        
        switch(returnRepresentation){
            //Takes the full state and returns the abbreviation name
            case "ABBR":
                for(int i = 0;i < statesMap.length;i++){
                    if(currentStateRepresentation.equals(statesMap[i][0])){
                        returnState = statesMap[i][1];
                        break;
                    }
                }
            break;
            //Takes the abbreviation state and returns the full state name
            case "FULL":
                for(int i = 0;i < statesMap.length;i++){
                    if(currentStateRepresentation.equals(statesMap[i][1])){
                        returnState = statesMap[i][0];
                        break;
                    }
                }   
            break;
            default:
                returnState = "";
            break;
        }
        
        return returnState;
    }
}
