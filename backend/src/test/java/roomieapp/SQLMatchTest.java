package roomieapp;

import org.junit.*;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * unit tests for setting/getting matches
 */
public class SQLMatchTest {

    private static final Query querier = new Query(false);

    private static final Match match = new Match(
        "user1",
        "user2",
        90,
        0
    );

    private static final Match updateMatch = new Match(
        "user1",
        "user2",
        60,
        2
    );

    private static final Match overlapMatch = new Match(
            "user1",
            "user3",
            47,
            3
    );

    private static final Match otherMatch = new Match(
        "firstUser",
        "secondUser",
        12,
        1
    );

    private static final Match overlapMatch2 = new Match(
            "user1",
            "user4",
            20,
            0
    );

    private static final Match overlapMatch3 = new Match(
            "alphaUser",
            "user1",
            63,
            0
    );

    private static final Match updatedCompMatch = new Match(
            "user1",
            "user2",
            70,
            0
    );

    private static final Match updatedStatusMatch = new Match(
            "user1",
            "user2",
            90,
            2
    );

    /**
     * test creating match for single pair of users
     */
    @Test
    public void testCreateMatch()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting match for single pair of users
     */
    @Test
    public void testGetMatch()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);

            // test shouldn't change between these 2 iterations
            Match test = querier.getMatch(match.user1, match.user2);
            assertTrue(match.equals(test));
            test = querier.getMatch(match.user2, match.user1);
            assertTrue(match.equals(test));

            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting match for 2 separate pair of matches
     */
    @Test
    public void testMultiMatch()
    {
        try {
            querier.clearTables();

            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);
            Match test = querier.getMatch(match.user1, match.user2);
            assertTrue(match.equals(test));

            querier.createUser(otherMatch.user1, "passIt");
            querier.createUser(otherMatch.user2, "hitIt");
            querier.setMatch(otherMatch);
            test = querier.getMatch(otherMatch.user2, otherMatch.user1);
            assertTrue(otherMatch.equals(test));

            test = querier.getMatch(match.user1, match.user2);
            assertTrue(match.equals(test));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test updating a single match
     */
    @Test
    public void testUpdateMatch()
    {
        try {
            querier.clearTables();

            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);
            Match test = querier.getMatch(match.user1, match.user2);
            assertTrue(match.equals(test));

            querier.setMatch(updateMatch);
            test = querier.getMatch(match.user1, match.user2);
            assertTrue(updateMatch.equals(test));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * testing setting match with user2 not existing
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMatchNoUser2()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.setMatch(match);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * testing setting match with user1 not existing
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMatchNoUser1()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * testing getting match with user2 not existing
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetMatchNoUser2()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            Match test = querier.getMatch(match.user1, match.user2);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * testing getting match with user1 not existing
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetMatchNoUser1()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user2, "passIt");
            Match test = querier.getMatch(match.user1, match.user2);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting match that doesn't exist
     */
    @Test
    public void testGetMatchNoneExists()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            Match test = querier.getMatch(match.user1, match.user2);
            assertTrue(test == null);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting/setting matches with 1 user overlapping
     */
    @Test
    public void testOverlapMatch()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.createUser(overlapMatch.user2, "user3");
            querier.setMatch(match);
            Match test = querier.getMatch(match.user1, match.user2);
            assertTrue(match.equals(test));

            querier.setMatch(overlapMatch);
            test = querier.getMatch(overlapMatch.user1, overlapMatch.user2);
            assertTrue(overlapMatch.equals(test));

            test = querier.getMatch(match.user1, match.user2);
            assertTrue(match.equals(test));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting top 2 matches among 4 total matches for single user
     */
    @Test
    public void testGetTop2Matches()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.createUser(overlapMatch.user2, "lololol");
            querier.createUser(overlapMatch2.user2, "user3");
            querier.createUser(overlapMatch3.user1, "alpha");

            querier.setMatch(match);
            querier.setMatch(overlapMatch);
            querier.setMatch(overlapMatch2);
            querier.setMatch(overlapMatch3);

            List<Match> test = querier.getTopMatches(match.user1, 2);
            assertTrue(test.size() == 2);
            assertTrue(test.get(0).equals(match));
            assertTrue(test.get(1).equals(overlapMatch3));

            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting all matches among 4 total matches for single user
     */
    @Test
    public void testGetTop10Matches()
    {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.createUser(overlapMatch.user2, "lololol");
            querier.createUser(overlapMatch2.user2, "user3");
            querier.createUser(overlapMatch3.user1, "alpha");

            querier.setMatch(match);
            querier.setMatch(overlapMatch);
            querier.setMatch(overlapMatch2);
            querier.setMatch(overlapMatch3);

            List<Match> test = querier.getTopMatches(match.user1, 10);
            assertTrue(test.size() == 3);
            assertTrue(test.get(0).equals(match));
            assertTrue(test.get(1).equals(overlapMatch3));
            assertTrue(test.get(2).equals(overlapMatch2));

            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testUpdateCompatibility() {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);

            querier.updateCompatibility(match.user1, match.user2, updatedCompMatch.compatibility);
            Match test = querier.getMatch(match.user1, match.user2);
            assertTrue(updatedCompMatch.equals(test));

            querier.updateCompatibility(match.user2, match.user1, updatedCompMatch.compatibility);
            test = querier.getMatch(match.user1, match.user2);
            assertTrue(updatedCompMatch.equals(test));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCompatibilityError() {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);

            querier.updateCompatibility(match.user1, match.user2, 200);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCompatibilityNoUser() {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.setMatch(match);

            querier.updateCompatibility(match.user1, match.user2, 50);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testUpdateMatchStatus() {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);

            querier.updateMatchStatus(match.user1, match.user2, updatedStatusMatch.matchStatus);
            Match test = querier.getMatch(match.user1, match.user2);
            assertTrue(updatedStatusMatch.equals(test));

            querier.updateMatchStatus(match.user2, match.user1, updatedStatusMatch.matchStatus);
            test = querier.getMatch(match.user1, match.user2);
            assertTrue(updatedStatusMatch.equals(test));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMatchStatusError() {
        try {
            querier.clearTables();
            querier.createUser(match.user1, "passIt");
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);

            querier.updateMatchStatus(match.user1, match.user2, 5);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMatchStatusNoUser() {
        try {
            querier.clearTables();
            querier.createUser(match.user2, "hitIt");
            querier.setMatch(match);

            querier.updateMatchStatus(match.user1, match.user2, 2);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
