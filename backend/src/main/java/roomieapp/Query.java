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
    private PreparedStatement getTopMatchesStmt;

    // query to update only compatibility of a match
    private PreparedStatement updateCompatibilityStmt;

    // query to update only matchStatus of a match
    private PreparedStatement updateMatchStatusStmt;

    // query to get all surveys
    private PreparedStatement getAllSurveysStmt;

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

        getTopMatchesStmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Matches " +
                "WHERE (user1 = ? or user2 = ?) and matchStatus = 0 " +
                "ORDER BY compatibility DESC " +
                "LIMIT ?;"
        );

        updateCompatibilityStmt = conn.prepareStatement(
            "UPDATE Matches " +
                "SET compatibility = ? " +
                "WHERE user1 = ? and user2 = ?;"
        );

        updateMatchStatusStmt = conn.prepareStatement(
            "UPDATE Matches " +
                "SET matchStatus = ? " +
                "WHERE user1 = ? and user2 = ?;"
        );

        getAllSurveysStmt = conn.prepareStatement(
                "SELECT * FROM Surveys;"
        );
    }

    /**
     * Clears all tables from SQL database
     */
    public void clearTables() {
        try {
            clearTable("Matches");
            clearTable("Surveys");
            clearTable("Contact_Info");
            clearTable("Users");
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
        try {
            // check if username already exists
            if(userExists(username)) {
                return false;
            }

            // username doesn't exist, so create the new user and return true
            createUserStmt.setString(1, username);
            createUserStmt.setBytes(2, PasswordUtils.hashPassword(password));
            createUserStmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if login is successful based on username and password
     * @param username user's identifier
     * @param password a string to verify user's identity
     * @return return true if login was successful, otherwise return false
     */
    public boolean login(String username, String password) {
        try {
            getUserStmt.setString(1, username);
            ResultSet user = getUserStmt.executeQuery();

            // if username incorrect, return false
            if(!user.next()) {
                return false;
            }

            // if password matches, return true, otherwise return false
            boolean correctLogin = PasswordUtils.plaintextMatchesHash(
                    password, user.getBytes(2));
            user.close();
            return correctLogin;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the contact information of the given user
     * @param username user's identifier
     * @return contact info of given user.
     *         If contact info of given user not found, return null.
     * @throws IllegalArgumentException if user not found in database
     */
    public ContactInfo getContactInfo(String username) {
        try {
            // if username not found, throw exception
            if(!userExists(username)) {
                throw new IllegalArgumentException();
            }

            // if user's contact info not in database, return null
            getContactStmt.setString(1, username);
            ResultSet contact = getContactStmt.executeQuery();
            if(!contact.next()) {
                return null;
            }

            // return user's contact info
            ContactInfo usersContact = storeContactInfo(contact);
            contact.close();
            return usersContact;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
        try {
            // if username not found, throw exception
            if(!userExists(username)) {
                throw new IllegalArgumentException();
            }

            // if user's survey not in database, return null
            getSurveyStmt.setString(1, username);
            ResultSet survey = getSurveyStmt.executeQuery();
            if(!survey.next()) {
                return null;
            }

            // return user's survey answers
            Survey usersSurvey = storeSurvey(survey);
            survey.close();
            return usersSurvey;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Sets contact information for userContactInfo.username to be
     * the contact information provided in contacts
     * @param userContactInfo updates contact information with info
     *                        provided in contacts
     * @throws IllegalArgumentException if userContactInfo has invalid values
     */
    public void setContactInfo(ContactInfo userContactInfo) {
        try {
            String username = userContactInfo.username;

            // if username not found, throw exception
            if(!userExists(username)) {
                throw new IllegalArgumentException();
            }

            // if user's contactInfo already there, update their info
            // else Create new contactInfo for user
            getContactStmt.setString(1, username);
            ResultSet contact = getContactStmt.executeQuery();
            if(contact.next()) {
                updateContactStmt.setString(1, userContactInfo.email);
                updateContactStmt.setLong(2, userContactInfo.phoneNumber);
                updateContactStmt.setString(3, userContactInfo.discord);
                updateContactStmt.setString(4, username);
                updateContactStmt.execute();
            } else {
                createContactStmt.setString(1, username);
                createContactStmt.setString(2, userContactInfo.email);
                createContactStmt.setLong(3, userContactInfo.phoneNumber);
                createContactStmt.setString(4, userContactInfo.discord);
                createContactStmt.execute();
            }
            contact.close();

        } catch (SQLException e) {
            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 530) {
                throw new IllegalArgumentException();
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets survey answers for user userContactInfo.username
     * with the new survey answers provided in userSurvey
     * @param userSurvey updates a survey with the information provided in userSurvey
     * @throws IllegalArgumentException if userSurvey has invalid values
     */
    public void setSurvey(Survey userSurvey) {
        try {
            String username = userSurvey.username;

            // if username not found, throw exception
            if(!userExists(username)) {
                throw new IllegalArgumentException();
            }

            // if user's survey already there, update their info
            // else Create new survey for user
            getSurveyStmt.setString(1, username);
            ResultSet survey = getSurveyStmt.executeQuery();
            if(survey.next()) {
                updateSurveyStmt.setString(1, userSurvey.firstDorm);
                updateSurveyStmt.setString(2, userSurvey.secondDorm);
                updateSurveyStmt.setString(3, userSurvey.thirdDorm);
                updateSurveyStmt.setInt(4, userSurvey.roomType);
                updateSurveyStmt.setInt(5, userSurvey.genderInclusive);
                updateSurveyStmt.setInt(6, userSurvey.studentYear);
                updateSurveyStmt.setInt(7, userSurvey.roommateYear);
                updateSurveyStmt.setInt(8, userSurvey.drinkingPref);
                updateSurveyStmt.setInt(9, userSurvey.wakeTime);
                updateSurveyStmt.setInt(10, userSurvey.sleepTime);
                updateSurveyStmt.setInt(11, userSurvey.heavySleep);
                updateSurveyStmt.setInt(12, userSurvey.studentVert);
                updateSurveyStmt.setInt(13, userSurvey.roommateVert);
                updateSurveyStmt.setInt(14, userSurvey.studentFriends);
                updateSurveyStmt.setInt(15, userSurvey.roommateFriends);
                updateSurveyStmt.setInt(16, userSurvey.studentNeat);
                updateSurveyStmt.setInt(17, userSurvey.roommateNeat);
                updateSurveyStmt.setString(18, userSurvey.hobbies);
                updateSurveyStmt.setString(19, username);
                updateSurveyStmt.execute();
            } else {
                createSurveyStmt.setString(1, username);
                createSurveyStmt.setString(2, userSurvey.firstDorm);
                createSurveyStmt.setString(3, userSurvey.secondDorm);
                createSurveyStmt.setString(4, userSurvey.thirdDorm);
                createSurveyStmt.setInt(5, userSurvey.roomType);
                createSurveyStmt.setInt(6, userSurvey.genderInclusive);
                createSurveyStmt.setInt(7, userSurvey.studentYear);
                createSurveyStmt.setInt(8, userSurvey.roommateYear);
                createSurveyStmt.setInt(9, userSurvey.drinkingPref);
                createSurveyStmt.setInt(10, userSurvey.wakeTime);
                createSurveyStmt.setInt(11, userSurvey.sleepTime);
                createSurveyStmt.setInt(12, userSurvey.heavySleep);
                createSurveyStmt.setInt(13, userSurvey.studentVert);
                createSurveyStmt.setInt(14, userSurvey.roommateVert);
                createSurveyStmt.setInt(15, userSurvey.studentFriends);
                createSurveyStmt.setInt(16, userSurvey.roommateFriends);
                createSurveyStmt.setInt(17, userSurvey.studentNeat);
                createSurveyStmt.setInt(18, userSurvey.roommateNeat);
                createSurveyStmt.setString(19, userSurvey.hobbies);
                createSurveyStmt.execute();
            }
            survey.close();

        } catch (SQLException e) {
            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 530) {
                throw new IllegalArgumentException();
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the match info between matchInfo.user1 and matchInfo.user2
     * to have matchInfo.compatibility and matchInfo.matchStatus
     * @param matchInfo contains match information to be added/updated
     * @throws IllegalArgumentException if matchInfo has invalid values
     */
    public void setMatch(Match matchInfo) {
        try {
            // if users don't exist, throw exception
            if(!userExists(matchInfo.user1) || !userExists(matchInfo.user2)) {
                throw new IllegalArgumentException();
            }

            // if user's match info already there, update their info
            // else Create new match for users
            getMatchStmt.setString(1, matchInfo.user1);
            getMatchStmt.setString(2, matchInfo.user2);
            ResultSet match = getMatchStmt.executeQuery();
            if(match.next()) {
                updateMatchStmt.setFloat(1, matchInfo.compatibility);
                updateMatchStmt.setInt(2, matchInfo.matchStatus);
                updateMatchStmt.setString(3, matchInfo.user1);
                updateMatchStmt.setString(4, matchInfo.user2);
                updateMatchStmt.execute();
            } else {
                createMatchStmt.setString(1, matchInfo.user1);
                createMatchStmt.setString(2, matchInfo.user2);
                createMatchStmt.setFloat(3, matchInfo.compatibility);
                createMatchStmt.setInt(4, matchInfo.matchStatus);
                createMatchStmt.execute();
            }
            match.close();

        } catch (SQLException e) {
            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 530) {
                throw new IllegalArgumentException();
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Find and return the match information between user1 and user2
     * @param username1 user 1 identifier
     * @param username2 user 2 identifier
     * @return match info between user1 and user2.
     *         Return null if match info between user1 and user2 doesn't exist
     * @throws IllegalArgumentException if either user1 or user2 not in database
     */
    public Match getMatch(String username1, String username2) {
        if(username1.compareTo(username2) >= 0) {
            String temp = username1;
            username1 = username2;
            username2 = temp;
        }
        try {
            // if users don't exist, throw exception
            if(!userExists(username1) || !userExists(username2)) {
                throw new IllegalArgumentException();
            }

            // if users' match not in database, return null
            getMatchStmt.setString(1, username1);
            getMatchStmt.setString(2, username2);
            ResultSet match = getMatchStmt.executeQuery();
            if(!match.next()) {
                return null;
            }

            // return users' match info
            Match usersMatch = storeMatch(match);
            match.close();
            return usersMatch;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return topK users with the highest compatibility of parameter user in sorted order.
     * Ignores other users who've already matched with given user.
     * @param username the identifier of the user of interest
     * @param topK the number of possible matches to return.
     *             If there are less than topK results, return all possible matches
     * @return the usernames of the user's with highest compatibility between them
     *         and parameter user.
     *         user's with higher compatibility will be at beginning of list.
     *         user's with lower compatibility will be at the end of the list.
     * @throws IllegalArgumentException if user not in database
     */
    public List<String> getTopMatches(String username, int topK) {
        try {
            List<String> topMatches = new LinkedList<String>();

            // throw exception if user not in database
            if(!userExists(username)) {
                throw new IllegalArgumentException();
            }

            // return up to topK matches
            getTopMatchesStmt.setString(1, username);
            getTopMatchesStmt.setString(2, username);
            getTopMatchesStmt.setInt(3, topK);
            ResultSet currMatch = getTopMatchesStmt.executeQuery();
            while(currMatch.next()) {
                if(currMatch.getString(1).equals(username)) {
                    topMatches.add(currMatch.getString(2));
                } else {
                    topMatches.add(currMatch.getString(1));
                }
            }
            return topMatches;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * updates compatibility rating with newCompatibility for 2 users named
     * user1 and user2.
     * @param username1 identifier for username1
     * @param username2 identifier for username2
     * @param newCompatibility the updated compatibility for the 2 users
     * @throws IllegalArgumentException if users or match not found in database
     *         or if newCompatibility not between 0 and 100
     */
    public void updateCompatibility(String username1, String username2,
                                    float newCompatibility) {
        if(username1.compareTo(username2) >= 0) {
            String temp = username1;
            username1 = username2;
            username2 = temp;
        }
        try {
            if(!userExists(username1) || !userExists(username2) ||
                    !matchExists(username1, username2)) {
                throw new IllegalArgumentException();
            }

            updateCompatibilityStmt.setFloat(1, newCompatibility);
            updateCompatibilityStmt.setString(2, username1);
            updateCompatibilityStmt.setString(3, username2);
            updateCompatibilityStmt.execute();

        } catch (SQLException e) {
            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 530) {
                throw new IllegalArgumentException();
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * updates match status with newMatchStatus for 2 users named
     * username1 and username2.
     * @param username1 identifier for user1
     * @param username2 identifier for user2
     * @param newMatchStatus the updated compatibility for the 2 users
     * @throws IllegalArgumentException if users or match not found in database
     *         or if newMatchStatus not between 0 and 3
     */
    public void updateMatchStatus(String username1, String username2, int newMatchStatus) {
        if(username1.compareTo(username2) >= 0) {
            String temp = username1;
            username1 = username2;
            username2 = temp;
        }
        try {
            // if user1, user2, or match not found, throw exception
            if(!userExists(username1) || !userExists(username2) ||
                    !matchExists(username1, username2)) {
                throw new IllegalArgumentException();
            }

            updateMatchStatusStmt.setFloat(1, newMatchStatus);
            updateMatchStatusStmt.setString(2, username1);
            updateMatchStatusStmt.setString(3, username2);
            updateMatchStatusStmt.execute();

        } catch (SQLException e) {
            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 530) {
                throw new IllegalArgumentException();
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get a list of survey answers of all users in database.
     * This is used to determine compatibility scores between 2 users.
     * @return list of survey answers of all users in database.
     *      If there are no surveys, return an empty list
     */
    public List<Survey> getAllSurveys() {
        try {
            List<Survey> surveys = new LinkedList<>();
            ResultSet currSurvey = getAllSurveysStmt.executeQuery();
            while(currSurvey.next()) {
                Survey survey = storeSurvey(currSurvey);
                surveys.add(survey);
            }
            return surveys;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // check if user with given username exists
    private boolean userExists(String username) throws SQLException {
        getUserStmt.setString(1, username);
        ResultSet user = getUserStmt.executeQuery();
        boolean userExists = user.next();
        user.close();
        return userExists;
    }

    // checks if match with the 2 given users exist
    // assumes username1 < username2
    private boolean matchExists(String username1, String username2) throws SQLException {
        getMatchStmt.setString(1, username1);
        getMatchStmt.setString(2, username2);
        ResultSet match = getMatchStmt.executeQuery();
        boolean matchExists = match.next();
        match.close();
        return matchExists;
    }

    // checks if survey with given user exists
    private boolean surveyExists(String username) throws SQLException {
        getSurveyStmt.setString(1, username);
        ResultSet survey = getSurveyStmt.executeQuery();
        boolean surveyExists = survey.next();
        survey.close();
        return surveyExists;
    }

    // checks if contact info with given user exists
    private boolean contactExists(String username) throws SQLException {
        getContactStmt.setString(1, username);
        ResultSet contact = getContactStmt.executeQuery();
        boolean contactExists = contact.next();
        contact.close();
        return contactExists;
    }

    // take SQL match data, and store in Java Match object
    private Match storeMatch(ResultSet match) throws SQLException {
        return new Match(
                match.getString(1), // user1
                match.getString(2), // user2
                match.getFloat(3), // compatibility
                match.getInt(4) // matchStatus
        );
    }

    // take SQL contactInfo, and store in Java ContactInfo object
    private ContactInfo storeContactInfo(ResultSet contact) throws SQLException {
        return new ContactInfo(
                contact.getString(1), // username
                contact.getString(2), // email
                contact.getLong(3), // phone number
                contact.getString(4) // discord
        );
    }

    // take SQL survey data, and store in Java Survey object
    private Survey storeSurvey(ResultSet survey) throws SQLException {
        return new Survey(
                survey.getString(1), // username
                survey.getString(2), // firstDorm
                survey.getString(3), // secondDorm
                survey.getString(4), // thirdDorm
                survey.getInt(5), // roomType
                survey.getInt(6), // genderInclusive
                survey.getInt(7), // studentYear
                survey.getInt(8), // roommateYear
                survey.getInt(9), // drinkingPref
                survey.getInt(10), // wakeTime
                survey.getInt(11), // sleepTime
                survey.getInt(12), // heavySleep
                survey.getInt(13), // studentVert
                survey.getInt(14), // roommateVert
                survey.getInt(15), // studentFriends
                survey.getInt(16), // roommateFriends
                survey.getInt(17), // studentNeat
                survey.getInt(18), // roommateNeat
                survey.getString(19) // hobbies
        );
    }
}
