package roomieapp;

import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class SQLTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testQueryConn()
    {
        Query querier = new Query();
        querier.clearTables();
        assertTrue(true);
    }
}
