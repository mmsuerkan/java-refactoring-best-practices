package com.onlineshop.handlers;

import com.onlineshop.entities.Voucher;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountManager {
    public double applyVoucher(Voucher voucher, double baseTotal) {
        // check if voucher is valid
        if (isValidVoucher(voucher)) {
            baseTotal = BigDecimal.valueOf(baseTotal * 0.95).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            System.out.println("Voucher invalid");
        }
        return baseTotal;
    }

    private boolean isValidVoucher(Voucher voucher) {
        return voucher.getCode().equals("GIMME_DISCOUNT") || voucher.getCode().equals("CHEAPER_PLEASE");
    }
}
