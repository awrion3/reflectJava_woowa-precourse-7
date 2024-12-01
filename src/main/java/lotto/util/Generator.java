package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Generator {
    public static List<Integer> generateRandomIntList(int start, int end, int count) {
        return Randoms.pickUniqueNumbersInRange(start, end, count);
    }

    public static int generateRandomInt(int start, int end) {
        return Randoms.pickNumberInRange(start, end);
    }
}
