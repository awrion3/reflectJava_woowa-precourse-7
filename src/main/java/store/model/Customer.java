package store.model;

import static store.common.Exception.INVALID_INPUT;

public enum Customer {
    YES("Y", true),
    NO("N", false);

    private final String response;
    private final boolean isPositive;

    Customer(String response, boolean isPositive) {
        this.response = response;
        this.isPositive = isPositive;
    }

    public static boolean isPositive(String response) {
        Customer customerResponse = validateResponse(response);
        if (customerResponse == null) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        return customerResponse.isPositive;
    }

    private static Customer validateResponse(String response) {
        if (response.equals(Customer.YES.response)) {
            return Customer.YES;
        }
        if (response.equals(Customer.NO.response)) {
            return Customer.NO;
        }
        return null;
    }
}
