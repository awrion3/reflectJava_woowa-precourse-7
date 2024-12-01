package racingcar.model;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RaceTest {
    @DisplayName("예외 테스트: 시도할 횟수가 잘못된 경우")
    @ParameterizedTest
    @ValueSource(strings = {"\n", "", "3회", "3.0", "-1", "0"})
    void check_race_round_exception(String input) {
        assertThatThrownBy(() -> new Race(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기능 테스트: 최종 우승자 명단 평가 동점자 반영 확인")
    @ParameterizedTest
    @CsvSource(value = {"ana,beth:1:3:4:beth", "ana,beth:1:4:4:ana,beth"}, delimiter = ':')
    void check_race_winners_execution(String names, String round, String number1, String number2, String winners) {
        final String COMMA = ",";

        Cars cars = new Cars(names);
        assertRandomNumberInRangeTest(
                () -> {
                    for (Car car : cars.getCars()) {
                        car.updatePosition();
                    }
                },
                Integer.parseInt(number1),
                Integer.parseInt(number2)
        );

        Race race = new Race(round);
        race.evaluateWinners(cars);
        String raceWinners = String.join(COMMA, race.getWinners());

        assertEquals(raceWinners, winners);
    }
}
