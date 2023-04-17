package racingcar.domain;

import racingcar.domain.movingstrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class RacingGame {

    private Cars cars;
    private TryCount tryCount;

    public RacingGame(final Names carNames, final TryCount tryCount) {
        final List<Car> collect = carNames.getNames()
                .stream()
                .map(Car::new)
                .collect(Collectors.toList());

        this.cars = new Cars(collect);
        this.tryCount = tryCount;
    }

    public List<Cars> run(final MovingStrategy strategy) {
        final List<Cars> result = new ArrayList<>();
        for (int i = 0; i < tryCount.getCount(); i++) {
            moveCars(strategy);
            result.add(cars);
        }
        return result;
    }

    private void moveCars(final MovingStrategy strategy) {
        this.cars = cars.move(strategy);
        tryCount = tryCount.decreaseCount();
    }

    public Cars getWinners() {
        final List<Car> result = cars.getCars()
                .stream()
                .filter(Car::isWinner)
                .collect(Collectors.toList());
        return new Cars(result);
    }
}
