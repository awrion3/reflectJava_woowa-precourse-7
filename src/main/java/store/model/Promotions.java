package store.model;

import static store.common.Constant.INDEX_ONE;
import static store.common.Constant.INDEX_THREE;
import static store.common.Constant.INDEX_TWO;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import store.util.Reader;

public class Promotions {
    private static final String FILE_NAME = "promotions.md";

    private List<Promotion> promotions = new ArrayList<>();

    public Promotions() {
        initPromotions();
    }

    private void initPromotions() {
        Reader reader = new Reader();
        List<List<String>> resource = reader.readResource(FILE_NAME);
        for (List<String> row : resource) {
            String name = row.getFirst();
            int buy = Integer.parseInt(row.get(INDEX_ONE));
            int get = Integer.parseInt(row.get(INDEX_TWO));
            LocalDate startDate = LocalDate.parse(row.get(INDEX_THREE));
            LocalDate endDate = LocalDate.parse(row.getLast());
            promotions.add(new Promotion(name, buy, get, startDate, endDate));
        }
    }

    boolean isPromotionPeriod(Product product) {
        if (product.getPromotion() == null) {
            return false;
        }
        Promotion promotion = findPromotionId(product);
        LocalDateTime start = LocalDateTime.of(promotion.startDate(), LocalTime.parse("00:00"));
        LocalDateTime end = LocalDateTime.of(promotion.endDate(), LocalTime.parse("23:59"));
        LocalDateTime today = DateTimes.now();
        return (!today.isBefore(start) && !today.isAfter(end));
    }

    Promotion findPromotionId(Product product) {
        if (product.getPromotion() == null) {
            return null;
        }
        Promotion id = promotions.stream()
                .filter(promotion -> promotion.name().equals(product.getPromotion()))
                .findAny()
                .orElse(null);
        return id;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }
}
