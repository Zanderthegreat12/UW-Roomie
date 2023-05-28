package roomieapp;

import org.junit.*;

import java.net.ConnectException;
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

    private static final Match incomingMatch1 = new Match(
            "aUser1",
            "incomingUser1",
            10,
            2
    );

    private static final Match incomingMatch2 = new Match(
            "aUser1",
            "incomingUser2",
            64,
            2
    );

    private static final Match outgoingMatch1 = new Match(
            "aUser1",
            "outgoingUser1",
            28,
            1
    );

    private static final Match outgoingMatch2 = new Match(
            "aUser1",
            "outgoingUser2",
            98,
            1
    );

    private static final Match completeMatch1 = new Match(
            "aUser1",
            "completeUser1",
            49,
            3
    );

    private static final Match completeMatch2 = new Match(
            "aUser1",
            "completeUser2",
            81,
            3
    );

    private static final Match nonMatch1 = new Match(
            "aUser1",
            "nonUser1",
            3,
            0
    );

    private static final Match nonMatch2 = new Match(
            "aUser1",
            "nonUser2",
            56,
            0
    );

    private static final Match rejectMatch1 = new Match(
            "aUser1",
            "rejectUser1",
            3,
            -1
    );

    private static final Match rejectMatch2 = new Match(
            "aUser1",
            "rejectUser2",
            56,
            -1
    );

    private static final Match caseMatch = new Match(
            "aa",
            "PP",
            20,
            0
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
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
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetIncomingMatchesSingle() {
        try {
            querier.clearTables();
            querier.createUser(incomingMatch1.user1, "lal");
            querier.createUser(incomingMatch1.user2, "lil");
            querier.setMatch(incomingMatch1);
            List<Match> test = querier.getIncomingMatches(incomingMatch1.user1);
            assertTrue(test.size() == 1);
            assertTrue(test.get(0).equals(incomingMatch1));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetIncomingMatchesMulti() {
        try {
            querier.clearTables();
            querier.createUser(nonMatch1.user1, "lal");
            querier.createUser(nonMatch1.user2, "lul");
            querier.setMatch(nonMatch1);

            querier.createUser(nonMatch2.user2, "lel");
            querier.setMatch(nonMatch2);

            querier.createUser(incomingMatch1.user2, "lil");
            querier.setMatch(incomingMatch1);

            querier.createUser(incomingMatch2.user2, "lil");
            querier.setMatch(incomingMatch2);

            querier.createUser(outgoingMatch1.user2, "lil");
            querier.setMatch(outgoingMatch1);

            querier.createUser(outgoingMatch2.user2, "lil");
            querier.setMatch(outgoingMatch2);

            querier.createUser(completeMatch1.user2, "lil");
            querier.setMatch(completeMatch1);

            querier.createUser(completeMatch2.user2, "lil");
            querier.setMatch(completeMatch2);

            List<Match> test = querier.getIncomingMatches(nonMatch1.user1);
            List<Match> actual = new ArrayList<>();
            actual.add(incomingMatch1);
            actual.add(incomingMatch2);
            assertTrue(actual.size() == test.size());
            assertTrue(actual.containsAll(test));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetOutgoingMatchesSingle() {
        try {
            querier.clearTables();
            querier.createUser(outgoingMatch1.user1, "lal");
            querier.createUser(outgoingMatch1.user2, "lil");
            querier.setMatch(outgoingMatch1);
            List<Match> test = querier.getOutgoingMatches(outgoingMatch1.user1);
            assertTrue(test.size() == 1);
            assertTrue(test.get(0).equals(outgoingMatch1));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetOutgoingMatchesMulti() {
        try {
            querier.clearTables();
            querier.createUser(nonMatch1.user1, "lal");
            querier.createUser(nonMatch1.user2, "lul");
            querier.setMatch(nonMatch1);

            querier.createUser(nonMatch2.user2, "lel");
            querier.setMatch(nonMatch2);

            querier.createUser(incomingMatch1.user2, "lil");
            querier.setMatch(incomingMatch1);

            querier.createUser(incomingMatch2.user2, "lil");
            querier.setMatch(incomingMatch2);

            querier.createUser(outgoingMatch1.user2, "lil");
            querier.setMatch(outgoingMatch1);

            querier.createUser(outgoingMatch2.user2, "lil");
            querier.setMatch(outgoingMatch2);

            querier.createUser(completeMatch1.user2, "lil");
            querier.setMatch(completeMatch1);

            querier.createUser(completeMatch2.user2, "lil");
            querier.setMatch(completeMatch2);

            List<Match> test = querier.getOutgoingMatches(nonMatch1.user1);
            List<Match> actual = new ArrayList<>();
            actual.add(outgoingMatch1);
            actual.add(outgoingMatch2);
            assertTrue(actual.size() == test.size());
            assertTrue(actual.containsAll(test));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetCompleteMatchesSingle() {
        try {
            querier.clearTables();
            querier.createUser(completeMatch1.user1, "lal");
            querier.createUser(completeMatch1.user2, "lil");
            querier.setMatch(completeMatch1);
            List<Match> test = querier.getCompleteMatches(completeMatch1.user1);
            assertTrue(test.size() == 1);
            assertTrue(test.get(0).equals(completeMatch1));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetCompleteMatchesMulti() {
        try {
            querier.clearTables();
            querier.createUser(nonMatch1.user1, "lal");
            querier.createUser(nonMatch1.user2, "lul");
            querier.setMatch(nonMatch1);

            querier.createUser(nonMatch2.user2, "lel");
            querier.setMatch(nonMatch2);

            querier.createUser(incomingMatch1.user2, "lil");
            querier.setMatch(incomingMatch1);

            querier.createUser(incomingMatch2.user2, "lil");
            querier.setMatch(incomingMatch2);

            querier.createUser(outgoingMatch1.user2, "lil");
            querier.setMatch(outgoingMatch1);

            querier.createUser(outgoingMatch2.user2, "lil");
            querier.setMatch(outgoingMatch2);

            querier.createUser(completeMatch1.user2, "lil");
            querier.setMatch(completeMatch1);

            querier.createUser(completeMatch2.user2, "lil");
            querier.setMatch(completeMatch2);

            List<Match> test = querier.getCompleteMatches(nonMatch1.user1);
            List<Match> actual = new ArrayList<>();
            actual.add(completeMatch1);
            actual.add(completeMatch2);
            assertTrue(actual.size() == test.size());
            assertTrue(actual.containsAll(test));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetRejectedMatchesSingle() {
        try {
            querier.clearTables();
            querier.createUser(rejectMatch1.user1, "lal");
            querier.createUser(rejectMatch1.user2, "lil");
            querier.setMatch(rejectMatch1);
            List<Match> test = querier.getRejectedMatches(rejectMatch1.user1);
            assertTrue(test.size() == 1);
            assertTrue(test.get(0).equals(rejectMatch1));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetRejectedMatchesMulti() {
        try {
            querier.clearTables();
            querier.createUser(nonMatch1.user1, "lal");
            querier.createUser(nonMatch1.user2, "lul");
            querier.setMatch(nonMatch1);

            querier.createUser(nonMatch2.user2, "lel");
            querier.setMatch(nonMatch2);

            querier.createUser(incomingMatch1.user2, "lil");
            querier.setMatch(incomingMatch1);

            querier.createUser(incomingMatch2.user2, "lil");
            querier.setMatch(incomingMatch2);

            querier.createUser(outgoingMatch1.user2, "lil");
            querier.setMatch(outgoingMatch1);

            querier.createUser(outgoingMatch2.user2, "lil");
            querier.setMatch(outgoingMatch2);

            querier.createUser(completeMatch1.user2, "lil");
            querier.setMatch(completeMatch1);

            querier.createUser(completeMatch2.user2, "lil");
            querier.setMatch(completeMatch2);

            querier.createUser(rejectMatch1.user2, "lil");
            querier.setMatch(rejectMatch1);

            querier.createUser(rejectMatch2.user2, "lil");
            querier.setMatch(rejectMatch2);

            List<Match> test = querier.getRejectedMatches(nonMatch1.user1);
            List<Match> actual = new ArrayList<>();
            actual.add(rejectMatch1);
            actual.add(rejectMatch2);
            assertTrue(actual.size() == test.size());
            assertTrue(actual.containsAll(test));
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void caseSupportUpdateComp() {
        try {
            querier.createUser(caseMatch.user1, ":P");
            querier.createUser(caseMatch.user2, ":P");
            querier.setMatch(caseMatch);
            querier.updateCompatibility(caseMatch.user2, caseMatch.user1, 2);
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }

    @Test
    public void caseSupportUpdateStat() {
        try {
            querier.createUser(caseMatch.user1, ":P");
            querier.createUser(caseMatch.user2, ":P");
            querier.setMatch(caseMatch);
            querier.updateMatchStatus(caseMatch.user2, caseMatch.user1, 2);
        } catch (ConnectException e) {
            assertTrue(false);
        }
    }
}
