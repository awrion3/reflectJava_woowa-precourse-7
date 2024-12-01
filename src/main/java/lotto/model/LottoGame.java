package lotto.model;

import static lotto.common.Constant.HUNDRED_VALUE;
import static lotto.common.Constant.MAX_RANGE;
import static lotto.common.Constant.MIN_RANGE;
import static lotto.common.Exception.INVALID_RANGE;
import static lotto.common.Exception.NOT_UNIQUE;

import java.util.ArrayList;
import java.util.List;
import lotto.util.Parser;

public class LottoGame {
    public LottoGame(LottoMachine lottoMachine, Lotto jackpotNumbers, String jackpotBonus) {
        validateBonusRange(jackpotBonus);
        validateBonusUnique(jackpotBonus, jackpotNumbers);
        checkLottoPrize(lottoMachine, jackpotNumbers, jackpotBonus);
    }

    private void validateBonusRange(String jackpotBonus) {
        int bonusNumber = Parser.parseStringToInt(jackpotBonus);

        if (!(bonusNumber >= MIN_RANGE && bonusNumber <= MAX_RANGE)) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
    }

    private void validateBonusUnique(String jackpotBonus, Lotto jackpotNumbers) {
        int bonusNumber = Parser.parseStringToInt(jackpotBonus);

        boolean isDuplicate = jackpotNumbers.getNumbers()
                .stream()
                .anyMatch(value -> value == bonusNumber);

        if (isDuplicate) {
            throw new IllegalArgumentException(NOT_UNIQUE);
        }
    }

    private void checkLottoPrize(LottoMachine lottoMachine, Lotto jackpotNumbers, String jackpotBonus) {
        int bonusNumber = Parser.parseStringToInt(jackpotBonus);

        List<Integer> totalNumbersHit = checkTotalNumbersHit(lottoMachine, jackpotNumbers);
        List<Boolean> totalBonusHit = checkTotalBonusHit(lottoMachine, bonusNumber);

        LottoPrize.updatePrizeCount(totalNumbersHit, totalBonusHit);
    }

    private static List<Integer> checkTotalNumbersHit(LottoMachine lottoMachine, Lotto jackpotNumber) {
        List<Integer> totalNumbersHit = new ArrayList<>();

        for (Lotto lottoTicket : lottoMachine.getLottoTickets()) {
            int numbersHit = (int) lottoTicket.getNumbers()
                    .stream()
                    .filter(jackpotNumber.getNumbers()::contains)
                    .count();
            totalNumbersHit.add(numbersHit);
        }
        return totalNumbersHit;
    }

    private static List<Boolean> checkTotalBonusHit(LottoMachine lottoMachine, int bonusNumber) {
        List<Boolean> totalBonusHit = new ArrayList<>();

        for (Lotto lottoTicket : lottoMachine.getLottoTickets()) {
            boolean bonusHit = lottoTicket.getNumbers()
                    .stream()
                    .anyMatch(value -> value == bonusNumber);
            totalBonusHit.add(bonusHit);
        }
        return totalBonusHit;
    }

    public double getLottoWinningsRate(LottoMachine lottoMachine) {
        double winningsRate = (double) LottoPrize.getTotalPrizeAmount() / lottoMachine.getBuyAmount();
        return winningsRate * HUNDRED_VALUE;
    }
}
