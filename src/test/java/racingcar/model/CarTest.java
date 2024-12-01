package racingcar.model;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {
    @DisplayName("예외 테스트: 자동차 이름이 잘못된 경우")
    @ParameterizedTest
    @ValueSource(strings = {"\n", "", "9", " ", "@car", "longCarName"})
    void check_car_name_exception(String input) {
        assertThatThrownBy(() -> new Car(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기능 테스트: 자동차 위치 갱신 확인")
    @ParameterizedTest
    @CsvSource(value = {"ana,3:0", "beth,4:1", "cindy,5:1"}, delimiter = ':')
    void check_car_position_execution(String input, String position) {
        final String COMMA = ",";
        String name = input.split(COMMA)[0];
        int number = Integer.parseInt(input.split(COMMA)[1]);

        Car car = new Car(name);
        assertRandomNumberInRangeTest(
                () -> {
                    car.updatePosition();
                },
                number
        );

        int updatedPosition = car.getPosition();
        assertEquals(updatedPosition, Integer.parseInt(position));
    }
}
