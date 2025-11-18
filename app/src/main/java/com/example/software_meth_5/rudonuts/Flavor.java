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
    GLAZED(DonutType.YEAST, "glazedyeast"),
    CHOCOLATE(DonutType.YEAST, "chocoyeast"),
    STRAWBERRY(DonutType.YEAST, "strawberryyeast"),
    VANILLA(DonutType.YEAST, "vanillayeast"),
    MAPLE(DonutType.YEAST, "mapleyeast"),
    CINNAMON_SUGAR(DonutType.YEAST, "cinasugaryeast"),

    PLAIN(DonutType.CAKE, "plaincake"),
    MARBLE(DonutType.CAKE, "marblecake"),
    BLUEBERRY(DonutType.CAKE, "blueberrycake"),

    POWDERED(DonutType.DONUTHOLE, "powderehole"),
    SEMI_GLAZED(DonutType.DONUTHOLE, "semiglazehole"),
    CINNAMON(DonutType.DONUTHOLE, "cinhole"),

    PUMPKIN_SPICE(DonutType.SEASONAL, "pumpkinspice"),
    SPOOKY(DonutType.SEASONAL, "spooky");

    private final DonutType type;
    private final String img;

    /**
     * Constructor for a donut flavor with the donut type
     * @param type donut type
     */
    Flavor(DonutType type, String img) {
        this.type = type;
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
