package com.onlineshop.handlers;

import com.onlineshop.entities.Customer;
import com.onlineshop.entities.Membership;

public class DeliveryManager {
    public double addDeliveryFee(Customer customer, double baseTotal) {
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
}
