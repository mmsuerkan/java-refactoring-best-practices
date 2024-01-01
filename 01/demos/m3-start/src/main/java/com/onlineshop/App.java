package com.onlineshop;


import com.onlineshop.entities.Customer;
import com.onlineshop.entities.Membership;
import com.onlineshop.entities.Order;
import com.onlineshop.entities.Voucher;
import com.onlineshop.items.Cheese;
import com.onlineshop.items.Chocolate;
import com.onlineshop.items.Item;
import com.onlineshop.items.Wine;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.onlineshop.DeliveryTimeWindow.deliveryTimeWindow;
import static com.onlineshop.entities.CustomerRepo.getUsCustomer;
import static java.time.LocalDate.now;

public class App {


    public static void main(String[] args){

     /*  // Create customer
        Customer customer1 = new Customer(Membership.GOLD, "MyStreet 123, US");

        // add items to list
        List<Item> shoppingList = Arrays.asList(new Chocolate(), new Chocolate(), new Cheese());

        CheckoutHandler checkout = new CheckoutHandler();
        Voucher voucher = new Voucher("DummyVoucher", now(), now().plusDays(1));

        // calculate total
        Order order = new Order(shoppingList, voucher);
        order.setCustomer(customer1);
        double total1 = checkout.calculateTotal(order);
        System.out.println("Total price for goods: " + total1);


        LocalDate start = now().plusDays(1);
        LocalDate end = now().plusDays(2);

        checkout.setDeliveryTimeWindow(start, end);

        DeliveryTimeWindow window = new DeliveryTimeWindow(now().plusDays(1), now().plusDays(2));
        checkout.setDeliveryTimeWindow(window);

        window = deliveryTimeWindow().startInDays(1).endInDays(2);
        checkout.setDeliveryTimeWindow(window);

      */
        Order order = new Order(getUsCustomer());
        order.add(new Wine());
        order.add(new Cheese());

        System.out.println("Items added for US customer: " + order.getItems());
    }
}
