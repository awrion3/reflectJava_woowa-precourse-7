package calculator.controller;

import calculator.model.Calculator;
import calculator.model.Expression;
import calculator.view.Input;
import calculator.view.Output;

public class Controller {
    public void start() {
        String input = Input.requestString();

        Expression expression = new Expression(input);
        Calculator calculator = new Calculator(expression);

        Output.printResult(calculator);
    }
}
