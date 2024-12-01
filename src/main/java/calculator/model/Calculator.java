package calculator.model;

public class Calculator {
    private int sum;

    public Calculator(Expression expression) {
        evaluateNumbers(expression);
    }

    private void evaluateNumbers(Expression expression) {
        String delimiter = expression.getDelimiter();
        String numbers = expression.getNumbers();

        for (String number : numbers.split(delimiter)) {
            validateNumber(number);
            calculateNumbers(number);
        }
    }

    private void validateNumber(String number) {
        try {
            if (Integer.parseInt(number) < 0) {
                throw new IllegalArgumentException("[ERROR] 숫자값은 음수가 아닌 정수를 입력해야 합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자값은 문자가 아닌 정수를 입력해야 합니다.");
        }
    }

    private void calculateNumbers(String number) {
        sum += Integer.parseInt(number);
    }

    public int getSum() {
        return sum;
    }
}
