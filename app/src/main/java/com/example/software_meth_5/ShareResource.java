package com.example.software_meth_5;

import com.example.software_meth_5.rudonuts.Order;
import com.example.software_meth_5.rudonuts.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to store global data such as currentOrder and allOrders.
 * Works across all Activities in Android.
 *
 * @author Carly Chick
 */
public final class ShareResource {
    private static ShareResource resource;
    private final List<Order> allOrders;
    private final List<MenuItem> currentOrder;
    private int orderNumber = 0;

    /**
     * Initializes the global variables
     */
    private ShareResource() {
        allOrders = new ArrayList<>();
        currentOrder = new ArrayList<>();
    }

    /**
     * Gets instance of global variables.
     * @return instance
     */
    public static synchronized ShareResource getInstance() {
        if (resource == null) {
            resource = new ShareResource();
        }
        return resource;
    }

    /**
     * Gets all orders
     * @return all orders in array
     */
    public List<Order> getAllOrders() {
        return allOrders;
    }

    /**
     * Gets current order.
     * @return current order
     */
    public List<MenuItem> getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Gets the order number
     * @return order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets new order number
     * @param number order number
     */
    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }
}
