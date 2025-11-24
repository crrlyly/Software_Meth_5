package com.example.software_meth_5.rudonuts;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a person's order.
 *
 * @author Carly Chick
 */
public class Order {
    private int orderNumber;
    private List<MenuItem> listOfItems;

    /**
     * Constructor for an order.
     * @param orderNumber order number
     * @param listOfItems list of menu items in order
     */
    public Order(int orderNumber, List<MenuItem> listOfItems) {
        this.orderNumber = orderNumber;
        this.listOfItems = new ArrayList<>();
    }

    /**
     * Get the order number
     * @return order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns a copy of the list of items
     * @return the list of items in order
     */
    public List<MenuItem> getListOfItems() {
        return listOfItems;
    }

    /**
     * Checks to see if orders are the same
     * @param obj   the reference object with which to compare.
     * @return true if the same; otherwise false
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Order order){
            return this.orderNumber == order.orderNumber;
        }
        return false;
    }

    /**
     * Changes order number to string
     * @return order number
     */
    @Override
    public String toString() {
        return String.valueOf(orderNumber);
    }
}
