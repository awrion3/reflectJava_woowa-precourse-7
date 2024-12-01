package calculator.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {
    @DisplayName("예외 테스트: 커스텀 구분자에서 숫자 문자열 형식이 올바르지 않은 경우")
    @ParameterizedTest
    @ValueSource(strings = {"//!\\n1!2.0!3", "//!\\n1!-2!3"})
    void check_numbers_exception(String input) {
        Expression expression = new Expression(input);

        assertThatThrownBy(() -> new Calculator(expression))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기능 테스트: 디폴트 구분자 숫자 문자열 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"1:2:3>6", "1,2,3>6", "1,2,>3"}, delimiter = '>')
    void check_default_delimiter_execution(String input, String inputSum) {
        Expression expression = new Expression(input);
        Calculator calculator = new Calculator(expression);
        int totalSum = calculator.getSum();

        assertEquals(totalSum, Integer.parseInt(inputSum));
    }

    @DisplayName("기능 테스트: 커스텀 구분자 숫자 문자열 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"//!\\n1!2!3>6", "//-\\n1-2-3>6", "//\"\\n1\"2\"3>6", "//*\\n1*2*3>6"}, delimiter = '>')
    void check_custom_delimiter_execution(String input, String inputSum) {
        Expression expression = new Expression(input);
        Calculator calculator = new Calculator(expression);
        int totalSum = calculator.getSum();

        assertEquals(totalSum, Integer.parseInt(inputSum));
    }
}
