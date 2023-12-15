package software.ulpgc.kata5;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String,Command> commands = new HashMap<>();
    public static void main(String[] args) {
        commands.put("letterCounter", new LetterCounterCommand());
        Spark.port(8080);
        Spark.get("/letterCounter/:parameter", ((request, response) -> new CommandExecutor(request,response).execute("letterCounter")));
    }

    private static class CommandExecutor {
        private final Request request;
        private final Response response;
        public CommandExecutor(Request request, Response response) {
            this.request = request;
            this.response = response;
        }

        public String execute(String name){
            Command command = commands.get(name);
            Command.Output output = command.execute(input());
            response.status(output.responseCode());
            return output.response();
        }

        private Command.Input input() {
            return parameter -> oneOf(request.params(parameter), request.queryParams(parameter));
        }

        private String oneOf(String a, String b) {
            return a!=null?a:b;
        }
    }
}
