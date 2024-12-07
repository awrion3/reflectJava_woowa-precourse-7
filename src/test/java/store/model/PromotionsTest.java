package store.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PromotionsTest {
    @DisplayName("기능 테스트: 파일 입력 생성 크기 확인")
    @Test
    void check_initialize_promotions_execution() {
        final int SIZE = 3;

        Promotions promotions = new Promotions();
        assertEquals(SIZE, promotions.getPromotions().size());
    }

    @DisplayName("기능 테스트: 행사 기간 확인")
    @ParameterizedTest
    @CsvSource(value = {"콜라:true", "오렌지주스:true", "감자칩:false", "에너지바:false"}, delimiter = ':')
    void check_promotions_period_execution(String name, String expectedResult) {
        Products products = new Products();
        Promotions promotions = new Promotions();

        Product product = products.findProductFirstId(name);
        boolean result = promotions.isPromotionPeriod(product);
        assertEquals(Boolean.parseBoolean(expectedResult), result);
    }
}
