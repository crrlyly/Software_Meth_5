package com.example.software_meth_5;

/**
 * Listener class for donut recycler.
 *
 * @author Carly Chick
 */
public interface OnDonutClickListener {
    /**
     * Handler to add donut to order.
     * @param position donut position in recycler
     */
    void onAddClick(int position);

    /**
     * Handler to increase quantity of donut.
     * @param position donut position in recycler
     */
    void onIncreaseQuantity(int position);

    /**
     * Handler to decrease quantity of donut.
     * @param position donut position in recycler
     */
    void onDecreaseQuantity(int position);
}


