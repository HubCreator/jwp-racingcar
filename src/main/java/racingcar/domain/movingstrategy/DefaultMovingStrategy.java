package racingcar.domain.movingstrategy;

import java.util.Random;

public class DefaultMovingStrategy implements MovingStrategy {

    private static final int RANDOM_MIN_RANGE = 0;
    private static final int RANDOM_MAX_RANGE = 9;
    private static final int MOVE_STANDARD = 4;

    private static final Random RANDOM = new Random();

    public int createRandomNumber() {
        return RANDOM.nextInt(RANDOM_MAX_RANGE - RANDOM_MIN_RANGE + 1);
    }

    @Override
    public boolean movable() {
        return createRandomNumber() >= MOVE_STANDARD;
    }
}
