package roomieapp;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SQLTestContactInfo {
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
}
