package racingcar.model;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private static final String COMMA = ",";

    private final List<Car> cars = new ArrayList<>();

    public Cars(String carNames) {
        validateNames(carNames);
        validateUnique(carNames);
        createCars(carNames);
    }

    private void validateNames(String carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름 목록은 빈 문자열일 수 없습니다.");
        }
    }

    private void validateUnique(String carNames) {
        List<String> cars = new ArrayList<>();

        for (String carName : carNames.split(COMMA)) {
            if (cars.contains(carName)) {
                throw new IllegalArgumentException("[ERROR] 자동차 이름 목록에는 중복되는 이름이 없어야 합니다.");
            }
            cars.add(carName);
        }
    }

    private void createCars(String carNames) {
        for (String carName : carNames.split(COMMA)) {
            cars.add(new Car(carName));
        }
    }

    public List<Car> getCars() {
        return cars;
    }
}
