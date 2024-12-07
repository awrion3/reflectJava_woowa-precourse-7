package store.model;

import static store.common.Constant.INDEX_ONE;
import static store.common.Constant.VALUE_ZERO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import store.util.Reader;

public class Products {
    private static final String FILE_NAME = "products.md";

    List<Product> products = new ArrayList<>();

    public Products() {
        initProducts();
    }

    private void initProducts() {
        Reader reader = new Reader();
        List<List<String>> resource = reader.readResource(FILE_NAME);
        for (List<String> row : resource) {
            products.add(new Product(row));
            handleRegularPair(row, resource);
        }
    }

    private void handleRegularPair(List<String> row, List<List<String>> resource) {
        String name = row.getFirst();
        List<String> regularPair = resource.stream()
                .filter(line -> line.getFirst().equals(name))
                .reduce((first, second) -> second)
                .orElse(null);
        if (!regularPair.getLast().equals("null")) {
            createRegularPair(row);
        }
    }

    private void createRegularPair(List<String> row) {
        String name = row.getFirst();
        int price = Integer.parseInt(row.get(INDEX_ONE));
        int quantity = VALUE_ZERO;
        String promotion = null;
        products.add(new Product(name, price, quantity, promotion));
    }

    Product findProductFirstId(String name) {
        Product id = products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
        return id;
    }

    List<Product> findProductPair(String name) {
        List<Product> productPair = products.stream()
                .filter(product -> product.getName().equals(name))
                .collect(Collectors.toList());
        return productPair;
    }

    int calculateProductPairTotalQuantity(String name) {
        List<Product> productPair = findProductPair(name);
        int totalQuantity = productPair.stream()
                .mapToInt(Product::getQuantity)
                .sum();
        return totalQuantity;
    }

    public List<Product> getProducts() {
        return products;
    }
}
