package com.onlineshop.entities;

public class Customer {

    private Membership membership;
    private Address address;
    private int age;

    public Customer(){}



    public Customer(Address address) {
        this.address = address;
    }

    public Customer(Membership membership, Address address, int age){
        this.membership = membership;
        this.address = address;
        this.age = age;
    }

    public Customer(Address address, int age) {
        this.address = address;
        this.age = age;
    }

    public Customer(Membership membership, Address address){
        this.membership = membership;
        this.address = address;
    }

    public Membership getMembership() {
        return membership;
    }

    public Address getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
