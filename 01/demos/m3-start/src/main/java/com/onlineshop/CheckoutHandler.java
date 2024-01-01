package com.onlineshop;


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

    public double calculateTotal(List<Item> items, String voucher, String membership, String address){

        double baseTotal = sumItemPrices(items);

        baseTotal = applyVoucher(voucher, baseTotal);

        baseTotal = addDeliveryFee(membership, address, baseTotal);

        return baseTotal;
    }

    private static double addDeliveryFee(String membership, String address, double baseTotal) {
        // handle delivery fee
        if(isEligibleForFreeDelivery(membership)){
            // do nothing
        } else {
            if(isUsAddress(address)){
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
        return Pattern.matches(".*US.*", address);
    }

    private static boolean isEligibleForFreeDelivery(String membership) {
        return membership.equalsIgnoreCase("GOLD");
    }

    private static double applyVoucher(String voucher, double baseTotal) {
        // check if voucher is valid
        if(isValidVoucher(voucher)){
             baseTotal = BigDecimal.valueOf(baseTotal * 0.95).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            System.out.println("Voucher invalid");
        }
        return baseTotal;
    }

    private static boolean isValidVoucher(String voucher) {
        return voucher.equals("GIMME_DISCOUNT") || voucher.equals("CHEAPER_PLEASE");
    }

    private static double sumItemPrices(List<Item> items) {
        double baseTotal = 0;

        // sum up the prices
        List<Double> prices = new ArrayList<>();
        for(Item item : items){
            prices.add(item.price());
        }

        for(double price : prices){
            baseTotal = baseTotal + price;
        }
        return baseTotal;
    }


    public void setDeliveryTimeWindow(LocalDate deliveryStart, LocalDate deliveryEnd){
        this.deliveryWindowStart = deliveryStart;
        this.deliveryWindowEnd = deliveryEnd;

        System.out.println(String.format("Your Order will delivered some time between %s and %s", deliveryWindowStart, deliveryWindowEnd));
    }

}