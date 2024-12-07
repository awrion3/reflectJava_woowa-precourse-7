package store.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ProductsTest {
    @DisplayName("기능 테스트: 파일 입력 생성 크기 확인")
    @Test
    void check_initialize_products_execution() {
        final int SIZE = 18;

        Products products = new Products();
        assertEquals(SIZE, products.getProducts().size());
    }

    @DisplayName("기능 테스트: 상품의 행사 및 정가 총 수량 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"콜라:20", "물:10"}, delimiter = ':')
    void check_product_pair_quantity_execution(String name, String expectedQuantity) {
        Products products = new Products();
        int totalQuantity = products.calculateProductPairTotalQuantity(name);
        assertEquals(Integer.parseInt(expectedQuantity), totalQuantity);
    }
}
