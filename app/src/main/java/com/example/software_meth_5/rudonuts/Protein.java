package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the protein for a sandwich.
 *
 * @author Carly Chick
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

    /**
     * Parses a string into its corresponding Protein value (case-insensitive).
     *
     * @param proteinStr the protein string to parse
     * @return the matching Protein constant if found; null if no match exists
     */
    public static Protein parseProtein(String proteinStr) {
        Protein[] allProteins = Protein.values();
        for (int i = 0; i < allProteins.length; i++) {
            if (allProteins[i].name().equalsIgnoreCase(proteinStr)) {
                return allProteins[i];
            }
        }
        return null;
    }
}
