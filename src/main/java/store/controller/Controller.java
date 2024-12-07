package store.controller;

import store.model.Cashier;
import store.model.Customer;
import store.model.Manager;
import store.model.Orders;
import store.model.Products;
import store.model.Promotions;
import store.view.Input;
import store.view.Output;

public class Controller {
    public void start() {
        Products products = new Products();
        Promotions promotions = new Promotions();
        do {
            Output.printGreetings();
            Output.printProducts(products);
            Orders orders = repeatOrders(products);
            Manager manager = repeatManager(orders, products, promotions);
            Cashier cashier = repeatCashier(orders, manager);
            Output.printReceipt(orders, manager, cashier);
        } while (repeatProcess());
    }

    private Orders repeatOrders(Products products) {
        while (true) {
            try {
                String response = Input.askOrders();
                return new Orders(response, products);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private Manager repeatManager(Orders orders, Products products, Promotions promotions) {
        while (true) {
            try {
                return new Manager(orders, products, promotions);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private Cashier repeatCashier(Orders orders, Manager manager) {
        while (true) {
            try {
                String response = Input.askMembershipDiscount();
                return new Cashier(response, orders, manager);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private boolean repeatProcess() {
        while (true) {
            try {
                String response = Input.askProcessRepeat();
                return Customer.isPositive(response);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }
}
