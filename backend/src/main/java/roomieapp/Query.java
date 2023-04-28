package roomieapp;

import org.apache.commons.lang3.NotImplementedException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Properties;

/**
 *  A collection of Query functions that can read and write data
 *  from/to the SQL database. This class can retrieve and update
 *  user information and compatibility.
 */
public class Query {

    // The connection manager
    // This is how Query will make queries to the SQL database
    private Connection conn;

    // query to get username and password of user
    private PreparedStatement getUserStmt;

    // query to get survey belonging to given user
    private PreparedStatement getSurveyStmt;

    // query to get match information of specified 2 users
    private PreparedStatement getMatchStmt;

    // query to get contact information belonging to given user
    private PreparedStatement getContactStmt;

    // query to add new user
    private PreparedStatement createUserStmt;

    // query to add a new survey belonging to specified user
    private PreparedStatement createSurveyStmt;

    // query to add new contact information belonging to specified user
    private PreparedStatement createContactStmt;

    // query to add new match information between 2 specified users
    private PreparedStatement createMatchStmt;

    // query to update survey of specified user
    private PreparedStatement updateSurveyStmt;

    // query to update contact info of specified user
    private PreparedStatement updateContactStmt;

    // query to update match info of specified 2 users
    private PreparedStatement updateMatchStmt;

    // query to get k number of top matches with given user
    private PreparedStatement getTopMatches;

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

        getSurveyStmt = conn.prepareStatement(
           "SELECT * " +
               "FROM Surveys " +
               "WHERE username = ?;"
        );

        getContactStmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Contact_Info " +
                "WHERE username = ?;"
        );

        getMatchStmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Matches " +
                "WHERE user1 = ? and user2 = ?;"
        );

        createUserStmt = conn.prepareStatement(
            "INSERT INTO Users " +
                "VALUES (?, ?);"
        );

        createSurveyStmt = conn.prepareStatement(
            "INSERT INTO Surveys " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?);"
        );

        createContactStmt = conn.prepareStatement(
            "INSERT INTO Contact_Info " +
                "VALUES(?, ?, ?, ?);"
        );

        createMatchStmt = conn.prepareStatement(
            "INSERT INTO Matches " +
                "VALUES(?, ?, ?, ?);"
        );

        updateSurveyStmt = conn.prepareStatement(
            "UPDATE Surveys " +
                "SET firstDorm = ?," +
                    "secondDorm = ?," +
                    "thirdDorm = ?," +
                    "roomType = ?," +
                    "genderInclusive = ?," +
                    "studentYear = ?," +
                    "roommateYear = ?," +
                    "drinkingPref = ?," +
                    "wakeTime = ?," +
                    "sleepTime = ?," +
                    "heavySleep = ?," +
                    "studentVert = ?," +
                    "roommateVert = ?," +
                    "studentFriends = ?," +
                    "roommateFriends = ?," +
                    "studentNeat = ?," +
                    "roommateNeat = ?," +
                    "hobbies = ? " +
                "WHERE username = ?;"
        );

        updateContactStmt = conn.prepareStatement(
            "UPDATE Contact_Info " +
                "SET email = ?," +
                    "phoneNumber = ?," +
                    "discord = ? " +
                "WHERE username = ?;"
        );

        updateMatchStmt = conn.prepareStatement(
            "UPDATE Matches " +
                "SET compatibility = ?," +
                    "matchStatus = ? " +
                "WHERE user1 = ? and user2 = ?;"
        );

        getTopMatches = conn.prepareStatement(
            "SELECT * " +
                "FROM Matches " +
                "WHERE user1 = ? or user2 = ? " +
                "LIMIT ?;"
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
    public void setMatch(Match matchInfo) {
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

    /**
     * Return topK users with the highest compatibility of parameter user in sorted order.
     * Ignores other users who've already matched with given user.
     * @param user the username of the user of interest
     * @param topK the number of possible matches to return.
     *             If there are less than topK results, return all possible matches
     * @return the usernames of the user's with highest compatibility of parameter user.
     *         user's with higher compatibility will be at beginning of list.
     *         user's with lower compatibility will be at the end of the list.
     * @throws IllegalArgumentException if user not in database
     */
    public List<String> getTopMatches(String user, int topK) {
        throw new NotImplementedException("");
    }
}
