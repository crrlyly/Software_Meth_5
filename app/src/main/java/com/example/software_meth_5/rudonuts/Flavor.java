package com.example.software_meth_5.rudonuts;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This enum class stores the flavors for a donut.
 *
 * @author Carly Chick
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
     * Returns a list of the flavors by donut type that was selected
     * @param type donut type selected
     * @return list of flavors of the donut type
     */
    public static List<Flavor> byType(DonutType type) {
        return Arrays.stream(values())
                .filter(f -> f.type == type)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    /**
     * Method that returns the res id of the image.
     *
     * @param context current activity
     * @param img the image
     * @return the res id
     */
    public static int getResID(Context context, String img) {
        return context.getResources().getIdentifier(img, "drawable", context.getPackageName());
    }
}
