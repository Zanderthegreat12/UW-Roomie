package roomieapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A collection of utility methods to help with parsing dbconn.properties.
 */
public class DBConnUtils {
  /**
   * Open and return a connection using dbconn.properties file
   *
   * @throws SQLException if connection to database fails
   * @throws IOException if opening dbconn.properties fails
   * @return connection to MySQL database
   */
  public static Connection openConnection() throws SQLException, IOException {
    // Connect to the database with the provided connection configuration
    Properties configProps = new Properties();
    configProps.load(new FileInputStream("dbconn.properties"));

    return getConn(configProps);
  }

  public static Connection openTestConnection() throws SQLException, IOException {
    // Connect to the database with the provided connection configuration
    Properties configProps = new Properties();
    configProps.load(new FileInputStream("dbconntest.properties"));

    return getConn(configProps);
  }

  private static Connection getConn(Properties configProps) throws SQLException, IOException {
    String serverURL = configProps.getProperty("roomieapp.server_url");
    String dbName = configProps.getProperty("roomieapp.database_name");
    String adminName = configProps.getProperty("roomieapp.username");
    String password = configProps.getProperty("roomieapp.password");

    String connectionUrl =
            String.format("jdbc:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
                    serverURL, dbName, adminName, password);
    Connection conn = DriverManager.getConnection(connectionUrl);

    // By default, set the transaction isolation level to serializable
    conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

    return conn;
  }
}
