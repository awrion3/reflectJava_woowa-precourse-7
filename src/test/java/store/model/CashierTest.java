package store.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CashierTest {
    @DisplayName("기능 테스트: 멤버십 할인 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"[콜라-3],[에너지바-5]:Y:-3000", "[콜라-3],[에너지바-5]:N:0",
            "[정식도시락-8]:Y:-8000"}, delimiter = ':')
    void check_membership_discount_execution(String input, String response, String expectedDiscount) {
        Products products = new Products();
        Promotions promotions = new Promotions();

        Orders orders = new Orders(input, products);
        Manager manager = new Manager(orders, products, promotions);
        Cashier cashier = new Cashier(response, orders, manager);

        assertEquals(cashier.getMembershipDiscount(), Integer.parseInt(expectedDiscount));
    }

    @DisplayName("기능 테스트: 총 구매량 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"[콜라-3],[에너지바-5]:N:8", "[오렌지주스-2]:N:2"}, delimiter = ':')
    void check_total_quantity_execution(String input, String response, String expectedCost) {
        Products products = new Products();
        Promotions promotions = new Promotions();

        Orders orders = new Orders(input, products);
        Manager manager = new Manager(orders, products, promotions);
        Cashier cashier = new Cashier(response, orders, manager);

        assertEquals(cashier.getTotalQuantity(orders), Integer.parseInt(expectedCost));
    }

    @DisplayName("기능 테스트: 내실 돈 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"[콜라-3],[에너지바-5]:N:12000", "[콜라-3],[에너지바-5]:Y:9000",
            "[물-10]:N:5000", "[물-10]:Y:3500"}, delimiter = ':')
    void check_final_cost_execution(String input, String response, String expectedCost) {
        Products products = new Products();
        Promotions promotions = new Promotions();

        Orders orders = new Orders(input, products);
        Manager manager = new Manager(orders, products, promotions);
        Cashier cashier = new Cashier(response, orders, manager);

        assertEquals(cashier.getFinalCost(), Integer.parseInt(expectedCost));
    }
}
