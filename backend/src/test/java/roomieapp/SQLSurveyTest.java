package roomieapp;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * unit tests for getting/setting surveys
 */
public class SQLSurveyTest {

    private static final Query querier = new Query();

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
        querier.clearTables();
        querier.createUser(exampleSurvey.username, "overHere");
        querier.setSurvey(exampleSurvey);
        Survey test = querier.getSurvey(exampleSurvey.username);
        assertTrue(exampleSurvey.equals(test));
        querier.clearTables();
    }

    /**
     * test creating survey for single user
     */
    @Test
    public void testCreateSurvey()
    {
        querier.clearTables();
        querier.createUser(exampleSurvey.username, "wellNow");
        querier.setSurvey(exampleSurvey);
        querier.clearTables();
    }

    /**
     * test setting a survey for user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSurveyNoUser()
    {
        querier.clearTables();
        querier.setSurvey(exampleSurvey);
        querier.clearTables();
    }

    /**
     * test getting survey for user not in database
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSurveyNoUser()
    {
        querier.clearTables();
        Survey test = querier.getSurvey(exampleSurvey.username);
        querier.clearTables();
    }

    /**
     * test getting survey that doesn't exist
     */
    @Test
    public void testGetSurveyNoneExists()
    {
        querier.clearTables();
        querier.createUser(exampleSurvey.username, "lolololol");
        Survey test = querier.getSurvey(exampleSurvey.username);
        assertTrue(test == null);
        querier.clearTables();
    }

    /**
     * test updating survey for single user
     */
    @Test
    public void testUpdateSurvey()
    {
        querier.clearTables();
        querier.createUser(exampleSurvey.username, "lolololol");
        querier.setSurvey(exampleSurvey);
        Survey test = querier.getSurvey(exampleSurvey.username);
        assertTrue(exampleSurvey.equals(test));

        querier.setSurvey(updatedSurvey);
        test = querier.getSurvey(updatedSurvey.username);
        assertTrue(updatedSurvey.equals(test));
        querier.clearTables();
    }

    /**
     * test getting/setting survey results for multiple users
     */
    @Test
    public void testSurveyMulti()
    {
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
    }

    @Test
    public void testGetAllSurveysEmpty() {
        querier.clearTables();
        List<Survey> test = querier.getAllSurveys();
        assertTrue(test.size() == 0);
        querier.clearTables();
    }

    @Test
    public void testGetAllSurveysSingle() {
        querier.clearTables();
        querier.createUser(exampleSurvey.username, "hardy har har");
        querier.setSurvey(exampleSurvey);

        List<Survey> test = querier.getAllSurveys();
        assertTrue(test.size() == 1);
        assertTrue(test.get(0).equals(exampleSurvey));
        querier.clearTables();
    }

    @Test
    public void testGetAllSurveysMulti() {
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

        List<Survey> test = querier.getAllSurveys();
        assertTrue(test.size() == 3);
        for(int i = 0; i < test.size(); i++) {
            assertTrue(actual.contains(test.get(i)));
            actual.remove(test.get(i));
        }

        querier.clearTables();
    }
}
