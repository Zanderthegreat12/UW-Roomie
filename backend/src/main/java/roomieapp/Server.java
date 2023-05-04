package roomieapp;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

class Server {
    //http://localhost:4567
    public static void main(String[] args) {
        //Creates access to the query calls
        final Query q = new Query();

        //Test to make sure its working
        Spark.get("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World";
            }
        });

        //Tests to make sure we can connect to the database.
        Spark.get("/connect", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                q.getAllSurveys();
                return "Test connect successful!";
            }
        });

        /**
         * Gets the top k matches associated with the user that is logged in.
         * @param username a user's identifier
         *
         * @param numMatch the number of matches we want to display
         *
         * @return a json list of usernames that the current user has matched with.
         */
        Spark.get("/get_Kmatch", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                int numMatch = Integer.parseInt(request.queryParams("numMatch"));
                List<String> matches = q.getTopMatches(username, numMatch);
                Gson g = new Gson();
                String jsonResponse = g.toJson(matches);
                return jsonResponse;
            }
        });

        /**
         * Runs the algorithm and generates matches to put into the database.
         * @param username A String representing the name of the user needing to be matched
         */
        Spark.get("/run_alg", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                Survey user = q.getSurvey(username);

                //Takes every person who isn't the user from the database
                List<Survey> sList = new ArrayList<>(q.getAllSurveys());

                //Runs them through the matching algorithm
                MatchingAlgorithm alg = new MatchingAlgorithm();
                List<Match> mList = alg.ComputeCompatabilityForAll(user, sList);

                //Puts them back into the database under matches
                for(Match m: mList) {
                    q.setMatch(m);
                }
                return "Completed";
            }
        });

        /**
         * Gets the contact info and returns it
         */
        Spark.get("/contact", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                Gson g = new Gson();
                String jsonResponse = g.toJson(q.getContactInfo(username));
                return jsonResponse;
            }
        });

        /**
         * Checks to make sure the person's account is one within the database
         * @return true if the login is successful
         */
        Spark.get("/logIn", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return true;
            }
        });

        /**
         * Creates a new user account and updates the database accordingly.
         * @param username a String representing the name of the user
         * @param password a String representing the password of the user
         */
        Spark.get("/createUser", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                String password = request.queryParams("password");
                q.createUser(username, password);

                //SHOULD WE BE HANDLING CONTACT INFO HERE TOO??
                return true;
            }
        });

        /**
         * Creates an adds an account's data to the database of contact info.
         * @param username a String representing the name of the user.
         * @param email a String representing the email of the user.
         * @param pNum a long representing the phone number of the user.
         * @param discord a String representing the discord address of the user.
         */
        Spark.get("/createContact", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                String email = request.queryParams("email");
                long pNum = Long.parseLong(request.queryParams("pNum"));
                String discord =  request.queryParams("discord");
                q.setContactInfo(new ContactInfo(username, email, pNum, discord));
                return true;
            }
        });

        /**
         * Creates a new survey object and adds it to the database.
         * @param username a String representing the name of the user
         * @param dorm1 a String representing the user's first choice of dorm
         * @param dorm2 a String representing the user's 2nd choice of dorm
         * @param dorm3 a String representing the user's 3rd choice of dorm
         * @param roomType an int representing the preferred type of room
         *          1 = single/studio;
         *          2 = double/2 bedroom;
         *          3 = triple/3 bedroom;
         *          4 = quad/4 bedroom;
         *          5 = 5 bedroom;
         *          6 = 6 bedroom;
         * @param genderInc an int indicating if the user wants a gender inclusive room
         *          0 = doesn't want to be in gender inclusive dorm;
         *          1 = does want to be in gender inclusive dorm;
         * @param sYear an int representing the user's class year. (not standing)
         *          1 = first year at UW;
         *          2 = second year at UW;
         *          3 = third year at UW;
         *          4 = fourth year at UW;
         * @param rYear an int representing the preferred year for the roommate. (same options as sYear)
         * @param drinkingPref an int indicating if the user is ok with the roommate drinking/smoking.
         *          0 = does not tolerate drinking;
         *          1 = they themselves drink or are fine with roommate drinking;
         * @param wakeTime an int representing the hour the user wakes up.
         * @param sleepTime an int representing the hour the user goes to sleep.
         * @param heavySleep an int indicating if the user is a heavier sleeper or not.
         * @param sVert an int indicating if the user is an introvert, extrovert, or ambivert.
         * @param rVert an int indicating how outgoing the user wants their roommate to be.
         * @param sFriends an int indicating if the user wants to bring friends to the room.
         * @param rFriends an int indicating if the user is ok with the roommate bringing friends over.
         * @param sNeat an int indicating if the user is neat or messy.
         * @param rNeat an int indicating the user's preference for roommate cleanliness.
         * @param hobbies a String of all the user's hobbies.
         */
        Spark.get("/createSurvey", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                String dorm1 = request.queryParams("dorm1");
                String dorm2 = request.queryParams("dorm2");
                String dorm3 = request.queryParams("dorm3");
                int roomType = Integer.parseInt(request.queryParams("roomType"));
                int genderInc = Integer.parseInt(request.queryParams("genderInc"));
                int sYear = Integer.parseInt(request.queryParams("sYear"));
                int rYear = Integer.parseInt(request.queryParams("rYear"));
                int drinkingPref = Integer.parseInt(request.queryParams("drinkingPref"));
                int wakeTime = Integer.parseInt(request.queryParams("wakeTime"));
                int sleepTime = Integer.parseInt(request.queryParams("sleepTime"));
                int heavySleep = Integer.parseInt(request.queryParams("heavySleep"));
                int sVert = Integer.parseInt(request.queryParams("sVert"));
                int rVert = Integer.parseInt(request.queryParams("rVert"));
                int sFriends = Integer.parseInt(request.queryParams("sFriends"));
                int rFriends = Integer.parseInt(request.queryParams("rFriends"));
                int sNeat = Integer.parseInt(request.queryParams("sNeat"));
                int rNeat = Integer.parseInt(request.queryParams("rNeat"));
                String hobbies = request.queryParams("hobbies");

                Survey newSurvey = new Survey(username, dorm1, dorm2, dorm3, roomType, genderInc, sYear, rYear,
                            drinkingPref, wakeTime, sleepTime, heavySleep, sVert, rVert, sFriends, rFriends, sNeat,
                            rNeat, hobbies);

                q.setSurvey(newSurvey);
                return true; //returns true if it has successfully connected to the server
            }
        });

        /**
         *
         */
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return true;
            }
        });
    }

}
