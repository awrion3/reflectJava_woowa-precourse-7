package store.model;

import static store.common.Constant.DELIMITER;
import static store.common.Exception.INVALID_INPUT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Orders {
    List<Order> orders = new ArrayList<>();

    public Orders(String response, Products products) {
        validateResponse(response);
        createOrders(response, products);
    }

    private void validateResponse(String response) {
        if (response == null || response.isEmpty() || response.equals(DELIMITER)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    private void createOrders(String response, Products products) {
        List<String> items = Arrays.asList(response.split(DELIMITER));
        for (String item : items) {
            validateItem(item);
            orders.add(new Order(item, products));
        }
    }

    private void validateItem(String item) {
        if (item == null || item.isEmpty()) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
