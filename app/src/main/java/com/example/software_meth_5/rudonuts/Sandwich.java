package com.example.software_meth_5.rudonuts;
import java.util.ArrayList;

/**
 * This class represents a sandwich menu item.
 *
 * @author Carly Chick
 */
public class Sandwich extends MenuItem{
    protected Bread bread;
    protected Protein protein;
    protected ArrayList<AddOns> addOns;

    /**
     * Constructor for a sandwich
     * @param quantity quantity
     * @param bread bread type
     * @param protein protein type
     * @param addOns list of addons
     */
    public Sandwich(int quantity, Bread bread, Protein protein, ArrayList<AddOns> addOns) {
        super(quantity);
        this.bread = bread;
        this.protein = protein;
        this.addOns = addOns;
    }

    /**
     * Calculates the price of the sandwich
     * @return price
     */
    public double price(){
        double price = 0;
        price = price + protein.getPrice();
        for(AddOns add: addOns){
            price = price + add.getPrice();
        }
        return price * quantity;
    };

    /**
     * Checks to see if sandwiches are the same to see if quantity stacks.
     * @param obj   the reference object with which to compare.
     * @return true if equal; otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Sandwich sandwich){
            return this.bread.equals(sandwich.bread) && this.protein.equals(sandwich.protein) && this.addOns.equals(sandwich.addOns);
        }
        return false;
    }

    /**
     * Creates a string to represent the sandwich for the current orders.
     * @return string of sandwich
     */
    @Override
    public String toString(){
        String addOnsString = (addOns.isEmpty()) ? "nothing" : addOns.toString();
        return protein.name() + " sandwich (" + quantity + ") on " + bread.name() + " with " + addOnsString;
    }

    public Protein getProtein() {
        return protein;
    }
}
