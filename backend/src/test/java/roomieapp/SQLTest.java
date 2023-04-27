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
     * test setContactInfo creates new contactInfo for single user
     */
    @Test
    public void testCreateContactInfo()
    {
        Query querier = new Query();
        querier.clearTables();
        querier.createUser("user", "userPass");
        ContactInfo userContact = new ContactInfo(
                "user",
                "userTest@helpMe.com",
                1028392074L,
                "userTest#6969"
        );
        querier.setContactInfo(userContact);
        querier.clearTables();
    }

    /**
     * Test getting contact info for one user
     */
    @Test
    public void testGetContactInfoSimple()
    {
        Query querier = new Query();
        querier.clearTables();
        querier.createUser("firstUser", "firstPassword");
        ContactInfo userContact = new ContactInfo(
                "firstUser",
                "userTest@helpMe.com",
                1028392074L,
                "userTest#6969"
        );
        ContactInfo retrievedData = querier.getContactInfo("firstUser");
        assertTrue(retrievedData.username.equals("firstUser"));
        assertTrue(retrievedData.email.equals("userTest@helpMe.com"));
        assertTrue(retrievedData.phoneNumber == 1028392074L);
        assertTrue(retrievedData.discord.equals("userTest#6969"));
        querier.clearTables();
    }

    /**
     * Test setting contact info if user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetContactInfoNoUser()
    {
        Query querier = new Query();
        querier.clearTables();
        ContactInfo userContact = new ContactInfo(
                "firstUser",
                "userTest@helpMe.com",
                1028392074L,
                "userTest#6969"
        );
        querier.setContactInfo(userContact);
        querier.clearTables();
    }

    /**
     * Test getting contact info if user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetContactInfoNoUser()
    {
        Query querier = new Query();
        querier.clearTables();
        querier.getContactInfo("user");
        querier.clearTables();
    }

    /**
     * Test getting contact info when contact info doesn't exist
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetContactInfoNoneExist()
    {
        Query querier = new Query();
        querier.clearTables();
        querier.createUser("firstUser", "firstPassword");
        ContactInfo retrievedData = querier.getContactInfo("firstUser");
        assertTrue(retrievedData == null);
        querier.clearTables();
    }

    /**
     * test setContactInfo updates contactInfo for single user
     */
    @Test
    public void testUpdateContactInfo()
    {
        Query querier = new Query();
        querier.clearTables();
        querier.createUser("user", "userPass");
        ContactInfo userContact = new ContactInfo(
                "user",
                "userTest@helpMe.com",
                1028392074L,
                "userTest#6969"
        );
        querier.setContactInfo(userContact);
        ContactInfo retrievedData = querier.getContactInfo("user");
        assertTrue(retrievedData.username.equals("user"));
        assertTrue(retrievedData.email.equals("userTest@helpMe.com"));
        assertTrue(retrievedData.phoneNumber == 1028392074L);
        assertTrue(retrievedData.discord.equals("userTest#6969"));

        userContact = new ContactInfo(
                "user",
                "help@helpMe.com",
                7392392074L,
                "welp#9696"
        );
        querier.setContactInfo(userContact);
        retrievedData = querier.getContactInfo("user");
        assertTrue(retrievedData.username.equals("user"));
        assertTrue(retrievedData.email.equals("help@helpMe.com"));
        assertTrue(retrievedData.phoneNumber == 7392392074L);
        assertTrue(retrievedData.discord.equals("welp#9696"));

        querier.clearTables();
    }

    /**
     * test setContactInfo/getContactInfo works with multiple users
     */
    @Test
    public void testMultiContactInfo()
    {
        Query querier = new Query();
        querier.clearTables();
        querier.createUser("user", "userPass");
        ContactInfo userContact = new ContactInfo(
                "user",
                "userTest@helpMe.com",
                1028392074L,
                "userTest#6969"
        );
        querier.setContactInfo(userContact);
        ContactInfo retrievedData = querier.getContactInfo("firstUser");
        assertTrue(retrievedData.username.equals("firstUser"));
        assertTrue(retrievedData.email.equals("userTest@helpMe.com"));
        assertTrue(retrievedData.phoneNumber == 1028392074L);
        assertTrue(retrievedData.discord.equals("userTest#6969"));

        querier.createUser("user2", "otherPass");
        userContact = new ContactInfo(
                "user2",
                "help@helpMe.com",
                7392392074L,
                "welp#9696"
        );
        querier.setContactInfo(userContact);
        retrievedData = querier.getContactInfo("user2");
        assertTrue(retrievedData.username.equals("user2"));
        assertTrue(retrievedData.email.equals("help@helpMe.com"));
        assertTrue(retrievedData.phoneNumber == 7392392074L);
        assertTrue(retrievedData.discord.equals("welp#9696"));

        retrievedData = querier.getContactInfo("user");
        assertTrue(retrievedData.username.equals("user"));
        assertTrue(retrievedData.email.equals("userTest@helpMe.com"));
        assertTrue(retrievedData.phoneNumber == 1028392074L);
        assertTrue(retrievedData.discord.equals("userTest#6969"));

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
