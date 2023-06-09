package roomieapp;

import org.junit.*;

import java.net.ConnectException;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for SQL all-purpose queries
 */
public class SQLTest
{
    /**
     * tests that I can successfully connect to MySQL database
     */
    @Test
    public void testQueryConn()
    {
        Query querier = new Query(false);
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
        try {
            Query querier = new Query(false);
            querier.clearTables();
            querier.createUser("user1", "password");
            querier.clearTables();
            assertTrue(!querier.login("user1", "password"));
            querier.clearTables();
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }
}
