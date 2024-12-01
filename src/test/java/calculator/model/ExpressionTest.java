package calculator.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ExpressionTest {
    @DisplayName("예외 테스트: 빈 문자열 혹은 구분자 형식이 올바르지 않은 경우")
    @ParameterizedTest
    @ValueSource(strings = {"\n", " ", "1:@:3", "1:2,3", "//8\\n18283", "//,\\n1,2,3"})
    void check_delimiter_exception(String input) {
        assertThatThrownBy(() -> new Expression(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외 테스트: 디폴트 구분자에서 숫자 문자열 형식이 올바르지 않은 경우")
    @ParameterizedTest
    @ValueSource(strings = {"1:2.0:3", "1:-2:3"})
    void check_default_delimiter_exception(String input) {
        assertThatThrownBy(() -> new Expression(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기능 테스트: 디폴트 구분자 및 숫자 문자열 추출 확인")
    @ParameterizedTest
    @CsvSource(value = {"1:2:3>:", "1,2,3>,"}, delimiter = '>')
    void check_default_delimiter_execution(String input, String inputDelimiter) {
        Expression expression = new Expression(input);
        String delimiter = expression.getDelimiter();
        String numbers = expression.getNumbers();

        assertTrue(delimiter.equals(inputDelimiter) && numbers.equals(input));
    }

    @DisplayName("기능 테스트: 커스텀 구분자 및 숫자 문자열 추출 확인")
    @ParameterizedTest
    @CsvSource(value = {"//!\\n1!2!3>!", "//-\\n1-2-3>-"}, delimiter = '>')
    void check_custom_delimiter_execution(String input, String inputDelimiter) {
        final int CUSTOM_DELIMITER_FINISH_INDEX = 5;

        Expression expression = new Expression(input);
        String delimiter = expression.getDelimiter();
        String numbers = expression.getNumbers();

        String inputNumbers = input.substring(CUSTOM_DELIMITER_FINISH_INDEX);

        assertTrue(delimiter.equals(inputDelimiter) && numbers.equals(inputNumbers));
    }
}
