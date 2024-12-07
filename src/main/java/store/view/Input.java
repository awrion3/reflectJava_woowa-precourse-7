package store.view;

import camp.nextstep.edu.missionutils.Console;

public class Input {
    public static String askOrders() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return Console.readLine();
    }

    public static String askBonusPromotionOrder(String name) {
        System.out.println();
        System.out.printf("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. "
                + "추가하시겠습니까? (Y/N)\n", name);
        return Console.readLine();
    }

    public static String askNoPromotionOrder(String name, int quantity) {
        System.out.println();
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. "
                + "그래도 구매하시겠습니까? (Y/N)\n", name, quantity);
        return Console.readLine();
    }

    public static String askMembershipDiscount() {
        System.out.println();
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        return Console.readLine();
    }

    public static String askProcessRepeat() {
        System.out.println();
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        return Console.readLine();
    }
}