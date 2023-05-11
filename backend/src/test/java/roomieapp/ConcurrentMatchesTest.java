package roomieapp;

import org.junit.*;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ConcurrentMatchesTest {
    private static Query q;

    @BeforeClass
    public static void setUp() {
        try {
            q = new Query(false);
            q.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void runConcurrentUsers() {
        Class<?>[] firstSet = { CreateUsers.class};
        Class<?>[] secondSet = { CreateMatches.class};
        Class<?>[] thirdSet = { UpdateMatches.class, SetMatches.class, GetTopMatches.class};

        // ParallelComputer(true,true) will run all classes and methods
        // in parallel.  (First arg for classes, second arg for methods)
        try {
            Result result1 = JUnitCore.runClasses(new ParallelComputer(false, true), firstSet);
            Result result2 = JUnitCore.runClasses(new ParallelComputer(false, true), secondSet);
            Result result3 = JUnitCore.runClasses(new ParallelComputer(true, true), thirdSet);
            List<Failure> failures1 = result1.getFailures();
            assertTrue(failures1.size() == 0);
            List<Failure> failures2 = result2.getFailures();
            assertTrue(failures2.size() == 0);
            List<Failure> failures3 = result3.getFailures();
            assertTrue(failures3.size() == 0);
        } catch (Exception e) {

        }
    }

    @AfterClass
    public static void finish() {
        q.clearTables();
    }

    public static class CreateUsers {

        @Test
        public void thread1() throws Exception {
            assertTrue(q.createUser("user1", "thread1"));
        }

        @Test
        public void thread2() throws Exception{
            assertTrue(q.createUser("user2", "thread2"));
        }
        @Test
        public void thread3() throws Exception{
            assertTrue(q.createUser("user3", "thread3"));
        }

        @Test
        public void thread4() throws Exception{
            assertTrue(q.createUser("user4", "thread4"));
        }
    }

    public static class CreateMatches {
        private static final Match match1 = new Match(
                "user1",
                "user2",
                90,
                0
        );

        private static final Match match2 = new Match(
                "user3",
                "user4",
                76,
                1
        );

        private static final Match match3 = new Match(
                "user1",
                "user4",
                50,
                3
        );

        @Test
        public void thread1() throws Exception {
            q.setMatch(match1);
            Match test = q.getMatch(match1.user1, match1.user2);
            assertTrue(test.equals(match1));
        }

        @Test
        public void thread2() throws Exception {
            q.setMatch(match2);
            Match test = q.getMatch(match2.user1, match2.user2);
            assertTrue(test.equals(match2));
        }

        @Test
        public void thread3() throws Exception {
            q.setMatch(match3);
            Match test = q.getMatch(match3.user1, match3.user2);
            assertTrue(test.equals(match3));
        }
    }

    public static class UpdateMatches {
        private static final Match match1 = new Match(
                "user1",
                "user2",
                90,
                2
        );

        private static final Match match2 = new Match(
                "user3",
                "user4",
                66,
                1
        );

        private static final Match match3 = new Match(
                "user1",
                "user4",
                98,
                3
        );

        @Test
        public void thread1() throws Exception {
            q.updateMatchStatus(match1.user1, match1.user2, match1.matchStatus);
            Match test = q.getMatch(match1.user1, match1.user2);
            assertTrue(test.equals(match1));
        }

        @Test
        public void thread2() throws Exception {
            q.updateCompatibility(match2.user1, match2.user2, match2.compatibility);
            Match test = q.getMatch(match2.user1, match2.user2);
            assertTrue(test.equals(match2));
        }

        @Test
        public void thread3() throws Exception {
            q.updateCompatibility(match3.user1, match3.user2, match3.compatibility);
            Match test = q.getMatch(match3.user1, match3.user2);
            assertTrue(test.equals(match3));
        }
    }

    public static class SetMatches {
        private static final Match match1 = new Match(
                "user1",
                "user3",
                80,
                0
        );

        private static final Match match2 = new Match(
                "user2",
                "user4",
                76,
                0
        );

        private static final Match match3 = new Match(
                "user2",
                "user3",
                88,
                0
        );

        @Test
        public void thread1() throws Exception {
            q.setMatch(match1);
            Match test = q.getMatch(match1.user1, match1.user2);
            assertTrue(test.equals(match1));
        }

        @Test
        public void thread2() throws Exception {
            q.setMatch(match2);
            Match test = q.getMatch(match2.user1, match2.user2);
            assertTrue(test.equals(match2));
        }

        @Test
        public void thread3() throws Exception {
            q.setMatch(match3);
            Match test = q.getMatch(match3.user1, match3.user2);
            assertTrue(test.equals(match3));
        }
    }

    public static class GetTopMatches {
        @Test
        public void thread1() throws Exception {
            Match match1 = new Match(
                    "user1",
                    "user2",
                    90,
                    0
            );
            Match match2 = new Match(
                    "user1",
                    "user3",
                    80,
                    0
            );
            List<Match> test = q.getTopMatches("user1", 2);
            assertTrue(test.size() <= 2);
            if(test.size() == 2) {
                assertTrue(test.get(0).equals(match1));
                assertTrue(test.get(1).equals(match2));
            } else if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1) ||
                        test.get(0).equals(match2));
            }
        }

        @Test
        public void thread2() throws Exception {
            Match match1 = new Match(
                    "user1",
                    "user2",
                    90,
                    0
            );
            Match match2 = new Match(
                    "user2",
                    "user4",
                    76,
                    0
            );
            Match match3 = new Match(
                    "user2",
                    "user3",
                    88,
                    0
            );
            List<Match> test = q.getTopMatches("user2", 2);
            assertTrue(test.size() <= 2);
            if(test.size() == 2) {
                if(test.contains(match1) && test.contains(match2)) {
                    assertTrue(test.get(0).equals(match1));
                    assertTrue(test.get(1).equals(match2));
                } else if(test.contains(match1) && test.contains(match3)) {
                    assertTrue(test.get(0).equals(match1));
                    assertTrue(test.get(1).equals(match3));
                } else {
                    assertTrue(test.get(0).equals(match2));
                    assertTrue(test.get(1).equals(match3));
                }
            } else if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1) ||
                        test.get(0).equals(match2) || test.get(0).equals(match2));
            }

        }

        @Test
        public void thread3() throws Exception {
            Match match2 = new Match(
                    "user1",
                    "user3",
                    80,
                    0
            );
            Match match1 = new Match(
                    "user2",
                    "user3",
                    88,
                    0
            );
            List<Match> test = q.getTopMatches("user3", 2);
            assertTrue(test.size() <= 2);
            if(test.size() == 2) {
                assertTrue(test.get(0).equals(match1));
                assertTrue(test.get(1).equals(match2));
            } else if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1) ||
                        test.get(0).equals(match2));
            }
        }

        @Test
        public void thread4() throws Exception {
            Match match1 = new Match(
                    "user2",
                    "user4",
                    76,
                    0
            );
            List<Match> test = q.getTopMatches("user4", 2);
            assertTrue(test.size() <= 1);
            if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1));
            }
        }
    }
}

