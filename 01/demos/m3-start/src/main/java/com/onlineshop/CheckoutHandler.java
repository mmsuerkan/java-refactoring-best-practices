package com.onlineshop;


import com.onlineshop.entities.Order;
import com.onlineshop.handlers.DeliveryManager;
import com.onlineshop.handlers.DiscountManager;
import com.onlineshop.items.Item;

import java.time.LocalDate;
import java.util.List;

public class CheckoutHandler {

    private LocalDate deliveryWindowStart;
    private LocalDate deliveryWindowEnd;

    private DiscountManager discountManager = new DiscountManager();
    private DeliveryManager deliveryManager = new DeliveryManager();

    public double calculateTotal(Order order) {

        double baseTotal = sumItemPrices(order.getItems());

        baseTotal = discountManager.applyVoucher(order.getVoucher(), baseTotal);

        baseTotal = deliveryManager.addDeliveryFee(order.getCustomer(), baseTotal);

        return baseTotal;
    }

    private static double sumItemPrices(List<Item> items) {
        double baseTotal = 0;

        for (Item item : items) {
            baseTotal += item.price();
        }
        return baseTotal;
    }


    public void setDeliveryTimeWindow(LocalDate deliveryStart, LocalDate deliveryEnd) {
        this.deliveryWindowStart = deliveryStart;
        this.deliveryWindowEnd = deliveryEnd;

        System.out.println(String.format("Your Order will delivered some time between %s and %s", deliveryWindowStart, deliveryWindowEnd));
    }

    public void setDeliveryTimeWindow(DeliveryTimeWindow window) {

        System.out.println(String.format("Your Order will delivered some time between %s and %s", window.getStart(), window.getEnd()));
    }

}
