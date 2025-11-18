package com.example.software_meth_5.rudonuts;
import java.util.ArrayList;

/**
 * This class represents a coffee menu item.
 *
 * @author Alec Cotler
 */
public class Coffee extends MenuItem{
    private CupSize size;
    private ArrayList<AddIns> addIns;
    private final static double ADDIN_COST = 0.25;

    /**
     * Constructor for a coffee.
     * @param quantity number of coffees
     * @param size size of the coffee
     * @param addIns add ins for the coffee
     */
    public Coffee(int quantity, CupSize size, ArrayList<AddIns> addIns) {
        super(quantity);
        this.size = size;
        this.addIns = addIns;
    }

    /**
     * Calculates the price of the coffee (no tax)
     * @return the price
     */
    public double price(){
        return (size.getBasePrice() + (addIns.toArray().length * ADDIN_COST)) * quantity;
    };

    /**
     * Checks if coffee items are the same as eachother to see if quantity should stack.
     * @param obj   the reference object with which to compare.
     * @return true if objects are the same; otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coffee coffee){
            return this.size.equals(coffee.size) && this.addIns.equals(coffee.addIns);
        }
        return false;
    }

    /**
     * Returns a string of the coffee order to show on current orders.
     * @return a string of the coffee order
     */
    @Override
    public String toString(){
        String addInsString = (addIns.isEmpty()) ? "nothing" : addIns.toString();
        return size.name() + " coffee (" + quantity + ") with " + addInsString;
    }
}
