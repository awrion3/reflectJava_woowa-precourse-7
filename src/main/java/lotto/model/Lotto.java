package lotto.model;

import static lotto.common.Constant.MAX_RANGE;
import static lotto.common.Constant.MIN_RANGE;
import static lotto.common.Constant.SIZE_LIMIT;
import static lotto.common.Exception.INVALID_RANGE;
import static lotto.common.Exception.INVALID_SIZE;
import static lotto.common.Exception.NOT_UNIQUE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.Sorter;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateUnique(numbers);
        this.numbers = Sorter.sortIntList(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != SIZE_LIMIT) {
            throw new IllegalArgumentException(INVALID_SIZE);
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (!(number >= MIN_RANGE && number <= MAX_RANGE)) {
                throw new IllegalArgumentException(INVALID_RANGE);
            }
        }
    }

    private void validateUnique(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(NOT_UNIQUE);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
