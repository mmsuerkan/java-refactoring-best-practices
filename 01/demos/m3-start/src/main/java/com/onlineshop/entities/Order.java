package com.onlineshop.entities;

import com.onlineshop.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> items;
    private String voucher;

    public Order(){
        items = new ArrayList<>();
    }

    public Order(List<Item> items, String voucher){
        this.items = items;
        this.voucher = voucher;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getVoucher() {
        return voucher;
    }
}
