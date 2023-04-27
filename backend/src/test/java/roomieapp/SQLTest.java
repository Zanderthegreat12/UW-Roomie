package roomieapp;

import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for SQL queries
 */
public class SQLTest
{
    /**
     * tests that I can successfully connect to MySQL database
     */
    @Test
    public void testQueryConn()
    {
        Query querier = new Query();
        assertTrue(true);
    }

    /**
     * Tests if User table is successfully cleared.
     * User table should only be able to be cleared if all other tables
     * with foreign key references are also cleared.
     * All other tests rely on clearTables() working.
     */
    @Test
    public void testClearTables()
    {
        Query querier = new Query();
        querier.clearTables();
        querier.createUser("user1", "password");
        querier.clearTables();
        assertTrue(!querier.login("user1", "password"));
        querier.clearTables();
    }

    /**
     * Tests createUser method with only one user
     */
    @Test
    public void testCreateUserSimple()
    {
        Query querier = new Query();
        querier.clearTables();
        assertTrue(querier.createUser("user1", "password"));
        querier.clearTables();
    }

    /**
     * Tests createUser method with multiple users
     */
    @Test
    public void testCreateUsers()
    {
        Query querier = new Query();
        querier.clearTables();
        // matching passwords between users shouldn't matter
        assertTrue(querier.createUser("user1", "password1"));
        assertTrue(querier.createUser("user2", "password2"));
        assertTrue(querier.createUser("user3", "password1"));
        querier.clearTables();
    }

    /**
     * tests 2 user creations with same name fails
     */
    @Test
    public void testCreateSameUsernames()
    {
        Query querier = new Query();
        querier.clearTables();
        assertTrue(querier.createUser("user1", "password1"));
        assertTrue(!querier.createUser("user1", "password2"));
        querier.clearTables();
    }

    /**
     * test login with correct password/username passes
     */
    @Test
    public void testLoginSimpleSuccess()
    {
        Query querier = new Query();
        querier.clearTables();
        assertTrue(querier.createUser("user", "user"));
        assertTrue(querier.login("user", "user"));
        querier.clearTables();
    }

    /**
     * test login with either incorrect username or password fails
     */
    @Test
    public void testLoginSimpleFail()
    {
        Query querier = new Query();
        querier.clearTables();
        assertTrue(querier.createUser("user", "passThis"));
        assertTrue(!querier.login("userFail", "passThis"));
        assertTrue(!querier.login("user", "failThis"));
        assertTrue(!querier.login("wat", "wat"));
        querier.clearTables();
    }

    /**
     * test login of multiple users with multiple accounts already created
     */
    @Test
    public void testMultiLogin()
    {
        Query querier = new Query();
        querier.clearTables();
        assertTrue(querier.createUser("testUser1", "password1"));
        assertTrue(querier.createUser("testUser2", "password2"));
        assertTrue(querier.login("testUser1", "password1"));
        assertTrue(querier.login("testUser2", "password2"));
        assertTrue(!querier.login("testUser1", "password2"));
        querier.clearTables();
    }

    /**
     *
     */
    @Test
    public void testGetContactInfo()
    {
        Query querier = new Query();
        querier.clearTables();
    }

    @Test
    public void testSetContactInfo()
    {
        Query querier = new Query();
        querier.clearTables();

    }

    @Test
    public void testGetSurvey()
    {
        Query querier = new Query();
        querier.clearTables();

    }

    @Test
    public void testSetSurvey()
    {
        Query querier = new Query();
        querier.clearTables();

    }

    @Test
    public void testUpdateMatch()
    {
        Query querier = new Query();
        querier.clearTables();

    }

    @Test
    public void testGetMatch()
    {
        Query querier = new Query();
        querier.clearTables();

    }
}
