package roomieapp;

import org.junit.*;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ConcurrentSurveysTest {
    private static Query q;

    @BeforeClass
    public static void setUp() {
        q = new Query();
        q.clearTables();
    }

    @Test
    public void runConcurrentUsers() {
        Class<?>[] firstSet = { CreateUsers.class};
        Class<?>[] secondSet = { CreateSurveys.class};
        Class<?>[] thirdSet = { UpdateSurveys.class, SetSurveys.class};

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

    public static class CreateSurveys {
        private final Survey user1Survey = new Survey(
                "user1",
                "Poplar",
                "Maple",
                "Stevens Court",
                2,
                1,
                3,
                3,
                1,
                9,
                23,
                0,
                0,
                1,
                1,
                1,
                1,
                1,
                "Gaming"
        );

        private final Survey user2Survey = new Survey(
                "user2",
                "Willow",
                "Mcmahon",
                "Elm",
                4,
                0,
                1,
                1,
                0,
                10,
                1,
                1,
                2,
                2,
                0,
                0,
                0,
                1,
                "Football"
        );

        @Test
        public void thread1(){
            q.setSurvey(user1Survey);
            Survey user1Test = q.getSurvey(user1Survey.username);
            assertTrue(user1Test.equals(user1Survey));
        }

        @Test
        public void thread2() {
            q.setSurvey(user2Survey);
            Survey user2Test = q.getSurvey(user2Survey.username);
            assertTrue(user2Test.equals(user2Survey));
        }
    }

    public static class UpdateSurveys {
        private final Survey user1Survey = new Survey(
                "user1",
                "Poplar",
                "Maple",
                "Stevens Court",
                1,
                0,
                3,
                3,
                1,
                11,
                3,
                1,
                0,
                1,
                1,
                1,
                1,
                1,
                "Gaming"
        );

        private final Survey user2Survey = new Survey(
                "user2",
                "Mcmahon",
                "Willow",
                "Elm",
                2,
                0,
                1,
                1,
                0,
                10,
                1,
                1,
                1,
                2,
                1,
                0,
                1,
                1,
                "Football"
        );

        @Test
        public void thread1(){
            q.setSurvey(user1Survey);
            Survey user1Test = q.getSurvey(user1Survey.username);
            assertTrue(user1Test.equals(user1Survey));
        }

        @Test
        public void thread2() {
            q.setSurvey(user2Survey);
            Survey user2Test = q.getSurvey(user2Survey.username);
            assertTrue(user2Test.equals(user2Survey));
        }
    }

    public static class SetSurveys {
        private final Survey user3Survey = new Survey(
                "user3",
                "Poplar",
                "Maple",
                "Stevens Court",
                2,
                1,
                3,
                3,
                1,
                9,
                23,
                0,
                0,
                1,
                1,
                1,
                1,
                1,
                "Gaming"
        );

        private final Survey user4Survey = new Survey(
                "user4",
                "Willow",
                "Mcmahon",
                "Elm",
                4,
                0,
                1,
                1,
                0,
                10,
                1,
                1,
                2,
                2,
                0,
                0,
                0,
                1,
                "Football"
        );

        @Test
        public void thread1(){
            q.setSurvey(user3Survey);
            Survey user2Test = q.getSurvey(user3Survey.username);
            assertTrue(user2Test.equals(user3Survey));
        }

        @Test
        public void thread2() {
            q.setSurvey(user4Survey);
            Survey user2Test = q.getSurvey(user4Survey.username);
            assertTrue(user2Test.equals(user4Survey));
        }
    }
}

