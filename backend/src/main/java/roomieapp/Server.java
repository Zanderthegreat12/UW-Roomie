package roomieapp;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import com.google.gson.Gson;

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

        //Gets the first k matches from the database
        Spark.get("/connect", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                //Gson g = new Gson();
                //String jsonResponse = g.toJson(qTest);
                //return jsonResponse;
                q.clearTables();
                return "Test connect successful!";
            }
        });

        /**
         * Runs the algorithm and generates matches to put into the database.
         */
        Spark.get("/run_alg", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                //Takes every person who isn't the user from the database
                //Runs them through the matching algorithm
                //Puts them back into the database under matches
                /*List<Survey> sList = q.getAllSurveys(); //obviously wrong
                MatchingAlgorithm alg = new MatchingAlgorithm();
                Match m = alg.getMatches();
                q.setMatch(m);*/
                return "Completed";
            }
        });

        /**
         * Gets the contact info and returns it
         */
        Spark.get("/contact", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World";
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
    }

}
