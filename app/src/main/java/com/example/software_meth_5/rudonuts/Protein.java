package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the protein for a sandwich.
 *
 * @author Alec Cotler
 */
public enum Protein {
    BEEF(12.99),
    CHICKEN(10.99),
    SALMON(14.99);

    private final double price;

    /**
     * Constructor for a protein enum with a price
     * @param price price of protein
     */
    Protein(double price){
        this.price = price;
    }

    /**
     * Returns the price of the protein
     * @return price
     */
    public double getPrice() {
        return price;
    }

}
