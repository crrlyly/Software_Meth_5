package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the add-ons for a sandwich.
 *
 * @author Alec Cotler
 */
public enum AddOns {
    LETTUCE(0.30),
    TOMATO(0.30),
    CHEESE(1.00),
    ONION(0.30);

    private final double price;

    /**
     * Constructor for Addon enum with price.
     * @param price the price of the addon
     */
    AddOns(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the addon
     * @return the price of the addon
     */
    public double getPrice() {
        return price;
    }
}
