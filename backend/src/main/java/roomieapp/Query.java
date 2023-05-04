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

    /**
     * Initializes a connection with SQL database
     * and sets up a Query object to be able to send queries
     * to the SQL database
     */
    public Query() {
        try {
            this.conn = DBConnUtils.openConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // query to get username and password of user
    private PreparedStatement getUserStmt(String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Users " +
                "WHERE username = ?;"
        );
        stmt.setString(1, username);
        return stmt;
    }

    // query to get survey belonging to given user
    private PreparedStatement getSurveyStmt(String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Surveys " +
                "WHERE username = ?;"
        );
        stmt.setString(1, username);
        return stmt;
    }

    // query to get contact information belonging to given user
    private PreparedStatement getContactStmt(String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Contact_Info " +
                "WHERE username = ?;"
        );
        stmt.setString(1, username);
        return stmt;
    }

    // query to get match information of specified 2 users
    private PreparedStatement getMatchStmt(String user1, String user2) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Matches " +
                "WHERE user1 = ? and user2 = ?;"
        );
        stmt.setString(1, user1);
        stmt.setString(2, user2);
        return stmt;
    }

    // query to add new user
    private PreparedStatement createUserStmt(String username, byte[] password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO Users " +
                "VALUES (?, ?);"
        );
        stmt.setString(1, username);
        stmt.setBytes(2, password);
        return stmt;
    }

    // query to add a new survey belonging to specified user
    private PreparedStatement createSurveyStmt(Survey survey) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO Surveys " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?);"
        );
        stmt.setString(1, survey.username);
        stmt.setString(2, survey.firstDorm);
        stmt.setString(3, survey.secondDorm);
        stmt.setString(4, survey.thirdDorm);
        stmt.setInt(5, survey.roomType);
        stmt.setInt(6, survey.genderInclusive);
        stmt.setInt(7, survey.studentYear);
        stmt.setInt(8, survey.roommateYear);
        stmt.setInt(9, survey.drinkingPref);
        stmt.setInt(10, survey.wakeTime);
        stmt.setInt(11, survey.sleepTime);
        stmt.setInt(12, survey.heavySleep);
        stmt.setInt(13, survey.studentVert);
        stmt.setInt(14, survey.roommateVert);
        stmt.setInt(15, survey.studentFriends);
        stmt.setInt(16, survey.roommateFriends);
        stmt.setInt(17, survey.studentNeat);
        stmt.setInt(18, survey.roommateNeat);
        stmt.setString(19, survey.hobbies);
        return stmt;
    }

    // query to add new contact information belonging to specified user
    private PreparedStatement createContactStmt(ContactInfo contact) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO Contact_Info " +
                "VALUES(?, ?, ?, ?);"
        );
        stmt.setString(1, contact.username);
        stmt.setString(2, contact.email);
        stmt.setLong(3, contact.phoneNumber);
        stmt.setString(4, contact.discord);
        return stmt;
    }

    // query to add new match information between 2 specified users
    private PreparedStatement createMatchStmt(Match match) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO Matches " +
                "VALUES(?, ?, ?, ?);"
        );
        stmt.setString(1, match.user1);
        stmt.setString(2, match.user2);
        stmt.setFloat(3, match.compatibility);
        stmt.setInt(4, match.matchStatus);
        return stmt;
    }

    // query to update survey of specified user
    private PreparedStatement updateSurveyStmt(Survey survey) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
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
        stmt.setString(1, survey.firstDorm);
        stmt.setString(2, survey.secondDorm);
        stmt.setString(3, survey.thirdDorm);
        stmt.setInt(4, survey.roomType);
        stmt.setInt(5, survey.genderInclusive);
        stmt.setInt(6, survey.studentYear);
        stmt.setInt(7, survey.roommateYear);
        stmt.setInt(8, survey.drinkingPref);
        stmt.setInt(9, survey.wakeTime);
        stmt.setInt(10, survey.sleepTime);
        stmt.setInt(11, survey.heavySleep);
        stmt.setInt(12, survey.studentVert);
        stmt.setInt(13, survey.roommateVert);
        stmt.setInt(14, survey.studentFriends);
        stmt.setInt(15, survey.roommateFriends);
        stmt.setInt(16, survey.studentNeat);
        stmt.setInt(17, survey.roommateNeat);
        stmt.setString(18, survey.hobbies);
        stmt.setString(19, survey.username);
        return stmt;
    }

    // query to update contact info of specified user
    private PreparedStatement updateContactStmt(ContactInfo contact) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE Contact_Info " +
                "SET email = ?," +
                    "phoneNumber = ?," +
                    "discord = ? " +
                "WHERE username = ?;"
        );
        stmt.setString(1, contact.email);
        stmt.setLong(2, contact.phoneNumber);
        stmt.setString(3, contact.discord);
        stmt.setString(4, contact.username);
        return stmt;
    }

    // query to update match info of specified 2 users
    private PreparedStatement updateMatchStmt(Match match) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE Matches " +
                "SET compatibility = ?," +
                    "matchStatus = ? " +
                "WHERE user1 = ? and user2 = ?;"
        );
        stmt.setFloat(1, match.compatibility);
        stmt.setInt(2, match.matchStatus);
        stmt.setString(3, match.user1);
        stmt.setString(4, match.user2);
        return stmt;
    }

    // query to get k number of top matches with given user
    private PreparedStatement getTopMatchesStmt(
            String username, int topK) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * " +
                "FROM Matches " +
                "WHERE (user1 = ? or user2 = ?) and matchStatus = 0 " +
                "ORDER BY compatibility DESC " +
                "LIMIT ?;"
        );
        stmt.setString(1, username);
        stmt.setString(2, username);
        stmt.setInt(3, topK);
        return stmt;
    }

    // query to update only compatibility of a match
    private PreparedStatement updateCompatibilityStmt(
            String user1, String user2, float compatibility) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE Matches " +
                "SET compatibility = ? " +
                "WHERE user1 = ? and user2 = ?;"
        );
        stmt.setFloat(1, compatibility);
        stmt.setString(2, user1);
        stmt.setString(3, user2);
        return stmt;
    }

    // query to update only matchStatus of a match
    private PreparedStatement updateMatchStatusStmt(
            String user1, String user2, int matchStatus) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE Matches " +
                "SET matchStatus = ? " +
                "WHERE user1 = ? and user2 = ?;"
        );
        stmt.setFloat(1, matchStatus);
        stmt.setString(2, user1);
        stmt.setString(3, user2);
        return stmt;
    }

    // query to get all surveys
    private PreparedStatement getAllSurveysStmt() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Surveys;");
        return stmt;
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
            conn.commit();
        } catch (Exception e) {
            //e.printStackTrace();
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
                conn.commit();
                return false;
            }

            // username doesn't exist, so create the new user and return true
            PreparedStatement createUserStmt = createUserStmt(username,
                    PasswordUtils.hashPassword(password));
            createUserStmt.executeUpdate();
            conn.commit();
            return true;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {}
            return createUser(username, password);
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
            PreparedStatement getUserStmt = getUserStmt(username);
            ResultSet user = getUserStmt.executeQuery();

            // if username incorrect, return false
            if(!user.next()) {
                conn.commit();
                return false;
            }

            // if password matches, return true, otherwise return false
            boolean correctLogin = PasswordUtils.plaintextMatchesHash(
                    password, user.getBytes(2));
            user.close();
            conn.commit();
            return correctLogin;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {}
            return login(username, password);
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
                conn.commit();
                throw new IllegalArgumentException();
            }

            // if user's contact info not in database, return null
            PreparedStatement getContactStmt = getContactStmt(username);
            ResultSet contact = getContactStmt.executeQuery();
            if(!contact.next()) {
                conn.commit();
                return null;
            }

            // return user's contact info
            ContactInfo usersContact = storeContactInfo(contact);
            contact.close();
            conn.commit();
            return usersContact;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {}
            return getContactInfo(username);
        }
    }

    /**
     * Returns the survey answers of given user. This is used for determining
     * compatibility between this user and other users
     * @param username user's identifier
     * @return survey answers of given user.
     *         If survey answers not found under user, return null.
     * @throws IllegalArgumentException if user not found in database
     */
    public Survey getSurvey(String username) {
        try {
            // if username not found, throw exception
            if(!userExists(username)) {
                conn.commit();
                throw new IllegalArgumentException();
            }

            // if user's survey not in database, return null
            PreparedStatement getSurveyStmt = getSurveyStmt(username);
            ResultSet survey = getSurveyStmt.executeQuery();
            if(!survey.next()) {
                conn.commit();
                return null;
            }

            // return user's survey answers
            Survey usersSurvey = storeSurvey(survey);
            survey.close();
            conn.commit();
            return usersSurvey;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {}
            return getSurvey(username);
        }
    }

    /**
     * Sets contact information for desired user to have
     * the contact information provided
     * @param userContactInfo updates contact information with info
     *                        provided in this
     * @throws IllegalArgumentException if userContactInfo has invalid values
     */
    public void setContactInfo(ContactInfo userContactInfo) {
        try {
            String username = userContactInfo.username;

            // if username not found, throw exception
            if(!userExists(username)) {
                conn.commit();
                throw new IllegalArgumentException();
            }

            // if user's contactInfo already there, update their info
            // else Create new contactInfo for user
            PreparedStatement getContactStmt = getContactStmt(username);
            ResultSet contact = getContactStmt.executeQuery();
            if(contact.next()) {
                PreparedStatement updateContactStmt = updateContactStmt(userContactInfo);
                updateContactStmt.executeUpdate();
            } else {
                PreparedStatement createContactStmt = createContactStmt(userContactInfo);
                createContactStmt.executeUpdate();
            }
            contact.close();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException otherE) {}

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 3819) {
                throw new IllegalArgumentException();
            } else {
                //e.printStackTrace();
                setContactInfo(userContactInfo);
            }
        }
    }

    /**
     * Sets survey answers for desired user
     * with the new survey answers provided
     * @param userSurvey updates a survey with the information provided in userSurvey
     * @throws IllegalArgumentException if userSurvey has invalid values
     */
    public void setSurvey(Survey userSurvey) {
        try {
            String username = userSurvey.username;

            // if username not found, throw exception
            if(!userExists(username)) {
                conn.commit();
                throw new IllegalArgumentException();
            }

            // if user's survey already there, update their info
            // else Create new survey for user
            PreparedStatement getSurveyStmt = getSurveyStmt(username);
            ResultSet survey = getSurveyStmt.executeQuery();
            if(survey.next()) {
                PreparedStatement updateSurveyStmt = updateSurveyStmt(userSurvey);
                updateSurveyStmt.executeUpdate();
            } else {
                PreparedStatement createSurveyStmt = createSurveyStmt(userSurvey);
                createSurveyStmt.executeUpdate();
            }
            survey.close();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException otherE) {}

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 3819) {
                throw new IllegalArgumentException();
            } else {
                //e.printStackTrace();
                setSurvey(userSurvey);
            }
        }
    }

    /**
     * Sets the match info between user1 and user2
     * with the info provided.
     * @param matchInfo contains match information to be added/updated
     * @throws IllegalArgumentException if matchInfo has invalid values
     */
    public void setMatch(Match matchInfo) {
        try {
            // if users don't exist, throw exception
            if(!userExists(matchInfo.user1) || !userExists(matchInfo.user2)) {
                conn.commit();
                throw new IllegalArgumentException();
            }

            // if user's match info already there, update their info
            // else Create new match for users
            PreparedStatement getMatchStmt = getMatchStmt(matchInfo.user1, matchInfo.user2);
            ResultSet match = getMatchStmt.executeQuery();
            if(match.next()) {
                PreparedStatement updateMatchStmt = updateMatchStmt(matchInfo);
                updateMatchStmt.executeUpdate();
            } else {
                PreparedStatement createMatchStmt = createMatchStmt(matchInfo);
                createMatchStmt.executeUpdate();
            }
            match.close();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException otherE) {}

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 3819) {
                throw new IllegalArgumentException();
            } else {
                //e.printStackTrace();
                setMatch(matchInfo);
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
                conn.commit();
                throw new IllegalArgumentException();
            }

            // if users' match not in database, return null
            PreparedStatement getMatchStmt = getMatchStmt(username1, username2);
            ResultSet match = getMatchStmt.executeQuery();
            if(!match.next()) {
                conn.commit();
                return null;
            }

            // return users' match info
            Match usersMatch = storeMatch(match);
            match.close();
            conn.commit();
            return usersMatch;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {}
            return getMatch(username1, username2);
        }
    }

    /**
     * Return topK users who have the highest compatibility with given user.
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
                conn.commit();
                throw new IllegalArgumentException();
            }

            // return up to topK matches
            PreparedStatement getTopMatchesStmt = getTopMatchesStmt(username, topK);
            ResultSet currMatch = getTopMatchesStmt.executeQuery();
            while(currMatch.next()) {
                if(currMatch.getString(1).equals(username)) {
                    topMatches.add(currMatch.getString(2));
                } else {
                    topMatches.add(currMatch.getString(1));
                }
            }
            conn.commit();
            return topMatches;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {}
            return getTopMatches(username, topK);
        }
    }

    /**
     * updates compatibility rating for user1 and user2
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
                conn.commit();
                throw new IllegalArgumentException();
            }

            PreparedStatement updateCompatibilityStmt =
                    updateCompatibilityStmt(username1, username2, newCompatibility);
            updateCompatibilityStmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException otherE) {}

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 3819) {
                throw new IllegalArgumentException();
            } else {
                //e.printStackTrace();
                updateCompatibility(username1, username2, newCompatibility);
            }
        }
    }

    /**
     * updates match status for user1 and user2
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
                conn.commit();
                throw new IllegalArgumentException();
            }

            PreparedStatement updateMatchStatusStmt =
                    updateMatchStatusStmt(username1, username2, newMatchStatus);
            updateMatchStatusStmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException otherE) {}

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 3819) {
                throw new IllegalArgumentException();
            } else {
                //e.printStackTrace();
                updateMatchStatus(username1, username2, newMatchStatus);
            }
        }
    }

    /**
     * Get the set of survey answers for all users in database.
     * This is used to determine compatibility scores between users.
     * @return set of survey answers of all users in database.
     *      If there are no surveys, return an empty set
     */
    public Set<Survey> getAllSurveys() {
        try {
            Set<Survey> surveys = new HashSet<>();
            ResultSet currSurvey = getAllSurveysStmt().executeQuery();
            while(currSurvey.next()) {
                Survey survey = storeSurvey(currSurvey);
                surveys.add(survey);
            }
            conn.commit();
            return surveys;

        } catch(SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {}
            return getAllSurveys();
        }
    }

    // check if user with given username exists
    private boolean userExists(String username) throws SQLException {
        PreparedStatement getUserStmt = getUserStmt(username);
        ResultSet user = getUserStmt.executeQuery();
        boolean userExists = user.next();
        user.close();
        return userExists;
    }

    // checks if match with the 2 given users exist
    // assumes username1 < username2
    private boolean matchExists(String username1, String username2) throws SQLException {
        PreparedStatement getMatchStmt = getMatchStmt(username1, username2);
        ResultSet match = getMatchStmt.executeQuery();
        boolean matchExists = match.next();
        match.close();
        return matchExists;
    }

    // checks if survey with given user exists
    private boolean surveyExists(String username) throws SQLException {
        PreparedStatement getSurveyStmt = getSurveyStmt(username);
        ResultSet survey = getSurveyStmt.executeQuery();
        boolean surveyExists = survey.next();
        survey.close();
        return surveyExists;
    }

    // checks if contact info with given user exists
    private boolean contactExists(String username) throws SQLException {
        PreparedStatement getContactStmt = getContactStmt(username);
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
