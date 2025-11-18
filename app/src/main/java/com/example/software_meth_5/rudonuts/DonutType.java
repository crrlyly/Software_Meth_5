package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the donut types for a donut.
 *
 * @author Carly Chick
 */
public enum DonutType {
    YEAST(1.99),
    CAKE(2.19),
    DONUTHOLE(0.39),
    SEASONAL(2.49);

    private final double price;

    /**
     * Constructor of a donut type with the price and image of the donut
     * @param price price
     */
    DonutType(double price) {
        this.price = price;
    }

    /**
     * Get the price of the donut type
     * @return price
     */
    public double getPrice() {
        return price;
    }

}
