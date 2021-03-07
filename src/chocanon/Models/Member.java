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

    public Member(String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int cardNumber, String emailAddress, boolean activeMember){
        super(firstName, lastName, streetAddress, city, state, zipCode, emailAddress);
        this.cardNumber = cardNumber;
        this.activeMember = activeMember;
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
             String strSelect = String.format("SELECT member_id, first_name, last_name, street_address, city, state, zip_code, email_address, card_number, active_membership FROM chocanon_db.members WHERE (first_name LIKE('%s') OR last_name LIKE('%s') OR card_number LIKE('%s')) AND deleted = 0 LIMIT 25;", searchText, searchText, searchText);
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
    
    public static int deleteMemberByDatabaseId(int databaseId){
        int affectedRows = 0;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("UPDATE chocanon_db.members SET deleted = 1 WHERE member_id = %s", databaseId);
            Log.debug("Member", "Query: " + strSelect);
            //Execute Query
            affectedRows = stmt.executeUpdate(strSelect);
            if(affectedRows > 0){
                Log.debug("Member", affectedRows + " rows were affected.");
            }else{
                Log.debug("Member", "Delete From Database Failed..");
            }
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Member", ex.toString());
        }  
        return affectedRows;
    }
    
    public static int insertNewMember(Member mem){
        int affectedRows = 0;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("INSERT INTO chocanon_db.members(first_name, last_name, street_address, city, state, zip_code, email_address, card_number, active_membership) VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s')", mem.getFirstName(), mem.getLastName(), mem.getStreetAddress(), mem.getCity(), biTranslateState(mem.getState(), "ABBR"), mem.getZipCode(), mem.getEmailAddress(), mem.getCardNumber(), (mem.getMembershipStatus() ? 1 : 0));
            Log.debug("Member", "Query: " + strSelect);
            //Execute Query
            affectedRows = stmt.executeUpdate(strSelect);
            if(affectedRows > 0){
                Log.debug("Member", affectedRows + " rows were affected.");
            }else{
                Log.debug("Member", "Adding New Member To Database Failed..");
            }
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Member", ex.toString());
        }  
        return affectedRows;
    }
    
    public static int updateNewMember(Member mem){
        int affectedRows = 0;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("UPDATE chocanon_db.members SET first_name = '%s', last_name = '%s', street_address = '%s', city = '%s', state = '%s', zip_code = %s, email_address = '%s', card_number = %s, active_membership = %s WHERE member_id = %s;", mem.getFirstName(), mem.getLastName(), mem.getStreetAddress(), mem.getCity(), mem.getState(), mem.getZipCode(), mem.getEmailAddress(), mem.getCardNumber(), (mem.getMembershipStatus() ? 1 : 0), mem.getDatabaseId());
            Log.debug("Member", "Query: " + strSelect);
            //Execute Query
            affectedRows = stmt.executeUpdate(strSelect);
            if(affectedRows > 0){
                Log.debug("Member", affectedRows + " rows were affected.");
            }else{
                Log.debug("Member", "Updating Member In Database Failed..");
            }
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Member", ex.toString());
        }  
        return affectedRows;
    }
    
    public static boolean doesCardNumberExist(String cardNumber){
        boolean cardExists = false;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT count(*) as card_exist FROM chocanon_db.members WHERE card_number = %s", cardNumber);
            Log.debug("Member", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                cardExists = rset.getInt("card_exist") != 0;
            }  
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Member", ex.toString());
        }  
        return cardExists;
    }
    
    //Search function
    public static Member findMemberById(Member[] memArray, int targetDatabaseId){
        Member foundMem = null;
        for(int i = 0;i < memArray.length;i++){
            if(memArray[i].getDatabaseId() == targetDatabaseId){
                foundMem = memArray[i];
                break;
            }
        }
        return foundMem;
    }
    
    @Override
    public String toString(){
        return ("Database Id: " + this.getDatabaseId() + " First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Card Number: " + this.getCardNumber() + " Membership Status: " + this.getMembershipStatus() + "\n");
    }
}
