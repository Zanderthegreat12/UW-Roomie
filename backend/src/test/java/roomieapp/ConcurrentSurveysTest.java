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
        private static boolean firstThread = false;
        private static boolean secondThread = false;
        private static boolean thirdThread = false;
        private static boolean fourthThread = false;

        @Test
        public void thread1() {
            firstThread = q.createUser("user1", "thread1");
        }

        @Test
        public void thread2() {
            secondThread = q.createUser("user1", "thread2");
        }
        @Test
        public void thread3() {
            thirdThread = q.createUser("user2", "thread3");
        }

        @Test
        public void thread4() {
            fourthThread = q.createUser("user2", "thread4");
        }

        @AfterClass
        public static void works() {
            assertTrue(firstThread ^ secondThread);
            assertTrue(thirdThread ^ fourthThread);
        }
    }

    public static class CreateSurveys {
        private final Survey exampleSurvey = new Survey(
                "user",
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

        private final Survey otherSurvey = new Survey(
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
            
        }

        @Test
        public void thread2() {

        }
    }

    public static class UpdateSurveys {
        @Test
        public void thread1(){

        }

        @Test
        public void thread2() {

        }
    }

    public static class SetSurveys {
        @Test
        public void thread1(){

        }

        @Test
        public void thread2() {

        }
    }
}

