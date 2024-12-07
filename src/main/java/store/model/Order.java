package store.model;

import static store.common.Constant.INDEX_ONE;
import static store.common.Constant.REGEX;
import static store.common.Constant.SEPARATOR;
import static store.common.Exception.INVALID_ORDER_FORMAT;
import static store.common.Exception.INVALID_ORDER_NAME;
import static store.common.Exception.INVALID_ORDER_QUANTITY;

public class Order {
    private String name;
    private int quantity;
    private Product id;

    Order(String order, Products products) {
        validateFormat(order);
        validateName(order, products);
        validateQuantity(order, products);
    }

    Order(String name, int quantity, Product id) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;
    }

    private void validateFormat(String order) {
        if (!order.matches(REGEX)) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT);
        }
    }

    private void validateName(String order, Products products) {
        int index = order.indexOf(SEPARATOR);
        name = order.substring(INDEX_ONE, index);
        id = products.findProductFirstId(name);
        if (id == null) {
            throw new IllegalArgumentException(INVALID_ORDER_NAME);
        }
    }

    private void validateQuantity(String order, Products products) {
        int index = order.indexOf(SEPARATOR);
        quantity = Integer.parseInt(order.substring(index + INDEX_ONE, order.length() - INDEX_ONE));
        int totalQuantity = products.calculateProductPairTotalQuantity(name);
        if (totalQuantity < quantity) {
            throw new IllegalArgumentException(INVALID_ORDER_QUANTITY);
        }
    }

    void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getId() {
        return id;
    }
}
