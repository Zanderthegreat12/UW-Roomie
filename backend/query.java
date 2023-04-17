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

    public void createTables() {

    }

}