package com.example.software_meth_5;

import com.example.software_meth_5.rudonuts.Order;
import com.example.software_meth_5.rudonuts.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to store global data such as currentOrder and allOrders.
 * Works across all Activities & Fragments in Android.
 *
 * @author Carly Chick
 */
public final class ShareResource {
    private static ShareResource resource;
    private final List<Order> allOrders;
    private final List<MenuItem> currentOrder;
    private int orderNumber = 0;

    private ShareResource() {
        allOrders = new ArrayList<>();
        currentOrder = new ArrayList<>();
    }

    public static synchronized ShareResource getInstance() {
        if (resource == null) {
            resource = new ShareResource();
        }
        return resource;
    }

    public List<Order> getAllOrders() {
        return allOrders;
    }

    public List<MenuItem> getCurrentOrder() {
        return currentOrder;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }
}
