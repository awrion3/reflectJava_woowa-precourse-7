package calculator.view;

import calculator.model.Calculator;

public class Output {
    public static void printResult(Calculator calculator) {
        String result = String.format("결과 : %d", calculator.getSum());
        System.out.println(result);
    }
}
