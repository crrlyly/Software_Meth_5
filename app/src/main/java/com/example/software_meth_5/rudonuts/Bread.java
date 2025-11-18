package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the bread for a sandwich.
 *
 * @author Carly Chick
 */
public enum Bread {
    BAGEL,
    WHEATBREAD,
    SOURDOUGH;

    /**
     * Parses a string into its corresponding Bread value (case-insensitive).
     *
     * @param breadStr the bread string to parse
     * @return the matching Bread constant if found; null if no match exists
     */
    public static Bread parseBread(String breadStr) {
        Bread[] allBreads = Bread.values();
        for (int i = 0; i < allBreads.length; i++) {
            if (allBreads[i].name().equalsIgnoreCase(breadStr)) {
                return allBreads[i];
            }
        }
        return null;
    }
}
