package lotto.view;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.model.LottoMachine;
import lotto.model.LottoPrize;

public class Output {
    public static void printTicketsAmount(LottoMachine lottoMachine) {
        System.out.printf("\n%s개를 구매했습니다.\n", lottoMachine.getTicketsAmount());
    }

    public static void printLottoNumbers(LottoMachine lottoMachine) {
        List<Lotto> lottoTickets = lottoMachine.getLottoTickets();
        for (Lotto lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getNumbers());
        }
        System.out.println();
    }

    public static void printLottoPrizeStatus() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        System.out.printf("3개 일치 (5,000원) - %d개\n", LottoPrize.FIFTH.getPrizeCount());
        System.out.printf("4개 일치 (50,000원) - %d개\n", LottoPrize.FOURTH.getPrizeCount());
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", LottoPrize.THIRD.getPrizeCount());
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", LottoPrize.SECOND.getPrizeCount());
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", LottoPrize.FIRST.getPrizeCount());
    }

    public static void printLottoPrizeRate(LottoMachine lottoMachine, LottoGame lottoGame) {
        double winningsRate = lottoGame.getLottoWinningsRate(lottoMachine);
        System.out.println();
        System.out.printf("총 수익률은 %,.1f%%입니다.", winningsRate);
    }
}
