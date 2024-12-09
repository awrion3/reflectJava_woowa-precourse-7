package store.model;

import static store.common.Constant.VALUE_ZERO;

import java.util.ArrayList;
import java.util.List;
import store.view.Input;

public class Manager {
    private List<Order> bonusPromotionOrders = new ArrayList<>();
    private List<Order> noPromotionOrders = new ArrayList<>();

    public Manager(Orders orders, Products products, Promotions promotions) {
        evaluateBonusPromotion(orders, promotions);
        evaluateNoPromotion(orders, promotions);
        updateBonusPromotionOrders(orders, promotions);
        updateNoPromotionOrders(orders, promotions);
        updateProducts(orders, products);
    }

    private void evaluateBonusPromotion(Orders orders, Promotions promotions) {
        for (Order order : orders.getOrders()) {
            Product product = order.getId();
            if (promotions.isPromotionPeriod(product)) {
                Promotion promotion = promotions.findPromotionId(product);
                confirmBonusPromotion(order, product, promotion);
            }
        }
    }

    private void confirmBonusPromotion(Order order, Product product, Promotion promotion) {
        if (hasBonusPromotion(order, product, promotion)) {
            String response = Input.askBonusPromotionOrder(order.getName());
            if (Customer.isPositive(response)) {
                order.addQuantity(promotion.get());
            }
        }
    }

    private boolean hasBonusPromotion(Order order, Product product, Promotion promotion) {
        if (product.getQuantity() < (order.getQuantity() + promotion.get())) {
            return false;
        }
        return (promotion.get() == (order.getQuantity() % calculatePromotionUnit(promotion)));
    }

    private int calculatePromotionUnit(Promotion promotion) {
        return promotion.buy() + promotion.get();
    }

    private void evaluateNoPromotion(Orders orders, Promotions promotions) {
        for (Order order : orders.getOrders()) {
            Product product = order.getId();
            if (promotions.isPromotionPeriod(product)) {
                Promotion promotion = promotions.findPromotionId(product);
                confirmNoPromotion(order, product, promotion);
            }
        }
    }

    private void confirmNoPromotion(Order order, Product product, Promotion promotion) {
        int noPromotionQuantity = calculateNoPromotion(order, product, promotion);
        if (noPromotionQuantity == VALUE_ZERO) {
            return;
        }
        String response = Input.askNoPromotionOrder(order.getName(), noPromotionQuantity);
        if (!Customer.isPositive(response)) {
            order.removeQuantity(noPromotionQuantity);
        }
    }

    private int calculateNoPromotion(Order order, Product product, Promotion promotion) {
        if (order.getQuantity() > product.getQuantity()) {
            int remainder = order.getQuantity() - product.getQuantity();
            remainder += product.getQuantity() % calculatePromotionUnit(promotion);
            return remainder;
        }
        return (order.getQuantity() % calculatePromotionUnit(promotion));
    }

    private void updateBonusPromotionOrders(Orders orders, Promotions promotions) {
        for (Order order : orders.getOrders()) {
            Product product = order.getId();
            if (promotions.isPromotionPeriod(product)) {
                Promotion promotion = promotions.findPromotionId(product);
                int quantity = calculateBonusPromotionQuantity(order, product, promotion);
                bonusPromotionOrders.add(new Order(order.getName(), quantity, product));
            }
        }
    }

    private int calculateBonusPromotionQuantity(Order order, Product product, Promotion promotion) {
        int quantity = Math.min(order.getQuantity(), product.getQuantity());
        quantity /= calculatePromotionUnit(promotion);
        return quantity;
    }

    private void updateNoPromotionOrders(Orders orders, Promotions promotions) {
        for (Order order : orders.getOrders()) {
            Product product = order.getId();
            int quantity = order.getQuantity();
            Promotion promotion = promotions.findPromotionId(product);
            if (promotion != null) {
                quantity -= (findBonusPromotionQuantity(order.getName()) * calculatePromotionUnit(promotion));
            }
            noPromotionOrders.add(new Order(order.getName(), quantity, product));
        }
    }

    private int findBonusPromotionQuantity(String name) {
        for (Order order : bonusPromotionOrders) {
            if (order.getName().equals(name)) {
                return order.getQuantity();
            }
        }
        return VALUE_ZERO;
    }

    private void updateProducts(Orders orders, Products products) {
        for (Order order : orders.getOrders()) {
            List<Product> productPair = products.findProductPair(order.getName());
            if (hasPromotionShortage(order, productPair)) {
                handlePromotionShortage(order, productPair);
                continue;
            }
            productPair.getFirst().removeQuantity(order.getQuantity());
        }
    }

    private boolean hasPromotionShortage(Order order, List<Product> productPair) {
        return (order.getQuantity() > productPair.getFirst().getQuantity());
    }

    private void handlePromotionShortage(Order order, List<Product> productPair) {
        Product promotionProduct = productPair.getFirst();
        Product regularProduct = productPair.getLast();

        int shortage = order.getQuantity() - promotionProduct.getQuantity();
        promotionProduct.removeQuantity(promotionProduct.getQuantity());
        regularProduct.removeQuantity(shortage);
    }

    public List<Order> getBonusPromotionOrders() {
        return bonusPromotionOrders;
    }

    public List<Order> getNoPromotionOrders() {
        return noPromotionOrders;
    }
}
