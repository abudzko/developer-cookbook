package counter;

import java.util.Random;

public class Counter {
    private static final Random RANDOM = new Random();
    private Integer value = RANDOM.nextInt();

    public Integer getValue() {
        return value;
    }

    public void add() {
        value++;
    }

}
