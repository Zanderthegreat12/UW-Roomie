package roomieapp;

import org.junit.*;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ConcurrentUsersTest {
    private static Query q;

    @BeforeClass
    public static void setUp() {
        q = new Query(false);
        q.clearTables();
    }

    @Test
    public void runConcurrentUsers() {
        Class<?>[] firstClass = { CreateUsers.class};
        Class<?>[] secondClass = { LoginUsers.class};

        // ParallelComputer(true,true) will run all classes and methods
        // in parallel.  (First arg for classes, second arg for methods)
        Result result1 = JUnitCore.runClasses(new ParallelComputer(false, true), firstClass);
        Result result2 = JUnitCore.runClasses(new ParallelComputer(false, true), secondClass);
        List<Failure> failures1 = result1.getFailures();
        assertTrue(failures1.size() == 0);
        List<Failure> failures2 = result2.getFailures();
        assertTrue(failures2.size() == 0);
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

    public static class LoginUsers {
        @Test
        public void thread1(){
            boolean firstTry = q.login("user1", "thread1");
            boolean secondTry = q.login("user1", "thread2");
            assertTrue(firstTry ^ secondTry);
        }

        @Test
        public void thread2() {
            boolean firstTry = q.login("user2", "thread3");
            boolean secondTry = q.login("user2", "thread4");
            assertTrue(firstTry ^ secondTry);
        }
    }
}
