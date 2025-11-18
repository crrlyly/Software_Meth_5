package com.example.software_meth_5.rudonuts;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This enum class stores the flavors for a donut.
 *
 * @author Carly Chick
 */
public enum Flavor {
    GLAZED(DonutType.YEAST),
    CHOCOLATE(DonutType.YEAST),
    STRAWBERRY(DonutType.YEAST),
    VANILLA(DonutType.YEAST),
    MAPLE(DonutType.YEAST),
    CINNAMON_SUGAR(DonutType.YEAST),

    PLAIN(DonutType.CAKE),
    MARBLE(DonutType.CAKE),
    BLUEBERRY(DonutType.CAKE),

    POWDERED(DonutType.DONUTHOLE),
    SEMI_GLAZED(DonutType.DONUTHOLE),
    CINNAMON(DonutType.DONUTHOLE),

    PUMPKIN_SPICE(DonutType.SEASONAL),
    SPOOKY(DonutType.SEASONAL);

    private final DonutType type;

    /**
     * Constructor for a donut flavor with the donut type
     * @param type donut type
     */
    Flavor(DonutType type) { this.type = type; }

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
}
