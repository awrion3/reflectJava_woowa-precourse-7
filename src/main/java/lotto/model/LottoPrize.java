package lotto.model;

import static lotto.common.Constant.ONE_VALUE;
import static lotto.common.Constant.ZERO_VALUE;

import java.util.Iterator;
import java.util.List;

public enum LottoPrize {
    MISS(0, false, 0, 0),
    FIFTH(3, false, 5000, 0),
    FOURTH(4, false, 50_000, 0),
    THIRD(5, false, 1_500_000, 0),
    SECOND(5, true, 30_000_000, 0),
    FIRST(6, false, 2_000_000_000, 0);

    private final int numbersHit;
    private final boolean bonusHit;
    private final int prizeAmount;
    private int prizeCount;

    LottoPrize(int numbersHit, boolean bonusHit, int prizeAmount, int prizeCount) {
        this.numbersHit = numbersHit;
        this.bonusHit = bonusHit;
        this.prizeAmount = prizeAmount;
        this.prizeCount = prizeCount;
    }

    private static void clearPrizeCount() {
        for (LottoPrize prize : LottoPrize.values()) {
            prize.prizeCount = ZERO_VALUE;
        }
    }

    static void updatePrizeCount(List<Integer> totalNumbersHit, List<Boolean> totalBonusHit) {
        clearPrizeCount();
        Iterator<Integer> numbersHit = totalNumbersHit.iterator();
        Iterator<Boolean> bonusHit = totalBonusHit.iterator();

        while (numbersHit.hasNext() && bonusHit.hasNext()) {
            LottoPrize prize = findPrizeMatch(numbersHit.next(), bonusHit.next());
            prize.prizeCount += ONE_VALUE;
        }
    }

    private static LottoPrize findPrizeMatch(int numbersHit, boolean bonusHit) {
        for (LottoPrize prize : LottoPrize.values()) {
            if (isNumbersHitMatch(numbersHit, prize) && isBonusHitMatch(bonusHit, prize)) {
                return prize;
            }
        }
        return LottoPrize.MISS;
    }

    private static boolean isNumbersHitMatch(int numbersHit, LottoPrize prize) {
        return numbersHit == prize.numbersHit;
    }

    private static boolean isBonusHitMatch(boolean bonusHit, LottoPrize prize) {
        return bonusHit == prize.bonusHit;
    }

    public static double getTotalPrizeAmount() {
        return calculateTotalPrizeAmount();
    }

    private static double calculateTotalPrizeAmount() {
        double totalPrizeAmount = ZERO_VALUE;

        for (LottoPrize prize : LottoPrize.values()) {
            totalPrizeAmount += prize.prizeAmount * prize.prizeCount;
        }
        return totalPrizeAmount;
    }

    public int getPrizeCount() {
        return prizeCount;
    }
}
