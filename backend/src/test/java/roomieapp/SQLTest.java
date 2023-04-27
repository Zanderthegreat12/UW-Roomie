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
}
