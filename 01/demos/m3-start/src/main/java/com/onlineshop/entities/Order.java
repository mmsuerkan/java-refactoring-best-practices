package com.onlineshop.entities;

import com.onlineshop.country.Canada;
import com.onlineshop.country.Country;
import com.onlineshop.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private Customer customer;

    private List<Item> items;
    private Voucher voucher;

    public Order(Customer usCustomer){
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
    public boolean add(Item item){
        if(item.isAgeRestrictedBeverage()){
            if(customer.getAge() < 21) {
                System.out.println("Cannot add age restricted item to order");
                return false;
            }
        }
        return items.add(item);
    }

    public boolean addWithCheck(Item item){
        Objects.requireNonNull(customer);
        Country country = customer.getAddress().getCountry();


        if(item.isAgeRestrictedBeverage()){
            int age = customer.getAge();

            if(age < 21 && country.toString().equals("US")){
                System.out.println("Cannot add age restricted item to order");
                return false;
            }

            if(customer.getAge() < 18 && country.toString().equals("France") ||
                    customer.getAge() < 18 && country.toString().equals("Spain")) {
                // the list goes on and on for all countries with legal age of 18
                System.out.println("Cannot add age restricted item to order");
                return false;
            }

            if(country.toString().equals("Canada")){
                String province = customer.getAddress().getProvince();

                if(age >=18 &&  "Quebec".equalsIgnoreCase(province) || "Alberta".equalsIgnoreCase(province)){
                    return items.add(item);
                } else if(age >= 19) {
                    return items.add(item);
                } else {
                    System.out.println("Sorry, you're under age");
                    return false;
                }
            }
        }
        return items.add(item);
    }

    public boolean addWithCheck2(Item item){
        if(item.isAgeRestrictedBeverage()){
            if(customer.getAge() < getLegalAgeFor(customer)){
                System.out.println("Sorry!");
                return false;
            }
        }
        return items.add(item);
    }

    private int getLegalAgeFor(Customer customer) {
        Country country = customer.getAddress().getCountry();
        if(country instanceof Canada){
            Canada canada = (Canada) country;
            return canada.getLegalDrinkingAge(customer.getAddress().getProvince());
        }
        return country.getMinimumLegalDrinkingAge();
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
