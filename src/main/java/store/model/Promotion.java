package store.model;

import java.time.LocalDate;
import java.util.Objects;

public record Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
    public Promotion {
        Objects.requireNonNull(name);
    }
}
