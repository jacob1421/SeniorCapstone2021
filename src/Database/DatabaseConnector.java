package Database;

/* 
    File: DatabaseConnector.java
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
    Description: Contains the database connection function and the functions to pull data from the database.
 */

import java.sql.*;  

public class DatabaseConnector {
    //Attributes
    private String database_host = "";
    private int database_port = 0;
    private String database_user = "";
    private String database_password = "";
    private String database_name = "";
    private Connection databaseConn;
    
    //Track Open and Closed.
    boolean databaseConnectionOpen = false;
    
    
    public DatabaseConnector(String database_host, int database_port, String database_user, String database_password, String database_name){
        this.database_host = database_host;
        this.database_port = database_port;
        this.database_user = database_user;
        this.database_password = database_password;
        this.database_name = database_name;
    }
    
    public Connection getDatabaseConnection() throws SQLException, Exception{
        if(this.databaseConnectionOpen = true){
            throw new Exception("Close your database connection before opening a new one.");
        }
        this.databaseConnectionOpen = true;
        this.databaseConn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", this.database_host, this.database_port, this.database_name), this.database_user, this.database_password);
        return this.databaseConn;  
    }
    
    public void closeDatabaseConnection() throws SQLException{
        this.databaseConnectionOpen = false;
        this.databaseConn.close();
    }   
}

//    
//    public Member getMemberById(int member_id){
//        Member ret_member = null;
//        try (
//            Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", this.database_host, this.database_port, this.database_name), this.database_user, this.database_password);
//            Statement stmt = conn.createStatement();
//        ){
//            //Query
//            String strSelect = String.format("SELECT first_name, last_name, street_address, city, state, zip_code, card_number FROM chocanon_db.members WHERE member_id = %s LIMIT 1;", member_id);
//            //Execute Query
//            ResultSet rset = stmt.executeQuery(strSelect);
//            //Get Results
//            while (rset.next()) {
//                ret_member = new Member(rset.getString("first_name"), rset.getString("last_name"), rset.getString("street_address"), rset.getString("city"), rset.getString("state"), rset.getInt("zip_code"), rset.getInt("card_number"));
//            }
//            //Close database
//            conn.close();
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//        return ret_member;
//    }
//    
//    public Member getMemberByCardNumber(int card_number){
//        Member ret_member = null;
//        try (
//            Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", this.database_host, this.database_port, this.database_name), this.database_user, this.database_password);
//            Statement stmt = conn.createStatement();
//        ){
//            //Query
//            String strSelect = String.format("SELECT first_name, last_name, street_address, city, state, zip_code, card_number FROM chocanon_db.members WHERE card_number = %s LIMIT 1;", card_number);
//            //Execute Query
//            ResultSet rset = stmt.executeQuery(strSelect);
//            //Get Results
//            while (rset.next()) {
//                ret_member = new Member(rset.getString("first_name"), rset.getString("last_name"), rset.getString("street_address"), rset.getString("city"), rset.getString("state"), rset.getInt("zip_code"), rset.getInt("card_number"));
//            }
//            //Close database
//            conn.close();
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//        return ret_member;
//    }
//    
//    public Provider getProviderById(int provider_id){
//        Provider ret_provider = null;
//        try (
//            Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", this.database_host, this.database_port, this.database_name), this.database_user, this.database_password);
//            Statement stmt = conn.createStatement();
//        ){
//            //Query
//            String strSelect = String.format("SELECT first_name, last_name, street_address, city, state, zip_code, provider_number, providertypes.provider_type FROM chocanon_db.providers JOIN providertypes ON providers.provider_type_id = providertypes.provider_type_id WHERE provider_id = %s LIMIT 1;", provider_id);
//            //Execute Query
//            ResultSet rset = stmt.executeQuery(strSelect);
//            //Get Results
//            while (rset.next()) {
//                ret_provider = new Provider(rset.getString("first_name"), rset.getString("last_name"), rset.getString("street_address"), rset.getString("city"), rset.getString("state"), rset.getInt("zip_code"), rset.getInt("provider_number"), rset.getString("provider_type"));
//            }
//            //Close database
//            conn.close();
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//        
//        return ret_provider;
//    }
//    
//    public Service getServiceById(int service_id){
//        Service ret_service = null;
//        try (
//            Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", this.database_host, this.database_port, this.database_name), this.database_user, this.database_password);
//            Statement stmt = conn.createStatement();
//        ){
//            //Query
//            String strSelect = String.format("SELECT name, fee, code FROM chocanon_db.services WHERE service_id = %s LIMIT 1;", service_id);
//            //Execute Query
//            ResultSet rset = stmt.executeQuery(strSelect);
//            //Get Results
//            while (rset.next()) {
//                ret_service = new Service(rset.getString("name"), rset.getInt("code"), rset.getBigDecimal("fee"));
//            }
//            //Close database
//            conn.close();
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//        return ret_service;
//    }
//    
//    public ArrayList<Visit> getMemberVisitsById(int member_id) throws ParseException{
//        ArrayList<Visit> member_visits = new ArrayList<Visit>();
//        try (
//            Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", this.database_host, this.database_port, this.database_name), this.database_user, this.database_password);
//            Statement stmt = conn.createStatement();
//        ){
//            //Query
//            String strSelect = String.format("SELECT provider_id, member_id, service_id, visit_date, comment FROM chocanon_db.visits WHERE member_id = %s;", member_id);
//            //Execute Query
//            ResultSet rset = stmt.executeQuery(strSelect);
//            //Get Results
//            while (rset.next()) {
//                member_visits.add(
//                        new Visit(
//                                this.getProviderById(rset.getInt("provider_id")),
//                                this.getMemberById(rset.getInt("member_id")),
//                                this.getServiceById(rset.getInt("service_id")),
//                                rset.getString("visit_date"), 
//                                rset.getString("comment")
//                        )
//                );
//            }
//            //Close database
//            conn.close();
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//        
//        return member_visits;
//    }
//    
//}