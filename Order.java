package LuoPOS;

import java.util.ArrayList;
import java.util.List;

/**
 * The Order class represents an abstract order in the Luo POS System. It is the
 * base class for specific order types such as ToGoOrder and DineInOrder. An
 * order contains a list of menu items that the customer has selected.
 */
public abstract class Order {

	private ArrayList<MenuItem> order; // List of menu items in the order.

	/**
	 * Constructs an Order object. Initializes the list of menu items.
	 */
	public Order() {
		this.order = new ArrayList<MenuItem>();
	}

	/**
	 * Adds a menu item to the order.
	 *
	 * @param item The menu item to be added to the order.
	 */
	public void addItem(MenuItem item) {
		order.add(item);
	}

	/**
	 * Removes a menu item from the order.
	 *
	 * @param item The menu item to be removed from the order.
	 */
	public void removeItem(MenuItem item) {
		order.remove(item);
	}

	/**
	 * Gets the list of menu items in the order.
	 *
	 * @return An ArrayList containing the menu items in the order.
	 */
	public ArrayList<MenuItem> getItems() {
		return this.order;
	}

	/**
     * Sets the items for this order.
     *
     * @param items The list of MenuItem objects to set for this order.
     */
    public void setItems(List<MenuItem> items) {
        this.order = new ArrayList<>(items);
    }
}
