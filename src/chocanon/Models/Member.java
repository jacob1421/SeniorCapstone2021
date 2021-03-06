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
    Updated: 3/3/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: A class to model the member data coming from the database/
 */

package chocanon.Models;

import Database.DatabaseConnector;
import Logger.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Member extends Person{
    //Data Attributes
    private int cardNumber = 0; //9 Digit Card Number - Page 627
    private boolean activeMember = false;
    private int databaseId = 0;
    
    //Constructors
    public Member(){
    }
    
    public Member(int databaseId, String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int cardNumber, String emailAddress, boolean activeMember){
        super(firstName, lastName, streetAddress, city, state, zipCode, emailAddress);
        this.cardNumber = cardNumber;
        this.activeMember = activeMember;
        this.databaseId = databaseId;
    }
    
    //Getters
    public int getCardNumber(){
        return this.cardNumber;
    }
    
    public boolean getMembershipStatus(){
        return this.activeMember;
    }
    
    public int getDatabaseId(){
        return this.databaseId;
    }
    
    //Setters
    public void setCardNumber(int cardNumber){
        this.cardNumber = cardNumber;
    }
    
    public void setMembershipStatus(boolean membershipStatus){
        this.activeMember = membershipStatus;
    }
    public void setDatabaseId(int databaseId){
        this.databaseId = databaseId;
    }
    
    //Static getters
    public static Member getMemberByCardNumber(int cardNumber){
        Member memberFound = null;
        /* Do the database functionality to get a member from the database by card number - 9 digit*/
        return memberFound;
    }
    
    public static Member[] getMembersByNameOrCardNumber(String searchText){
        Log.debug("Member", "Search Text: " + searchText);
        ArrayList<Member> membersFound = new ArrayList<>(); 
         try {
             //Create Database Connection
             DatabaseConnector dbConn = new DatabaseConnector();
             Connection conn = dbConn.getDatabaseConnection();
             Statement stmt = conn.createStatement();
             //Query
             searchText = "%" + searchText + "%";
             String strSelect = String.format("SELECT member_id, first_name, last_name, street_address, city, state, zip_code, email_address, card_number, active_membership FROM chocanon_db.members WHERE first_name LIKE('%s') OR last_name LIKE('%s') OR card_number LIKE('%s');", searchText, searchText, searchText);
             Log.debug("Member", "Query: " + strSelect);
             //Execute Query
             ResultSet rset = stmt.executeQuery(strSelect);
             //Get Results
             while (rset.next()) {
                 //Build the member
                 Member m = new Member(rset.getInt("member_id"), rset.getString("first_name"), rset.getString("last_name"), rset.getString("street_address"), rset.getString("city"), rset.getString("state"), rset.getInt("zip_code"), rset.getInt("card_number"), rset.getString("email_address"), rset.getBoolean("active_membership"));
                 Log.debug("Member", "Found Member: \n" + m.toString());
                 //Add Member
                 membersFound.add(m);
             }

             if(membersFound.size() > 0){
                 Log.debug("Member", "Found " + membersFound.size() + " Members");
             }else{
                 Log.debug("Member", "No Members Found With Search Text: " + searchText);
             }

             //Close database
             dbConn.closeDatabaseConnection();
         } catch (Exception ex) {
             Log.error("Member", ex.toString());
         }  

         return Arrays.stream(membersFound.toArray()).toArray(Member[]::new);
    }
    
    public static void deleteMemberByDatabaseId(){
    
    }
    
    @Override
    public String toString(){
        return ("Database Id: " + this.getDatabaseId() + " First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Card Number: " + this.getCardNumber() + " Membership Status: " + this.getMembershipStatus() + "\n");
    }
}
