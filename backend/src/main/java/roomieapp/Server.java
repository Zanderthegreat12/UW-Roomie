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
                //q.getAllSurveys();
                Gson g = new Gson();
                String jsonResponse = g.toJson(q.getAllSurveys());
                return jsonResponse;
                //return "Test connect successful!";
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
        Spark.get("/getKmatch", new Route() {
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
        Spark.get("/runAlg", new Route() {
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
         * Checks to make sure the person's account is one within the database
         * @return true if the login is successful
         */
        Spark.get("/logIn", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                String password = request.queryParams("password");
                return q.login(username, password);
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
         * Gets the contact info and returns it
         */
        Spark.get("/getContact", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                Gson g = new Gson();
                String jsonResponse = g.toJson(q.getContactInfo(username));
                return jsonResponse;
            }
        });

        /**
         * Creates a new survey based on the user's answers (or changes) and uploads it to the database. If the user
         * has already created a survey, it updates it instead.
         * @param str A string of every answer to the survey split by one space.
         */
        Spark.get("/createSurvey", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String toParse = request.queryParams("str");
                String[] parsed = toParse.split(" ");
                for(String s : parsed){
                    s.trim();
                }

                Survey newSurvey = new Survey(parsed[0], parsed[1], parsed[2], parsed[3], Integer.parseInt(parsed[4]),
                        Integer.parseInt(parsed[5]), Integer.parseInt(parsed[6]), Integer.parseInt(parsed[7]),
                        Integer.parseInt(parsed[8]), Integer.parseInt(parsed[9]), Integer.parseInt(parsed[10]),
                        Integer.parseInt(parsed[11]), Integer.parseInt(parsed[12]), Integer.parseInt(parsed[13]),
                        Integer.parseInt(parsed[14]), Integer.parseInt(parsed[15]), Integer.parseInt(parsed[16]),
                        Integer.parseInt(parsed[17]), parsed[18]);

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