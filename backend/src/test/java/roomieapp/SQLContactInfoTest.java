package roomieapp;

import org.junit.*;

import java.net.ConnectException;

import static org.junit.Assert.assertTrue;

/**
 * unit tests for setting/getting contact info
 */
public class SQLContactInfoTest {

    private static final Query querier = new Query(false);

    private static final ContactInfo userContact = new ContactInfo(
            "user",
            "userTest@helpMe.com",
            1028392074L,
            "userTest#6969"
    );

    private static final ContactInfo otherUserContact = new ContactInfo(
             "user2",
             "help@helpMe.com",
             7392392074L,
             "welp#9696"
    );

    private static final ContactInfo updatedUserContact = new ContactInfo(
            "user",
            "whyOwhy@hasThisHappened.com",
            5038640723L,
            "goodLuck#2094"
    );

    /**
     * test setContactInfo creates new contactInfo for single user
     */
    @Test
    public void testCreateContactInfo()
    {
        try {
            querier.clearTables();
            querier.createUser(userContact.username, "userPass");
            querier.setContactInfo(userContact);
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    /**
     * Test getting contact info for one user
     */
    @Test
    public void testGetContactInfoSimple()
    {
        try {
            querier.clearTables();
            querier.createUser(userContact.username, "firstPassword");
            querier.setContactInfo(userContact);
            ContactInfo retrievedData = querier.getContactInfo(userContact.username);
            assertTrue(retrievedData.equals(userContact));
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    /**
     * Test setting contact info if user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetContactInfoNoUser()
    {
        try {
            querier.clearTables();
            querier.setContactInfo(userContact);
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    /**
     * Test getting contact info if user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetContactInfoNoUser()
    {
        try {
            querier.clearTables();
            ContactInfo test = querier.getContactInfo("user");
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    /**
     * Test getting contact info when contact info doesn't exist
     */
    @Test
    public void testGetContactInfoNoneExist()
    {
        try {
            querier.clearTables();
            querier.createUser("firstUser", "firstPassword");
            ContactInfo retrievedData = querier.getContactInfo("firstUser");
            assertTrue(retrievedData == null);
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    /**
     * test setContactInfo updates contactInfo for single user
     */
    @Test
    public void testUpdateContactInfo()
    {
        try {
            querier.clearTables();
            querier.createUser(userContact.username, "userPass");
            querier.setContactInfo(userContact);
            ContactInfo retrievedData = querier.getContactInfo(userContact.username);
            assertTrue(retrievedData.equals(userContact));
            querier.setContactInfo(updatedUserContact);
            retrievedData = querier.getContactInfo(updatedUserContact.username);
            assertTrue(retrievedData.equals(updatedUserContact));
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    /**
     * test setContactInfo/getContactInfo works with multiple users
     */
    @Test
    public void testMultiContactInfo()
    {
        try {
            querier.clearTables();
            querier.createUser(userContact.username, "userPass");
            querier.setContactInfo(userContact);
            ContactInfo retrievedData = querier.getContactInfo(userContact.username);
            assertTrue(retrievedData.equals(userContact));

            querier.createUser(otherUserContact.username, "otherPass");
            querier.setContactInfo(otherUserContact);
            retrievedData = querier.getContactInfo(otherUserContact.username);
            assertTrue(retrievedData.equals(otherUserContact));

            retrievedData = querier.getContactInfo(userContact.username);
            assertTrue(retrievedData.equals(userContact));
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }
}
