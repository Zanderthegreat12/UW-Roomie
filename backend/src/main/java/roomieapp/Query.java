package roomieapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 *  A collection of Query functions that can read and write data
 *  from/to the SQL database. This class can retrieve and update
 *  user information and compatibility.
 */
public class Query {

    // The connection manager
    // This is how Query will queries to the SQL database
    private Connection conn;

    // query to check if certain user exists
    // executing stmt return 1 if user exists, otherwise it returns 0
    private PreparedStatement checkUserStmt;

    /**
     * Initializes a connection with SQL database
     * and sets up a Query object to be able to send queries
     * to the SQL database
     */
    public Query() {
        try {
            this.conn = DBConnUtils.openConnection();
            this.prepareStatements();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Prepares all queries during object initialization to prevent SQL injections
    private void prepareStatements() throws SQLException{
        checkUserStmt = conn.prepareStatement(
           "SELECT COUNT(*) " +
               "FROM Users " +
               "WHERE username = ?;"
        );
    }

    /**
     * Clears all tables from SQL database
     */
    public void clearTables() {
        try {
            clearTable("User_Survey_Pairs");
            clearTable("User_Contact_Pairs");
            clearTable("Matches");
            clearTable("Users");
            clearTable("Surveys");
            clearTable("Contact_Info");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Clears SQL table with given tableName
    private void clearTable(String tableName) throws SQLException{
        conn.prepareStatement("DELETE FROM " + tableName + ";").executeUpdate();
    }

    public 
}
