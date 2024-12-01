package racingcar.view;

import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Race;

public class Output {
    public static void printMessage() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public static void printStatus(Cars cars) {
        for (Car car : cars.getCars()) {
            String status = formatStatus(car);
            System.out.println(status);
        }
        System.out.println();
    }

    private static String formatStatus(Car car) {
        String status = String.format("%s : ", car.getName());
        if (car.getPosition() == 0) {
            return status;
        }
        return status + "-".repeat(car.getPosition());
    }

    public static void printWinners(Race race) {
        System.out.print("최종 우승자 : ");
        System.out.println(String.join(", ", race.getWinners()));
    }
}
