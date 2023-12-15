package software.ulpgc.kata5;

public interface Command {
    Output execute(Input input);
    interface Input{
        String get(String Parameters);
    }
    interface Output{
        String response();
        int responseCode();
    }
}
