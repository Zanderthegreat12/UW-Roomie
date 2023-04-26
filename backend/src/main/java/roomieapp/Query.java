package roomieapp;

import org.apache.commons.lang3.NotImplementedException;

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

    // query to get username and password of user
    private PreparedStatement getUserStmt;

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
        getUserStmt = conn.prepareStatement(
           "SELECT * " +
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clears SQL table with given tableName
    private void clearTable(String tableName) throws SQLException{
        conn.prepareStatement("DELETE FROM " + tableName + ";").executeUpdate();
    }

    /**
     * Creates a new user with given username and password
     * @param username user's identifier
     * @param password a string to verify user's identity
     * @return return false if another user already has username, otherwise
     *         return true if login was successful
     */
    public boolean createUser(String username, String password) {
        throw new NotImplementedException("");
    }

    /**
     * Checks if login is successful based on username and password
     * @param username user's identifier
     * @param password a string to verify user's identity
     * @return return true if login was successful, otherwise return false
     */
    public boolean login(String username, String password) {
        throw new NotImplementedException("");
    }

    /**
     * Returns the contact information of the given user
     * @param username user's identifier
     * @return contact info of given user.
     *         If contact info of given user not found, return null.
     * @throws IllegalArgumentException if user not found in database
     */
    public ContactInfo getContactInfo(String username) {
        throw new NotImplementedException("");
    }

    /**
     * Returns the survey answers of given user used for determining compatibility
     * between this user and other users
     * @param username user's identifier
     * @return survey answers of given user.
     *         If survey answers not found under user, return null.
     * @throws IllegalArgumentException if user not found in database
     */
    public Survey getSurvey(String username) {
        throw new NotImplementedException("");
    }

    /**
     * Sets contact information for userContactInfo.username to be
     * the contact information provided in contacts
     * @param userContactInfo updates contact information with info
     *                        provided in contacts
     * @throws IllegalArgumentException if userContactInfo has invalid values
     */
    public void setContactInfo(ContactInfo userContactInfo) {
        throw new NotImplementedException("");
    }

    /**
     * Sets survey answers for user userContactInfo.username
     * with the new survey answers provided in userSurvey
     * @param userSurvey updates a survey with the information provided in userSurvey
     * @throws IllegalArgumentException if userSurvey has invalid values
     */
    public void setSurvey(Survey userSurvey) {
        throw new NotImplementedException("");
    }

    /**
     * Sets the match info between matchInfo.user1 and matchInfo.user2
     * to have matchInfo.compatibility and matchInfo.matchStatus
     * @param matchInfo contains match information to be added/updated
     * @throws IllegalArgumentException if matchInfo has invalid values
     */
    public void updateMatch(Match matchInfo) {
        throw new NotImplementedException("");
    }

    /**
     * Find and return the match information between user1 and user2
     * @param user1 user 1 identifier
     * @param user2 user 2 identifier
     * @return match info between user1 and user2.
     *         Return null if match info between user1 and user2 doesn't exist
     * @throws IllegalArgumentException if either user1 or user2 not in database
     */
    public Match getMatch(String user1, String user2) {
        throw new NotImplementedException("");
    }
}
