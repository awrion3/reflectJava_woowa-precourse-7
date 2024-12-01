package racingcar.controller;

import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Race;
import racingcar.view.Input;
import racingcar.view.Output;

public class Controller {
    public void start() {
        String carNames = Input.askCarNames();
        Cars cars = new Cars(carNames);

        String raceRound = Input.askRaceRound();
        Race race = new Race(raceRound);

        Output.printMessage();
        repeatRace(race, cars);

        race.evaluateWinners(cars);
        Output.printWinners(race);
    }

    private void repeatRace(Race race, Cars cars) {
        for (int i = 0; i < race.getRound(); i++) {
            for (Car car : cars.getCars()) {
                car.updatePosition();
            }
            Output.printStatus(cars);
        }
    }
}
