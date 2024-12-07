package store.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CustomerTest {
    @DisplayName("예외 테스트: 잘못된 형식으로 답변을 입력한 경우")
    @ParameterizedTest
    @ValueSource(strings = {" ", "yes", "no", "예", "아니오", "1", "0"})
    void check_validate_exception(String input) {
        assertThatThrownBy(() -> Customer.isPositive(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
