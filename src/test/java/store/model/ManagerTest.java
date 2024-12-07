package store.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ManagerTest {
    @DisplayName("기능 테스트: 증정 상품 목록 크기 확인")
    @ParameterizedTest
    @CsvSource(value = {"[콜라-3],[에너지바-5]:1"}, delimiter = ':')
    void check_bonus_promotion_execution(String input, String size) {
        Products products = new Products();
        Promotions promotions = new Promotions();
        Orders orders = new Orders(input, products);
        Manager manager = new Manager(orders, products, promotions);

        List<Order> bonusOrders = manager.getBonusPromotionOrders();
        assertEquals(bonusOrders.size(), Integer.parseInt(size));
    }

    @DisplayName("기능 테스트: 정가 결제 상품 수량 확인")
    @ParameterizedTest
    @CsvSource(value = {"[콜라-3],[에너지바-5]:0:5"}, delimiter = ':')
    void check_no_promotion_execution(String input, String quantity1, String quantity2) {
        Products products = new Products();
        Promotions promotions = new Promotions();
        Orders orders = new Orders(input, products);
        Manager manager = new Manager(orders, products, promotions);

        List<Order> noPromotionOrders = manager.getNoPromotionOrders();
        boolean result1 = isEqualQuantity(noPromotionOrders.getFirst(), quantity1);
        boolean result2 = isEqualQuantity(noPromotionOrders.getLast(), quantity2);
        assertTrue(result1 && result2);
    }

    private boolean isEqualQuantity(Order order, String quantity) {
        return (order.getQuantity() == Integer.parseInt(quantity));
    }
}
