package com.example.software_meth_5.rudonuts;

/**
 * This enum class stores the flavors for a donut.
 *
 * @author Alec Cotler
 */
public enum Flavor {
    GLAZED(DonutType.YEAST, "Glazed Yeast Donut", "glazedyeast"),
    CHOCOLATE(DonutType.YEAST, "Chocolate Yeast Donut", "chocoyeast"),
    STRAWBERRY(DonutType.YEAST, "Strawberry Yeast Donut", "strawberryyeast"),
    VANILLA(DonutType.YEAST, "Vanilla Yeast Donut", "vanillayeast"),
    MAPLE(DonutType.YEAST, "Maple Yeast Donut", "mapleyeast"),
    CINNAMON_SUGAR(DonutType.YEAST, "Cinnamon Sugar Yeast Donut", "cinasugaryeast"),

    PLAIN(DonutType.CAKE, "Plain Cake Donut", "plaincake"),
    MARBLE(DonutType.CAKE, "Marble Cake Donut", "marblecake"),
    BLUEBERRY(DonutType.CAKE, "Blueberry Cake Donut", "blueberrycake"),

    POWDERED(DonutType.DONUTHOLE, "Powdered Donut Hole", "powderhole"),
    SEMI_GLAZED(DonutType.DONUTHOLE, "Semi Glazed Donut Hole", "semiglazehole"),
    CINNAMON(DonutType.DONUTHOLE, "Cinnamon Donut Hole", "cinhole"),

    PUMPKIN_SPICE(DonutType.SEASONAL, "Pumpkin Spice Donut", "pumpkinspice"),
    SPOOKY(DonutType.SEASONAL, "Spooky Donut", "spooky");

    private final DonutType type;
    private final String name;
    private final String img;

    /**
     * Constructor for a donut flavor with the donut type
     * @param type donut type
     */
    Flavor(DonutType type, String name, String img) {
        this.type = type;
        this.name = name;
        this.img = img;
    }

    /**
     * Gets the name of the donut
     * @return donut name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the donut image
     * @return donut image
     */
    public String getImg() {
        return img;
    }

    /**
     * Gets the donut type
     * @return donut type
     */
    public DonutType getType() {
        return type;
    }
}
