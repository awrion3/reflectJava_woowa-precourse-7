package lotto.model;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static lotto.common.Constant.HUNDRED_VALUE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.util.Convertor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoGameTest {
    @DisplayName("예외 테스트: 보너스 번호가 잘못된 경우")
    @ParameterizedTest
    @CsvSource(value = {"1000:1,2,3,4,5,6:", "1000:1,2,3,4,5,6:1번", "1000:1,2,3,4,5,6:50",
            "1000:1,2,3,4,5,6:1"}, delimiter = ':')
    void check_buy_amount_exception(String amount, String numbers, String bonus) {
        LottoMachine lottoMachine = new LottoMachine(amount);
        Lotto jackpotNumbers = new Lotto(Convertor.convertStringToIntList(numbers));
        assertThatThrownBy(() -> new LottoGame(lottoMachine, jackpotNumbers, bonus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기능 테스트: 로또 당첨 수익률 계산 수행 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000:1,2,3,4,5,6:7"}, delimiter = ':')
    void check_total_numbers_bonus_hit_execution(String amount, String numbers, String bonus) {
        final int PRIZE_SECOND_AMOUNT = 30_000_000;

        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    LottoMachine lottoMachine = new LottoMachine(amount);
                    Lotto jackpotNumbers = new Lotto(Convertor.convertStringToIntList(numbers));
                    LottoGame lottoGame = new LottoGame(lottoMachine, jackpotNumbers, bonus);

                    double winningsRate = lottoGame.getLottoWinningsRate(lottoMachine);
                    double expectedRate = ((double) PRIZE_SECOND_AMOUNT / lottoMachine.getBuyAmount()) * HUNDRED_VALUE;

                    assertEquals(winningsRate, expectedRate);
                },
                List.of(1, 2, 3, 4, 5, 7)
        );
    }
}
