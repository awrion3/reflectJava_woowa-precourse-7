package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private static final String REGEX_CHARACTER = ".*[^a-zA-Z가-힣].*";
    private static final int MAX_LENGTH = 5;
    private static final int MIN_NUMBER = 4;

    private final String name;
    private int position;

    public Car(String name) {
        validateString(name);
        validateLength(name);
        validateName(name);
        this.name = name;
    }

    private void validateString(String carName) {
        if (carName == null || carName.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 빈 문자열이 될 수 없습니다.");
        }
    }

    private void validateLength(String carName) {
        if (carName.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 5문자 이하여야 합니다.");
        }
    }

    private void validateName(String carName) {
        if (carName.matches(REGEX_CHARACTER)) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 영어 혹은 한글 문자로만 이루어져야 합니다.");
        }
    }

    public void updatePosition() {
        if (isProgress()) {
            position++;
        }
    }

    private boolean isProgress() {
        return generateRandomNumber() >= MIN_NUMBER;
    }

    private int generateRandomNumber() {
        return Randoms.pickNumberInRange(0, 9);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
