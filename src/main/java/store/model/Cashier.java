package store.model;

import static store.common.Constant.VALUE_MAX;
import static store.common.Constant.VALUE_NEGATIVE;
import static store.common.Constant.VALUE_PERCENT;
import static store.common.Constant.VALUE_ZERO;

public class Cashier {
    private int membershipDiscount = VALUE_ZERO;
    private int bonusDiscount = VALUE_ZERO;
    private int totalCost = VALUE_ZERO;

    public Cashier(String response, Orders orders, Manager manager) {
        calculateMembershipDiscount(response, manager);
        calculateBonusDiscount(manager);
        calculateTotalCost(orders);
    }

    private void calculateMembershipDiscount(String response, Manager manager) {
        if (!Customer.isPositive(response)) {
            return;
        }
        for (Order order : manager.getNoPromotionOrders()) {
            Product id = order.getId();
            membershipDiscount += order.getQuantity() * id.getPrice();
        }
        updateMembershipDiscount();
    }

    private void updateMembershipDiscount() {
        membershipDiscount *= VALUE_PERCENT;
        if (membershipDiscount > VALUE_MAX) {
            membershipDiscount = VALUE_MAX;
        }
        membershipDiscount *= VALUE_NEGATIVE;
    }

    private void calculateBonusDiscount(Manager manager) {
        for (Order order : manager.getBonusPromotionOrders()) {
            Product id = order.getId();
            bonusDiscount += order.getQuantity() * id.getPrice();
        }
        bonusDiscount *= VALUE_NEGATIVE;
    }

    public int calculateOrderCost(Order order) {
        Product id = order.getId();
        return order.getQuantity() * id.getPrice();
    }

    private void calculateTotalCost(Orders orders) {
        for (Order order : orders.getOrders()) {
            totalCost += calculateOrderCost(order);
        }
    }

    private int calculateTotalQuantity(Orders orders) {
        int totalQuantity = VALUE_ZERO;
        for (Order order : orders.getOrders()) {
            totalQuantity += order.getQuantity();
        }
        return totalQuantity;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public int getBonusDiscount() {
        return bonusDiscount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getTotalQuantity(Orders orders) {
        return calculateTotalQuantity(orders);
    }

    public int getFinalCost() {
        return totalCost + membershipDiscount + bonusDiscount;
    }
}
