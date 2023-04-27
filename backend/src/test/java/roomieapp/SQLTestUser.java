package roomieapp;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SQLTestUser {
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
}
