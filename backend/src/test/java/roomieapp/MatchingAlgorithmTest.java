package roomieapp;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MatchingAlgorithmTest {

    @Test
    public void testAllandNone() {

        Survey user1 = new Survey("a", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, 0, 0, 1, 0, 0, "Hello");
        Survey user2 = new Survey("b", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, 0, 0, 1, 0, 0, "Hello");

        MatchingAlgorithm m = new MatchingAlgorithm();

        assertTrue(m.ComputeCompatability(user1, user2).equals(new Match("a", "b", 100, 0)));
        assertTrue(m.ComputeCompatability(user2, user1).equals(new Match("a", "b", 100, 0)));

        Survey user3 = new Survey("c", "B", "c", "D", 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "Sup");

        m.ComputeCompatability(user1, user3);
        assertTrue(m.ComputeCompatability(user1, user3).equals(new Match("a", "c", 0, 0)));
        assertTrue(m.ComputeCompatability(user3, user1).equals(new Match("a", "c", 0, 0)));

        assertTrue(m.ComputeCompatability(user1, user1) == null);


    }

    @Test
    public void testIdontCare() {
        Survey user1 = new Survey("a", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, -1, 0, 1, 0, 0, "Hello");
        Survey user2 = new Survey("b", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 1, -1, 0, 1, 0, 0, "Hello");
        Survey user3 = new Survey("c", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, 0, 0, 1, 0, 0, "Hello");
        Survey user4 = new Survey("d", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 1, 0, 0, 1, 0, 0, "Hello");

        MatchingAlgorithm m = new MatchingAlgorithm();

        assertTrue(m.ComputeCompatability(user1, user2).equals(new Match("a", "b", 100, 0)));
        assertTrue(m.ComputeCompatability(user2, user1).equals(new Match("a", "b", 100, 0)));

        assertTrue(m.ComputeCompatability(user1, user3).equals(new Match("a", "c", 100, 0)));
        assertTrue(m.ComputeCompatability(user3, user1).equals(new Match("a", "c", 100, 0)));

        assertTrue(m.ComputeCompatability(user1, user4).equals(new Match("a", "d", 100, 0)));
        assertTrue(m.ComputeCompatability(user4, user1).equals(new Match("a", "d", 100, 0)));

        assertTrue(m.ComputeCompatability(user2, user4).equals(new Match("b", "d", 95, 0)));
        assertTrue(m.ComputeCompatability(user4, user2).equals(new Match("b", "d", 95, 0)));

        assertTrue(m.ComputeCompatability(user3, user4).equals(new Match("c", "d", 95, 0)));
        assertTrue(m.ComputeCompatability(user4, user3).equals(new Match("c", "d", 95, 0)));
    }

    @Test
    public void testFriends() {
        Survey user1 = new Survey("a", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, 0, 0, 0, 0, 0, "Hello");
        Survey user2 = new Survey("b", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, 0, 1, 0, 0, 0, "Hello");
        Survey user3 = new Survey("c", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, 0, 0, 1, 0, 0, "Hello");
        Survey user4 = new Survey("d", "a", "b", "c", 2, 1, 2, 2, 0, 8, 11, 0, 0, 0, 1, 1, 0, 0, "Hello");

        MatchingAlgorithm m = new MatchingAlgorithm();

        assertTrue(m.ComputeCompatability(user1, user2).equals(new Match("a", "b", 100, 0)));
        assertTrue(m.ComputeCompatability(user2, user1).equals(new Match("a", "b", 100, 0)));

        assertTrue(m.ComputeCompatability(user1, user3).equals(new Match("a", "c", 100, 0)));
        assertTrue(m.ComputeCompatability(user3, user1).equals(new Match("a", "c", 100, 0)));

        assertTrue(m.ComputeCompatability(user1, user4).equals(new Match("a", "d", 100, 0)));
        assertTrue(m.ComputeCompatability(user4, user1).equals(new Match("a", "d", 100, 0)));

        assertTrue(m.ComputeCompatability(user3, user2).equals(new Match("b", "c", 93, 0)));
        assertTrue(m.ComputeCompatability(user2, user3).equals(new Match("b", "c", 93, 0)));

        assertTrue(m.ComputeCompatability(user2, user4).equals(new Match("b", "d", 93, 0)));
        assertTrue(m.ComputeCompatability(user4, user2).equals(new Match("b", "d", 93, 0)));

        assertTrue(m.ComputeCompatability(user3, user4).equals(new Match("c", "d", 93, 0)));
        assertTrue(m.ComputeCompatability(user4, user3).equals(new Match("c", "d", 93, 0)));

    }

    @Test
    public void testCompAll(){
        Survey user1 = new Survey("a","a","b","c",2,1,2,2,0,8,11,0,0,0,0,1,0,0, "Hello");
        Survey user2 = new Survey("b","a","b","c",2,1,2,2,0,8,11,0,0,0,0,1,0,0, "Hello");
        Survey user3 = new Survey("c", "B", "c", "D", 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "Sup");

        List<Survey> surveys = new ArrayList<>();
        surveys.add(user1);
        surveys.add(user2);
        surveys.add(user3);

        MatchingAlgorithm M = new MatchingAlgorithm();

        List<Match> matches =  M.ComputeCompatabilityForAll(user1, surveys);

        List<Match> check = new ArrayList<>();
        check.add(new Match("a","b",100,0));
        check.add(new Match("a","c",0,0));

        assertTrue(matches.equals(check));
    }
}
