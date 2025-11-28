package com.example.software_meth_5.rudonuts;

/**
 * This class represents a donut menu item.
 *
 * @author Alec Cotler
 */
public class Donut extends MenuItem{
    private DonutType type;
    private Flavor flavor;

    /**
     * Constructor for a donut menu item
     * @param quantity quantity
     * @param type donut type
     * @param flavor donut flavor
     */
    public Donut(int quantity, DonutType type, Flavor flavor) {
        super(quantity);
        this.type = type;
        this.flavor = flavor;
    }

    /**
     * Calculates price of donut
     * @return price of donut(s)
     */
    public double price(){
        return (type.getPrice() * quantity);
    };

    /**
     * Returns donut flavor
     * @return donut flavor
     */
    public Flavor getFlavor() { return flavor; }

    /**
     * Checks to see if donuts are the same so quantity can stack.
     *
     * @param obj   the reference object with which to compare.
     * @return true if they are the same; otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Donut donut){
            return this.type.equals(donut.type) && this.flavor.equals(donut.flavor);
        }
        return false;
    }

    /**
     * Creates and returns a stringified version of a donut.
     * @return string of donut information
     */
    @Override
    public String toString(){
        return flavor.name() + " " + type.name() + " donut (" + quantity + ")";
    }
}
