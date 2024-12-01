package lotto.util;

import static lotto.common.Constant.COMMA;

import java.util.ArrayList;
import java.util.List;

public class Convertor {
    public static List<Integer> convertStringToIntList(String input) {
        List<Integer> items = new ArrayList<>();

        for (String item : input.split(COMMA)) {
            int parsedItem = Parser.parseStringToInt(item);
            items.add(parsedItem);
        }
        return items;
    }

    public static int convertIntToUnitAmount(int input, int unit) {
        return input / unit;
    }
}
