package store.model;

import static store.common.Constant.INDEX_ONE;
import static store.common.Constant.INDEX_TWO;

import java.util.List;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private String promotion;

    Product(List<String> row) {
        name = row.getFirst();
        price = Integer.parseInt(row.get(INDEX_ONE));
        quantity = Integer.parseInt(row.get(INDEX_TWO));
        updatePromotionValue(row.getLast());
    }

    Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    private void updatePromotionValue(String promotion) {
        if (promotion.equals("null")) {
            this.promotion = null;
            return;
        }
        this.promotion = promotion;
    }

    void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }
}
