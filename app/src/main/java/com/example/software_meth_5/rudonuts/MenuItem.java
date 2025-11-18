package com.example.software_meth_5.rudonuts;

/**
 * This class represents a menu item.
 *
 * @author Carly Chick
 */
public abstract class MenuItem {
    protected int quantity;

    /**
     * Constructor for a menu item.
     * @param quantity the quantity
     */
    public MenuItem(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Abstract method that must be implemented by donut, coffee, and sandwich classes.
     * @return the price
     */
    public abstract double price();

    /**
     * Get the quantity of the menu item.
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
