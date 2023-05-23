package roomieapp;

import org.apache.commons.lang3.NotImplementedException;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;
import java.util.*;
import java.util.Properties;

import static java.lang.Long.parseLong;

/**
 *  A collection of Query functions that can read and write data
 *  from/to the SQL database. This class can retrieve and update
 *  user information and compatibility.
 */
public class Query {

    // The connection manager
    // This is how Query will make queries to the SQL database
    private Connection conn;
    // This encrypts data such as contact information and passwords
    private SecurityUtils cipher;

    /**
     * Initializes a connection with SQL database
     * and sets up a Query object to be able to send queries
     * to the SQL database
     * @param testOff if true, use actual database, else if false, use test database
     */
    public Query(boolean testOff) {
        try {
            if(testOff) {
                this.conn = DBConnUtils.openConnection();
            } else {
                this.conn = DBConnUtils.openTestConnection();
            }
            this.cipher = new SecurityUtils();
        }catch (Exception e) {
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
        byte[] email = cipher.encrypt(contact.email);
        byte[] phoneNum = cipher.encrypt(Long.toString(contact.phoneNumber));
        byte[] discord = cipher.encrypt(contact.discord);
        stmt.setString(1, contact.username);
        stmt.setBytes(2, email);
        stmt.setBytes(3, phoneNum);
        stmt.setBytes(4, discord);
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
        byte[] email = cipher.encrypt(contact.email);
        byte[] phoneNum = cipher.encrypt(Long.toString(contact.phoneNumber));
        byte[] discord = cipher.encrypt(contact.discord);
        stmt.setBytes(1, email);
        stmt.setBytes(2, phoneNum);
        stmt.setBytes(3, discord);
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
            "SELECT TOP(?) * " +
                "FROM Matches " +
                "WHERE (user1 = ? or user2 = ?) and matchStatus = 0 " +
                "ORDER BY compatibility DESC;"
        );
        stmt.setString(2, username);
        stmt.setString(3, username);
        stmt.setInt(1, topK);
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

    // query to find all incoming match requests
    private PreparedStatement incomingMatchesStmt(String user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM Matches " +
                "WHERE (user1 = ? and matchStatus = 2) " +
                    "or (user2 = ? and matchStatus = 1)"
        );
        stmt.setString(1, user);
        stmt.setString(2, user);
        return stmt;
    }

    // query to find all outgoing match requests
    private PreparedStatement outgoingMatchesStmt(String user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Matches " +
                    "WHERE (user1 = ? and matchStatus = 1) " +
                        "or (user2 = ? and matchStatus = 2)"
        );
        stmt.setString(1, user);
        stmt.setString(2, user);
        return stmt;
    }

    // query to find everyone whom you already matched with
    private PreparedStatement completeMatchesStmt(String user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Matches " +
                    "WHERE (user1 = ? or user2 = ?) " +
                        "and matchStatus = 3"
        );
        stmt.setString(1, user);
        stmt.setString(2, user);
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
        } catch (SQLException e) {
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
     * @throws ConnectException if SQL database fails to commit queries
     */
    public boolean createUser(String username, String password) throws ConnectException {
        try {
            // check if username already exists
            if(userExists(username)) {
                conn.commit();
                return false;
            }

            // username doesn't exist, so create the new user and return true
            PreparedStatement createUserStmt = createUserStmt(username,
                    SecurityUtils.hashPassword(password));
            createUserStmt.executeUpdate();
            conn.commit();
            return true;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {
                throw new ConnectException();
            }
            return createUser(username, password);
        }
    }

    /**
     * Checks if login is successful based on username and password
     * @param username user's identifier
     * @param password a string to verify user's identity
     * @return return true if login was successful, otherwise return false
     * @throws ConnectException if SQL database fails to commit queries
     */
    public boolean login(String username, String password) throws ConnectException {
        try {
            PreparedStatement getUserStmt = getUserStmt(username);
            ResultSet user = getUserStmt.executeQuery();

            // if username incorrect, return false
            if(!user.next()) {
                conn.commit();
                return false;
            }

            // if password matches, return true, otherwise return false
            boolean correctLogin = SecurityUtils.plaintextMatchesHash(
                    password, user.getBytes(2));
            user.close();
            conn.commit();
            return correctLogin;

        } catch (SQLException e) {
            //e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException otherE) {
                throw new ConnectException();
            }
            return login(username, password);
        }
    }

    /**
     * Returns the contact information of the given user
     * @param username user's identifier
     * @return contact info of given user.
     *         If contact info of given user not found, return null.
     * @throws IllegalArgumentException if user not found in database
     * @throws ConnectException if SQL database fails to commit queries
     */
    public ContactInfo getContactInfo(String username) throws ConnectException {
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }
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
     * @throws ConnectException if SQL database fails to commit queries
     */
    public Survey getSurvey(String username) throws ConnectException {
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }
            return getSurvey(username);
        }
    }

    /**
     * Sets contact information for desired user to have
     * the contact information provided
     * @param userContactInfo updates contact information with info
     *                        provided in this
     * @throws IllegalArgumentException if userContactInfo has invalid values
     * @throws ConnectException if SQL database fails to commit queries
     */
    public void setContactInfo(ContactInfo userContactInfo) throws ConnectException {
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 547) {
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
     * @throws ConnectException if SQL database fails to commit queries
     */
    public void setSurvey(Survey userSurvey) throws ConnectException {
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 547) {
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
     * @throws ConnectException if SQL database fails to commit queries
     */
    public void setMatch(Match matchInfo) throws ConnectException {
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 547) {
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
     * @throws ConnectException if SQL database fails to commit queries
     */
    public Match getMatch(String username1, String username2) throws ConnectException {
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }
            return getMatch(username1, username2);
        }
    }

    /**
     * Return topK users who have the highest compatibility with given user.
     * Ignores other users who've already matched with given user.
     * @param username the identifier of the user of interest
     * @param topK the number of possible matches to return.
     *             If there are less than topK results, return all possible matches
     * @return the matches of highest compatibility between given user
     *         and other users.
     *         matches with higher compatibility will be at beginning of list.
     *         matches with lower compatibility will be at the end of the list.
     * @throws IllegalArgumentException if user not in database
     * @throws ConnectException if SQL database fails to commit queries
     */
    public List<Match> getTopMatches(String username, int topK) throws ConnectException {
        try {
            return getMatches(username, getTopMatchesStmt(username, topK));
        } catch (SQLException e) {
            throw new ConnectException();
        }
    }

    /**
     * updates compatibility rating for user1 and user2
     * @param username1 identifier for username1
     * @param username2 identifier for username2
     * @param newCompatibility the updated compatibility for the 2 users
     * @throws IllegalArgumentException if users or match not found in database
     *         or if newCompatibility not between 0 and 100
     * @throws ConnectException if SQL database fails to commit queries
     */
    public void updateCompatibility(String username1, String username2,
                                    float newCompatibility) throws ConnectException {
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }

            int value = e.getErrorCode();
            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 547) {
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
     * @throws ConnectException if SQL database fails to commit queries
     */
    public void updateMatchStatus(String username1, String username2, int newMatchStatus)
            throws ConnectException{
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }

            // if value incorrect format, throw exception, else try again
            if(e.getErrorCode() == 547) {
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
     * @throws ConnectException if SQL database fails to commit queries
     */
    public Set<Survey> getAllSurveys() throws ConnectException{
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
            } catch (SQLException otherE) {
                throw new ConnectException();
            }
            return getAllSurveys();
        }
    }

    /**
     * Returns all incoming match requests for given user.
     * Matches aren't returned in any given order
     * @param username identifier for user
     * @return list of incoming match requests
     * @throws IllegalArgumentException if user not in database
     * @throws ConnectException if SQL database fails to commit queries
     */
    public List<Match> getIncomingMatches(String username) throws ConnectException {
        try {
            return getMatches(username, incomingMatchesStmt(username));
        } catch (SQLException e) {
            throw new ConnectException();
        }
    }

    /**
     * Returns all outgoing match requests for given user.
     * Matches aren't returned in any given order
     * @param username identifier for user
     * @return list of outgoing match requests
     * @throws IllegalArgumentException if user not in database
     * @throws ConnectException if SQL database fails to commit queries
     */
    public List<Match> getOutgoingMatches(String username) throws ConnectException {
        try {
            return getMatches(username, outgoingMatchesStmt(username));
        } catch (SQLException e) {
            throw new ConnectException();
        }
    }

    /**
     * Returns all completed match requests for given user
     * where both users matched with each other.
     * Matches aren't returned in any given order.
     * @param username identifier for user
     * @return list of matches of where user and other user both matched each other
     * @throws IllegalArgumentException if user not in database
     * @throws ConnectException if SQL database fails to commit queries
     */
    public List<Match> getCompleteMatches(String username) throws ConnectException {
        try {
            return getMatches(username, completeMatchesStmt(username));
        } catch (SQLException e) {
            throw new ConnectException();
        }
    }

    // gets matches for given user.
    // the types of matches returned is specified by matchStmt
    private List<Match> getMatches(String username, PreparedStatement matchStmt)
            throws ConnectException{
        try {
            // throw exception if user not in database
            if(!userExists(username)) {
                conn.commit();
                throw new IllegalArgumentException();
            }

            List<Match> completeMatches = new ArrayList<>();
            ResultSet currMatch = matchStmt.executeQuery();
            while(currMatch.next()) {
                Match match = storeMatch(currMatch);
                completeMatches.add(match);
            }
            currMatch.close();
            conn.commit();
            return completeMatches;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException otherE) {
                throw new ConnectException();
            }
            return getMatches(username, matchStmt);
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
                cipher.decrypt(contact.getBytes(2)), // email
                parseLong(cipher.decrypt(contact.getBytes(3))), // phone number
                cipher.decrypt(contact.getBytes(4)) // discord
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
