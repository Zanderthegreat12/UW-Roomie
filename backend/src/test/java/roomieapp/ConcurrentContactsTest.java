package roomieapp;

import org.junit.*;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ConcurrentContactsTest {
    private static Query q;

    @BeforeClass
    public static void setUp() {
        q = new Query();
        q.clearTables();
    }

    @Test
    public void runConcurrentUsers() {
        Class<?>[] firstSet = { CreateUsers.class};
        Class<?>[] secondSet = { CreateContacts.class};
        Class<?>[] thirdSet = { UpdateContacts.class, SetContacts.class};

        // ParallelComputer(true,true) will run all classes and methods
        // in parallel.  (First arg for classes, second arg for methods)
        Result result1 = JUnitCore.runClasses(new ParallelComputer(false, true), firstSet);
        Result result2 = JUnitCore.runClasses(new ParallelComputer(false, true), secondSet);
        Result result3 = JUnitCore.runClasses(new ParallelComputer(true, true), secondSet);
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

    public static class CreateContacts {
        private static final ContactInfo user1Contact = new ContactInfo(
                "user1",
                "user1@helpMe.com",
                1028392074L,
                "user1#6969"
        );

        private static final ContactInfo user2Contact = new ContactInfo(
                "user2",
                "user2@helpMe.com",
                7392392074L,
                "user2#9696"
        );

        @Test
        public void thread1() {
            q.setContactInfo(user1Contact);
            ContactInfo test = q.getContactInfo(user1Contact.username);
            assertTrue(test.equals(user1Contact));
        }

        @Test
        public void thread2() {
            q.setContactInfo(user2Contact);
            ContactInfo test = q.getContactInfo(user2Contact.username);
            assertTrue(test.equals(user2Contact));
        }
    }

    public static class UpdateContacts {
        private static final ContactInfo user1Contact = new ContactInfo(
                "user1",
                "user_1@helpMe.com",
                9999999999L,
                "user_1#9696"
        );

        private static final ContactInfo user2Contact = new ContactInfo(
                "user2",
                "user_2@helpMe.com",
                1111111111L,
                "user_2#6969"
        );

        @Test
        public void thread1() {
            q.setContactInfo(user1Contact);
            ContactInfo test = q.getContactInfo(user1Contact.username);
            assertTrue(test.equals(user1Contact));
        }

        @Test
        public void thread2() {
            q.setContactInfo(user2Contact);
            ContactInfo test = q.getContactInfo(user2Contact.username);
            assertTrue(test.equals(user2Contact));
        }
    }

    public static class SetContacts {
        private static final ContactInfo user3Contact = new ContactInfo(
                "user3",
                "user_3@helpMe.com",
                9999999999L,
                "user_3#9696"
        );

        private static final ContactInfo user4Contact = new ContactInfo(
                "user4",
                "user_4@helpMe.com",
                1111111111L,
                "user_4#6969"
        );

        @Test
        public void thread1() {
            q.setContactInfo(user3Contact);
            ContactInfo test = q.getContactInfo(user3Contact.username);
            assertTrue(test.equals(user3Contact));
        }

        @Test
        public void thread2() {
            q.setContactInfo(user4Contact);
            ContactInfo test = q.getContactInfo(user4Contact.username);
            assertTrue(test.equals(user4Contact));
        }
    }
}
