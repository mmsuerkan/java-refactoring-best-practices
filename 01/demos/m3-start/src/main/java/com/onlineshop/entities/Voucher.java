package com.onlineshop.entities;

import java.time.LocalDate;

public class Voucher {

    String code;
    LocalDate startDate;
    LocalDate expiryDate;

    public Voucher(String code, LocalDate startDate, LocalDate expiryDate) {
        this.code = code;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }

    public String getCode() {
        return code;
    }
}
