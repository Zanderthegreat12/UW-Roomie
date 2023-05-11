package roomieapp;

import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * unit test for creating user and logging a user in
 */
public class SQLUserTest {

    private static final Query querier = new Query(false);

    /**
     * Tests createUser method with only one user
     */
    @Test
    public void testCreateUserSimple()
    {
        try {
            querier.clearTables();
            assertTrue(querier.createUser("user1", "password"));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * Tests createUser method with multiple users
     */
    @Test
    public void testCreateUsers()
    {
        try {
            querier.clearTables();
            // matching passwords between users shouldn't matter
            assertTrue(querier.createUser("user1", "password1"));
            assertTrue(querier.createUser("user2", "password2"));
            assertTrue(querier.createUser("user3", "password1"));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * tests 2 user creations with same name fails
     */
    @Test
    public void testCreateSameUsernames()
    {
        try {
            querier.clearTables();
            assertTrue(querier.createUser("user1", "password1"));
            assertTrue(!querier.createUser("user1", "password2"));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test login with correct password/username passes
     */
    @Test
    public void testLoginSimpleSuccess()
    {
        try {
            querier.clearTables();
            assertTrue(querier.createUser("user", "user"));
            assertTrue(querier.login("user", "user"));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test login with either incorrect username or password fails
     */
    @Test
    public void testLoginSimpleFail()
    {
        try {
            querier.clearTables();
            assertTrue(querier.createUser("user", "passThis"));
            assertTrue(!querier.login("userFail", "passThis"));
            assertTrue(!querier.login("user", "failThis"));
            assertTrue(!querier.login("wat", "wat"));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test login of multiple users with multiple accounts already created
     */
    @Test
    public void testMultiLogin()
    {
        try {
            querier.clearTables();
            assertTrue(querier.createUser("testUser1", "password1"));
            assertTrue(querier.createUser("testUser2", "password2"));
            assertTrue(querier.login("testUser1", "password1"));
            assertTrue(querier.login("testUser2", "password2"));
            assertTrue(!querier.login("testUser1", "password2"));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
