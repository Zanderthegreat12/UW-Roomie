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
        final Query q = new Query();
        //Test to make sure its working
        Spark.get("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World";
            }
        });

        Spark.get("/get_match", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String username = request.queryParams("username");
                int numMatch = Integer.parseInt(request.queryParams("numMatch"));
                //List<String> matches = q.getTopMatches(username, numMatch);
                //Gson g = new Gson();
                //String jsonResponse = g.toJson(matches);
                //return jsonResponse;
                return "hello";
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

        //Runs the algorithm and generates matches to put into the database.
        Spark.get("/run_alg", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                //Takes every person who isn't the user from the database
                //Runs them through the matching algorithm
                //Puts them back into the database under matches
                return "Hello World";
            }
        });

        //Gets the contact info and returns it
        Spark.get("/contact", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World";
            }
        });

        //Checks to make sure the person's account is one within the database
        Spark.get("/logIn", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World";
            }
        });
    }

}
