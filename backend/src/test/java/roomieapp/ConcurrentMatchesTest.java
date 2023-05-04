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
        q = new Query();
        q.clearTables();
    }

    @Test
    public void runConcurrentUsers() {
        Class<?>[] firstSet = { CreateUsers.class};
        Class<?>[] secondSet = { CreateMatches.class};
        Class<?>[] thirdSet = { UpdateMatches.class, SetMatches.class, GetTopMatches.class};

        // ParallelComputer(true,true) will run all classes and methods
        // in parallel.  (First arg for classes, second arg for methods)
        Result result1 = JUnitCore.runClasses(new ParallelComputer(false, true), firstSet);
        Result result2 = JUnitCore.runClasses(new ParallelComputer(false, true), secondSet);
        Result result3 = JUnitCore.runClasses(new ParallelComputer(true, true), thirdSet);
        List<Failure> failures1 = result1.getFailures();
        assertTrue(failures1.size() == 0);
        List<Failure> failures2 = result2.getFailures();
        assertTrue(failures2.size() == 0);
        List<Failure> failures3 = result3.getFailures();
        assertTrue(failures3.size() == 0);
    }

    @AfterClass
    public static void finish() {
        q.clearTables();
    }

    public static class CreateUsers {

        @Test
        public void thread1() {
            assertTrue(q.createUser("user1", "thread1"));
        }

        @Test
        public void thread2() {
            assertTrue(q.createUser("user2", "thread2"));
        }
        @Test
        public void thread3() {
            assertTrue(q.createUser("user3", "thread3"));
        }

        @Test
        public void thread4() {
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
        public void thread1() {
            q.setMatch(match1);
            Match test = q.getMatch(match1.user1, match1.user2);
            assertTrue(test.equals(match1));
        }

        @Test
        public void thread2() {
            q.setMatch(match2);
            Match test = q.getMatch(match2.user1, match2.user2);
            assertTrue(test.equals(match2));
        }

        @Test
        public void thread3() {
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
        public void thread1() {
            q.updateMatchStatus(match1.user1, match1.user2, match1.matchStatus);
            Match test = q.getMatch(match1.user1, match1.user2);
            assertTrue(test.equals(match1));
        }

        @Test
        public void thread2() {
            q.updateCompatibility(match2.user1, match2.user2, match2.compatibility);
            Match test = q.getMatch(match2.user1, match2.user2);
            assertTrue(test.equals(match2));
        }

        @Test
        public void thread3() {
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
        public void thread1() {
            q.setMatch(match1);
            Match test = q.getMatch(match1.user1, match1.user2);
            assertTrue(test.equals(match1));
        }

        @Test
        public void thread2() {
            q.setMatch(match2);
            Match test = q.getMatch(match2.user1, match2.user2);
            assertTrue(test.equals(match2));
        }

        @Test
        public void thread3() {
            q.setMatch(match3);
            Match test = q.getMatch(match3.user1, match3.user2);
            assertTrue(test.equals(match3));
        }
    }

    public static class GetTopMatches {
        @Test
        public void thread1() {
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
            List<String> test = q.getTopMatches("user1", 2);
            assertTrue(test.size() <= 2);
            if(test.size() == 2) {
                assertTrue(test.get(0).equals(match1.user2));
                assertTrue(test.get(1).equals(match2.user2));
            } else if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1.user2) ||
                        test.get(0).equals(match2.user2));
            }
        }

        @Test
        public void thread2() {
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
            List<String> test = q.getTopMatches("user2", 2);
            assertTrue(test.size() <= 2);
            if(test.size() == 2) {
                if(test.contains(match1.user1) && test.contains(match2.user2)) {
                    assertTrue(test.get(0).equals(match1.user1));
                    assertTrue(test.get(1).equals(match2.user2));
                } else if(test.contains(match1.user1) && test.contains(match3.user2)) {
                    assertTrue(test.get(0).equals(match1.user1));
                    assertTrue(test.get(1).equals(match3.user2));
                } else {
                    assertTrue(test.get(0).equals(match2.user2));
                    assertTrue(test.get(1).equals(match3.user2));
                }
            } else if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1.user1) ||
                        test.get(0).equals(match2.user2) || test.get(0).equals(match2.user2));
            }

        }

        @Test
        public void thread3() {
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
            List<String> test = q.getTopMatches("user3", 2);
            assertTrue(test.size() <= 2);
            if(test.size() == 2) {
                assertTrue(test.get(0).equals(match1.user1));
                assertTrue(test.get(1).equals(match2.user1));
            } else if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1.user1) ||
                        test.get(0).equals(match2.user1));
            }
        }

        @Test
        public void thread4() {
            Match match1 = new Match(
                    "user2",
                    "user4",
                    76,
                    0
            );
            List<String> test = q.getTopMatches("user4", 2);
            assertTrue(test.size() <= 1);
            if(test.size() == 1) {
                assertTrue(test.get(0).equals(match1.user1));
            }
        }
    }
}

