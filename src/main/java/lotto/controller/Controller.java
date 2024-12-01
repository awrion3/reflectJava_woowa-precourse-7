package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.model.LottoMachine;
import lotto.util.Convertor;
import lotto.view.Input;
import lotto.view.Output;

public class Controller {
    public void start() {
        LottoMachine lottoMachine = repeatBuyAmount();

        Output.printTicketsAmount(lottoMachine);
        Output.printLottoNumbers(lottoMachine);

        Lotto jackpotNumbers = repeatJackpotNumbers();
        LottoGame lottoGame = repeatJackpotBonus(lottoMachine, jackpotNumbers);

        Output.printLottoPrizeStatus();
        Output.printLottoPrizeRate(lottoMachine, lottoGame);
    }

    private LottoMachine repeatBuyAmount() {
        while (true) {
            try {
                String buyAmount = Input.askBuyAmount();
                return new LottoMachine(buyAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto repeatJackpotNumbers() {
        while (true) {
            try {
                String jackpotNumbers = Input.askJackpotNumbers();
                return new Lotto(Convertor.convertStringToIntList(jackpotNumbers));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LottoGame repeatJackpotBonus(LottoMachine lottoMachine, Lotto jackpotNumbers) {
        while (true) {
            try {
                String jackpotBonus = Input.askJackpotBonus();
                return new LottoGame(lottoMachine, jackpotNumbers, jackpotBonus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
