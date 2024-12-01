package racingcar.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarsTest {
    @DisplayName("예외 테스트: 자동차 이름 목록이 잘못된 경우")
    @ParameterizedTest
    @ValueSource(strings = {"\n", "", "ana,beth,ana"})
    void check_car_names_exception(String input) {
        assertThatThrownBy(() -> new Cars(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기능 테스트: 자동차들 생성 크기 확인")
    @ParameterizedTest
    @CsvSource(value = {"ana:1", "ana,beth,cindy:3"}, delimiter = ':')
    void check_cars_execution(String input, String size) {
        Cars cars = new Cars(input);

        assertEquals(cars.getCars().size(), Integer.parseInt(size));
    }
}
