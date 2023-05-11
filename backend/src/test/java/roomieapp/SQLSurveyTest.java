package roomieapp;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * unit tests for getting/setting surveys
 */
public class SQLSurveyTest {

    private static final Query querier = new Query(false);

    private static final Survey exampleSurvey = new Survey(
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

    private static final Survey updatedSurvey = new Survey(
            "user",
            "Maple",
            "Poplar",
            "Stevens Court",
            2,
            1,
            4,
            4,
            1,
            1,
            15,
            0,
            0,
            1,
            1,
            1,
            1,
            1,
            "Gaming and Laughing"
    );

    private static final Survey otherSurvey = new Survey(
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

    private static final Survey thirdSurvey = new Survey(
            "popinUser",
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


    /**
     * test getting a survey from single user
     */
    @Test
    public void testGetSurveySimple()
    {
        try {
            querier.clearTables();
            querier.createUser(exampleSurvey.username, "overHere");
            querier.setSurvey(exampleSurvey);
            Survey test = querier.getSurvey(exampleSurvey.username);
            assertTrue(exampleSurvey.equals(test));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test creating survey for single user
     */
    @Test
    public void testCreateSurvey()
    {
        try {
            querier.clearTables();
            querier.createUser(exampleSurvey.username, "wellNow");
            querier.setSurvey(exampleSurvey);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test setting a survey for user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSurveyNoUser()
    {
        try {
            querier.clearTables();
            querier.setSurvey(exampleSurvey);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting survey for user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSurveyNoUser()
    {
        try {
            querier.clearTables();
            Survey test = querier.getSurvey(exampleSurvey.username);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting survey that doesn't exist
     */
    @Test
    public void testGetSurveyNoneExists()
    {
        try {
            querier.clearTables();
            querier.createUser(exampleSurvey.username, "lolololol");
            Survey test = querier.getSurvey(exampleSurvey.username);
            assertTrue(test == null);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test updating survey for single user
     */
    @Test
    public void testUpdateSurvey()
    {
        try {
            querier.clearTables();
            querier.createUser(exampleSurvey.username, "lolololol");
            querier.setSurvey(exampleSurvey);
            Survey test = querier.getSurvey(exampleSurvey.username);
            assertTrue(exampleSurvey.equals(test));

            querier.setSurvey(updatedSurvey);
            test = querier.getSurvey(updatedSurvey.username);
            assertTrue(updatedSurvey.equals(test));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test getting/setting survey results for multiple users
     */
    @Test
    public void testSurveyMulti()
    {
        try {
            querier.clearTables();
            querier.createUser(exampleSurvey.username, "lolololol");
            querier.createUser(otherSurvey.username, "lolololol");

            querier.setSurvey(exampleSurvey);
            Survey test = querier.getSurvey(exampleSurvey.username);
            assertTrue(exampleSurvey.equals(test));

            querier.setSurvey(otherSurvey);
            test = querier.getSurvey(otherSurvey.username);
            assertTrue(otherSurvey.equals(test));

            test = querier.getSurvey(exampleSurvey.username);
            assertTrue(exampleSurvey.equals(test));
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllSurveysEmpty() {
        try {
            querier.clearTables();
            Set<Survey> test = querier.getAllSurveys();
            assertTrue(test.size() == 0);
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllSurveysSingle() {
        try {
            querier.clearTables();
            querier.createUser(exampleSurvey.username, "hardy har har");
            querier.setSurvey(exampleSurvey);

            Set<Survey> test = querier.getAllSurveys();
            assertTrue(test.size() == 1);
            for (Survey currSurvey : test) {
                assertTrue(exampleSurvey.equals(currSurvey));
            }
            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetAllSurveysMulti() {
        try {
            querier.clearTables();
            querier.createUser(exampleSurvey.username, "hardy har har");
            querier.setSurvey(exampleSurvey);
            querier.createUser(otherSurvey.username, "hee hee hee");
            querier.setSurvey(otherSurvey);
            querier.createUser(thirdSurvey.username, "pIp pOckin");
            querier.setSurvey(thirdSurvey);
            List<Survey> actual = new ArrayList<>();
            actual.add(exampleSurvey);
            actual.add(otherSurvey);
            actual.add(thirdSurvey);

            Set<Survey> test = querier.getAllSurveys();
            assertTrue(test.size() == 3);
            for (Survey currSurvey : test) {
                assertTrue(actual.contains(currSurvey));
                actual.remove(currSurvey);
            }

            querier.clearTables();
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
