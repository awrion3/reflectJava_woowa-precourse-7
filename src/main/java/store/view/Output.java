package store.view;

import static store.common.Constant.VALUE_ZERO;

import store.model.Cashier;
import store.model.Manager;
import store.model.Order;
import store.model.Orders;
import store.model.Product;
import store.model.Products;

public class Output {
    public static void printGreetings() {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();
    }

    public static void printProducts(Products products) {
        for (Product product : products.getProducts()) {
            String item = formatInfo(product)
                    + formatQuantity(product)
                    + formatPromotion(product);
            System.out.println(item);
        }
        System.out.println();
    }

    private static String formatInfo(Product product) {
        return String.format("- %s %,d원 ", product.getName(), product.getPrice());
    }

    private static String formatQuantity(Product product) {
        int quantity = product.getQuantity();
        if (quantity == VALUE_ZERO) {
            return "재고 없음";
        }
        return String.format("%d개", quantity);
    }

    private static String formatPromotion(Product product) {
        String promotion = product.getPromotion();
        if (promotion == null) {
            return "";
        }
        return String.format(" %s", promotion);
    }

    public static void printReceipt(Orders orders, Manager manager, Cashier cashier) {
        System.out.println();
        formatOrder(orders, cashier);
        formatBonus(manager);

        formatTotal(orders, cashier);
        formatBonusDiscount(cashier.getBonusDiscount());
        formatMembershipDiscount(cashier.getMembershipDiscount());
        formatFinalCost(cashier);
    }

    private static void formatOrder(Orders orders, Cashier cashier) {
        System.out.println("===========W 편의점=============");
        System.out.printf("%-6s\t\t%-2s\t%5s\n", "상품명", "수량", "금액");

        for (Order order : orders.getOrders()) {
            System.out.printf("%-7s\t\t%d \t%,10d\n",
                    order.getName(),
                    order.getQuantity(),
                    cashier.calculateOrderCost(order));
        }
    }

    private static void formatBonus(Manager manager) {
        System.out.println("===========증\t정=============");
        for (Order order : manager.getBonusPromotionOrders()) {
            if (order.getQuantity() == VALUE_ZERO) {
                continue;
            }
            System.out.printf("%-7s\t\t%d\n", order.getName(), order.getQuantity());
        }
    }

    private static void formatTotal(Orders orders, Cashier cashier) {
        System.out.println("==============================");
        System.out.printf("%-5s\t\t%d\t%,10d\n", "총구매액",
                cashier.getTotalQuantity(orders),
                cashier.getTotalCost());
    }

    private static void formatBonusDiscount(int discount) {
        if (discount == VALUE_ZERO) {
            System.out.printf("%-10s\t\t\t-%d\n", "행사할인", discount);
            return;
        }
        System.out.printf("%-5s\t\t\t%+,10d\n", "행사할인", discount);
    }

    private static void formatMembershipDiscount(int discount) {
        if (discount == VALUE_ZERO) {
            System.out.printf("%-10s\t\t\t-%d\n", "멤버십할인", discount);
            return;
        }
        System.out.printf("%-5s\t\t\t%+,10d\n", "멤버십할인", discount);
    }

    private static void formatFinalCost(Cashier cashier) {
        System.out.printf("%-7s\t\t\t%,10d\n", "내실돈", cashier.getFinalCost());
    }
}
