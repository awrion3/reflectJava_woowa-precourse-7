package store.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrdersTest {
    @DisplayName("예외 테스트: 잘못된 형식으로 주문 상품과 수량을 입력한 경우")
    @ParameterizedTest
    @ValueSource(strings = {"", ",", "cola-1", "콜라-1,사이다-1", "콜라-0", "콜라-1.0", "콜라-999999", "탕수육-1"})
    void check_validate_exception(String input) {
        Products products = new Products();
        assertThatThrownBy(() -> new Orders(input, products))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
