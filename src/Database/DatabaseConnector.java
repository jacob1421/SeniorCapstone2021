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

import Logger.Log;
import java.sql.*;  

public class DatabaseConnector {
    //Attributes
    final private String database_host = "localhost";
    final private int database_port = 3306;
    final private String database_user = "chocanon_manager";
    final private String database_password = "SomeHardPassword1234";
    final private String database_name = "chocanon_db";
    private Connection databaseConn;
    
    //Date Queries To SQL MUST BE YYYY-MM-DD
    
    public Connection getDatabaseConnection() throws SQLException, Exception{
        Log.info("DatabaseConnector", "Checking if the database connection is closed");
        if(this.databaseConn != null){
            if(this.databaseConn.isClosed() != true){
                Log.error("DatabaseConnector", "Database connection is opened while another is trying to be opened!");
                throw new Exception("Close your database connection before opening a new one.");
            }
        }
        try {
            this.databaseConn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", this.database_host, this.database_port, this.database_name), this.database_user, this.database_password);
            Log.debug("DatabaseConnector", "SQL Connection to database established!");
        }catch (SQLException e) {
            Log.error("DatabaseConnector", "SQL Connection Failed!");
            e.printStackTrace();
        }

        return this.databaseConn;  
    }
    
    public void closeDatabaseConnection() throws SQLException{
        Log.info("DatabaseConnector", "Database connection closed!");
        this.databaseConn.close();
    }   
}