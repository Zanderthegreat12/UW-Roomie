package roomieapp;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import com.google.gson.Gson;

import static spark.Spark.*;

class Server {

    //http://localhost:4567
    public static void main(String[] args) {
        Spark.get("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World";
            }
        });
    }

}
