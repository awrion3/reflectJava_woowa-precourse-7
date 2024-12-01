package calculator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    private static final String COMMA = ",";
    private static final String COLON = ":";
    private static final String META_CHARACTER = "`.^$*+?()[]{}|\\";

    private static final int CUSTOM_DELIMITER_INDEX = 2;
    private static final int CUSTOM_DELIMITER_FINISH_INDEX = 5;

    private String delimiter;
    private String numbers;

    public Expression(String expression) {
        validateString(expression);
        extractDelimiter(expression);
    }

    private void validateString(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 문자열은 입력할 수 없습니다.");
        }
    }

    private void extractDelimiter(String expression) {
        if (hasDefaultDelimiter(expression)) {
            validateDefaultDelimiter(expression);
            extractDefaultDelimiter(expression);
        }
        if (!hasDefaultDelimiter(expression)) {
            validateCustomDelimiter(expression);
            extractCustomDelimiter(expression);
        }
    }

    private boolean hasDefaultDelimiter(String expression) {
        return expression.matches(".*[,:].*");
    }

    private void validateDefaultDelimiter(String expression) {
        if (expression.matches(".*[^0-9,:].*")) {
            throw new IllegalArgumentException("[ERROR] 디폴트 구분자와 정수로만 이루어진 문자열을 입력해야 합니다.");
        }
        if (expression.contains(COMMA) && expression.contains(COLON)) {
            throw new IllegalArgumentException("[ERROR] 디폴트 구분자는 쉼표 혹은 콜론 하나로만 구성되어야 합니다.");
        }
    }

    private void validateCustomDelimiter(String expression) {
        Pattern pattern = Pattern.compile("//[^0-9,:]?+\\\\n");
        Matcher matcher = pattern.matcher(expression);

        if (!matcher.find()) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자는 '//'와 '\\n' 사이에 위치한 하나의 문자로만 구성되어야 합니다.");
        }
    }

    private void extractDefaultDelimiter(String expression) {
        if (expression.contains(COLON)) {
            delimiter = COLON;
        }
        if (expression.contains(COMMA)) {
            delimiter = COMMA;
        }
        numbers = expression;
    }

    private void extractCustomDelimiter(String expression) {
        delimiter = String.valueOf(expression.charAt(CUSTOM_DELIMITER_INDEX));

        if (META_CHARACTER.contains(delimiter)) {
            delimiter = "\\" + delimiter;
        }
        numbers = expression.substring(CUSTOM_DELIMITER_FINISH_INDEX);
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getNumbers() {
        return numbers;
    }
}
