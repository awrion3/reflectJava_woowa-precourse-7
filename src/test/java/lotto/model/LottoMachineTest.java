package lotto.model;

import static lotto.common.Constant.AMOUNT_UNIT;
import static lotto.common.Constant.ONE_VALUE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.util.Convertor;
import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMachineTest {
    @DisplayName("예외 테스트: 구입 금액이 잘못된 경우")
    @ParameterizedTest
    @ValueSource(strings = {"\n", " ", "천원", "1200", "1000.1"})
    void check_buy_amount_exception(String input) {
        assertThatThrownBy(() -> new LottoMachine(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기능 테스트: 티켓 수량 계산 및 생성 크기 확인")
    @Test
    void check_ticket_amount_execution() {
        int randomValue = Generator.generateRandomInt(AMOUNT_UNIT, Integer.MAX_VALUE - ONE_VALUE);
        int randomUnit = Convertor.convertIntToUnitAmount(randomValue, AMOUNT_UNIT);
        String randomAmount = String.valueOf(randomUnit * AMOUNT_UNIT);

        LottoMachine lottoMachine = new LottoMachine(randomAmount);

        assertEquals(randomUnit, lottoMachine.getTicketsAmount());
    }
}
