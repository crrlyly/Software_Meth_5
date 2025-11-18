package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the donut types for a donut.
 *
 * @author Carly Chick
 */
public enum DonutType {
    YEAST(1.99, "YeastDonut.png"),
    CAKE(2.19, "CakeDonut.png"),
    DONUTHOLE(0.39, "DonutHole.png"),
    SEASONAL(2.49, "SeasonalDonut.png");

    private final double price;
    private final String img;

    /**
     * Constructor of a donut type with the price and image of the donut
     * @param price price
     * @param img donut image
     */
    DonutType(double price, String img) {
        this.price = price;
        this.img = img;
    }

    /**
     * Get the price of the donut type
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the image of the donut type
     * @return image path as string
     */
    public String getImg() {
        return img;
    }
}
