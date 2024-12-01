package racingcar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Race {
    private static final int MIN_ROUND_COUNT = 1;

    private int round;
    private List<String> winners = new ArrayList<>();

    public Race(String raceRound) {
        validateType(raceRound);
        validateCount(raceRound);
    }

    private void validateType(String raceRound) {
        try {
            Integer.parseInt(raceRound);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 시도할 횟수는 정수 값이어야 합니다.");
        }
    }

    private void validateCount(String raceRound) {
        int roundCount = Integer.parseInt(raceRound);
        if (roundCount < MIN_ROUND_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도할 횟수는 1 이상의 값이어야 합니다.");
        }
        round = roundCount;
    }

    public void evaluateWinners(Cars cars) {
        int maxPosition = cars.getCars().stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(-1);

        winners = cars.getCars().stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public int getRound() {
        return round;
    }

    public List<String> getWinners() {
        return winners;
    }
}
