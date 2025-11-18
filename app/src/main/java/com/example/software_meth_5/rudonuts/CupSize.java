package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the cup sizes for a coffee.
 *
 * @author Carly Chick
 */
public enum CupSize {
    SHORT(2.39),
    TALL(2.99),
    GRANDE(3.59),
    VENTI(4.19);

    private final double basePrice;

    /**
     * Cup size constructor with base-price.
     * @param basePrice price of the cup size
     */
    CupSize(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Get the base price of the coffee size.
     * @return base price
     */
    public double getBasePrice() {
        return basePrice;
    }
}
