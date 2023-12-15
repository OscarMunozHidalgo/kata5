package software.ulpgc.kata5;

public class LetterCounterCommand implements Command{
    @Override
    public Output execute(Input input) {
        return execute(input.get(":parameter"));
    }

    private Output execute(String word) {
        return new Output() {
            @Override
            public String response() {
                return Integer.toString(calculate(word));
            }

            @Override
            public int responseCode() {
                return 200;
            }
        };
    }

    private int calculate(String word) {
        return word.length();
    }
}
