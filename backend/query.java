package roomieapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Query {

    private Connection conn;

    public Query() {
        this.conn = DBConnUtils.openConnection();
    }

    public void clearTables() {
        try {
            clearTable("Users");
            clearTable("Surveys");
            clearTable("Contact_Info");
            clearTable("User_Survey_Pairs");
            clearTable("User_Contact_Pairs");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clearTable(String tableName) throws SQLException{
        conn.prepareStatement("DELETE FROM " + tableName + ";").executeUpdate();
    }

    public addUser

}