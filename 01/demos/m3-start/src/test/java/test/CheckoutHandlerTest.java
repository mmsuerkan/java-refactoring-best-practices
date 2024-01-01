package test;


import com.onlineshop.CheckoutHandler;
import com.onlineshop.entities.Customer;
import com.onlineshop.entities.Membership;
import com.onlineshop.entities.Order;
import com.onlineshop.entities.Voucher;
import com.onlineshop.items.Cheese;
import com.onlineshop.items.Chocolate;
import com.onlineshop.items.Item;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class CheckoutHandlerTest {

    static List<Item> shoppingList = Arrays.asList(new Chocolate(), new Chocolate(), new Cheese());
    static CheckoutHandler checkout = new CheckoutHandler();



    @Test(description = "All is good, free delivery with gold membership")
    public void calculateTotalValidVoucherGoldMembership(){
        Customer customer = new Customer(Membership.GOLD.toString(), "MyStreet 123, US");
        Voucher voucher = new Voucher("GIMME_DISCOUNT", null, null);
        Order order = new Order(shoppingList, voucher);
        order.setCustomer(customer);
        double total = checkout.calculateTotal(order);
        Assert.assertEquals(total, 4.27);
    }

    @Test(description = "invalid voucher")
    public void calculateTotalInValidVoucherGoldMembership(){
        Customer customer = new Customer(Membership.GOLD.toString(), "MyStreet 123, US");
        Voucher voucher = new Voucher("DummyVoucher", null, null);
        Order order = new Order(shoppingList, voucher);
        order.setCustomer(customer);
        double total = checkout.calculateTotal(order);
        Assert.assertEquals(total, 4.5);

    }

    @Test(description = "invalid voucher, non-gold membership incurs US delivery fee")
    public void calculateTotalInValidVoucherNonGoldMembership(){
        Customer customer = new Customer(Membership.SILVER.toString(), "MyStreet 123, US");
        Voucher voucher = new Voucher("DummyVoucher", null, null);
        Order order = new Order(shoppingList, voucher);
        order.setCustomer(customer);
        double total = checkout.calculateTotal(order);
        Assert.assertEquals(total, 9.5);
    }

    @Test(description = "invalid voucher, non-gold membership incurs Global delivery fee")
    public void calculateTotalInValidVoucherNonGoldMembershipNonUs(){
        Customer customer = new Customer(Membership.SILVER.toString(), "MyStreet 123, France");
        Voucher voucher = new Voucher("DummyVoucher", null, null);
        Order order = new Order(shoppingList, voucher);
        order.setCustomer(customer);
        double total = checkout.calculateTotal(order);
        Assert.assertEquals(total, 14.5);
    }

}
