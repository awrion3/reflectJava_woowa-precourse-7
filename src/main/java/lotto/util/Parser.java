package lotto.util;

import static lotto.common.Exception.NOT_DOUBLE;
import static lotto.common.Exception.NOT_INTEGER;

public class Parser {
    public static int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER);
        }
    }

    public static double parseStringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_DOUBLE);
        }
    }
}
