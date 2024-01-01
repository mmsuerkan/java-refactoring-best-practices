package com.onlineshop.entities;

import com.onlineshop.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;

    private List<Item> items;
    private Voucher voucher;

    public Order(){
        items = new ArrayList<>();
    }

    public Order(List<Item> items, Voucher voucher){
        this.items = items;
        this.voucher = voucher;
    }

    public List<Item> getItems() {
        return items;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
