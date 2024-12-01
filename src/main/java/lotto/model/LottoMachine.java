package lotto.model;

import static lotto.common.Constant.AMOUNT_UNIT;
import static lotto.common.Constant.MAX_RANGE;
import static lotto.common.Constant.MIN_RANGE;
import static lotto.common.Constant.SIZE_LIMIT;
import static lotto.common.Constant.ZERO_VALUE;
import static lotto.common.Exception.EMPTY_STRING;
import static lotto.common.Exception.INVALID_AMOUNT;
import static lotto.common.Exception.INVALID_UNIT;

import java.util.ArrayList;
import java.util.List;
import lotto.util.Checker;
import lotto.util.Generator;
import lotto.util.Parser;

public class LottoMachine {
    List<Lotto> lottoTickets = new ArrayList<>();

    public LottoMachine(String buyAmount) {
        validateString(buyAmount);
        validateAmount(buyAmount);
        validateUnit(buyAmount);
        generateLottoTickets(buyAmount);
    }

    private void validateString(String buyAmount) {
        if (Checker.isNullEmptyString(buyAmount)) {
            throw new IllegalArgumentException(EMPTY_STRING);
        }
    }

    private void validateAmount(String buyAmount) {
        if (Parser.parseStringToInt(buyAmount) < AMOUNT_UNIT) {
            throw new IllegalArgumentException(INVALID_AMOUNT);
        }
    }

    private void validateUnit(String buyAmount) {
        if (Parser.parseStringToInt(buyAmount) % AMOUNT_UNIT != ZERO_VALUE) {
            throw new IllegalArgumentException(INVALID_UNIT);
        }
    }

    private int calculateTicketAmount(String buyAmount) {
        return (Parser.parseStringToInt(buyAmount) / AMOUNT_UNIT);
    }

    private void generateLottoTickets(String buyAmount) {
        int ticketAmount = calculateTicketAmount(buyAmount);

        for (int i = 0; i < ticketAmount; i++) {
            List<Integer> lottoNumbers = Generator.generateRandomIntList(MIN_RANGE, MAX_RANGE, SIZE_LIMIT);
            Lotto lotto = new Lotto(lottoNumbers);
            lottoTickets.add(lotto);
        }
    }

    public int getTicketsAmount() {
        return lottoTickets.size();
    }

    public int getBuyAmount() {
        return lottoTickets.size() * AMOUNT_UNIT;
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }
}
