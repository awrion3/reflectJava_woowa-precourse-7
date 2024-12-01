package lotto.util;

import java.util.List;
import java.util.stream.Collectors;

public class Sorter {
    public static List<Integer> sortIntList(List<Integer> items) {
        items = items.stream()
                .sorted()
                .collect(Collectors.toList());
        return items;
    }
}
