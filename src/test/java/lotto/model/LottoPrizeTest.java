package lotto.model;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.util.Convertor;
import lotto.util.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoPrizeTest {
    @DisplayName("기능 테스트: 로또 상품 카운트 갱신 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000:1,2,9,10,11,12:13:1:MISS", "1000:1,2,3,9,10,11:12:1:FIFTH",
            "1000:1,2,3,4,9,10:11:1:FOURTH", "1000:1,2,3,4,5,9:10:1:THIRD",
            "1000:1,2,3,4,5,9:6:1:SECOND", "1000:1,2,3,4,5,6:9:1:FIRST"}, delimiter = ':')
    void check_prize_count_execution(String amount, String numbers, String bonus, String count, String name) {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    LottoMachine lottoMachine = new LottoMachine(amount);
                    Lotto jackpotNumbers = new Lotto(Convertor.convertStringToIntList(numbers));
                    LottoGame lottoGame = new LottoGame(lottoMachine, jackpotNumbers, bonus);

                    int prizeCount = Parser.parseStringToInt(count);
                    assertEquals(LottoPrize.valueOf(name).getPrizeCount(), prizeCount);
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @DisplayName("기능 테스트: 로또 당첨 총액 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000:1,2,9,10,11,12:13:0", "1000:1,2,3,9,10,11:12:5000",
            "1000:1,2,3,4,9,10:11:50000", "1000:1,2,3,4,5,9:10:1500000",
            "1000:1,2,3,4,5,9:6:30000000", "1000:1,2,3,4,5,6:9:2000000000"}, delimiter = ':')
    void check_prize_amount_execution(String amount, String numbers, String bonus, String prizeAmount) {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    LottoMachine lottoMachine = new LottoMachine(amount);
                    Lotto jackpotNumbers = new Lotto(Convertor.convertStringToIntList(numbers));
                    LottoGame lottoGame = new LottoGame(lottoMachine, jackpotNumbers, bonus);

                    double totalAmount = LottoPrize.getTotalPrizeAmount();
                    double expectedAmount = Parser.parseStringToDouble(prizeAmount);

                    assertEquals(expectedAmount, totalAmount);
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }
}
