package com.onlineshop;


import com.onlineshop.entities.Customer;
import com.onlineshop.entities.Membership;
import com.onlineshop.entities.Order;
import com.onlineshop.entities.Voucher;
import com.onlineshop.items.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CheckoutHandler {

    private LocalDate deliveryWindowStart;
    private LocalDate deliveryWindowEnd;

    public double calculateTotal(Order order) {

        double baseTotal = sumItemPrices(order.getItems());

        baseTotal = applyVoucher(order.getVoucher(), baseTotal);

        baseTotal = addDeliveryFee(order.getCustomer(), baseTotal);

        return baseTotal;
    }

    private static double addDeliveryFee(Customer customer, double baseTotal) {
        // handle delivery fee
        if (isEligibleForFreeDelivery(customer.getMembership())) {
            // do nothing
        } else {
            if (isUsAddress(customer.getAddress())) {
                System.out.println("Adding flat delivery fee of 5 USD");
                baseTotal = baseTotal + 5;
            } else {
                System.out.println("Adding flat global delivery fee of 10 USD");
                baseTotal = baseTotal + 10;
            }
        }
        return baseTotal;
    }

    private static boolean isUsAddress(String address) {
        return address.contains("US");
    }

    private static boolean isEligibleForFreeDelivery(String membership) {
        return membership.equalsIgnoreCase(Membership.GOLD.toString());
    }

    private static double applyVoucher(Voucher voucher, double baseTotal) {
        // check if voucher is valid
        if (isValidVoucher(voucher)) {
            baseTotal = BigDecimal.valueOf(baseTotal * 0.95).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            System.out.println("Voucher invalid");
        }
        return baseTotal;
    }

    private static boolean isValidVoucher(Voucher voucher) {
        return voucher.getCode().equals("GIMME_DISCOUNT") || voucher.getCode().equals("CHEAPER_PLEASE");
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
