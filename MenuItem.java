package LuoPOS;

/**
 * The MenuItem class represents an item on the restaurant menu. Each menu item
 * has a name, price, and quantity (amount).
 */
public class MenuItem {
	private String name;
	private double price;
	private int amount;

	/**
	 * Constructs a MenuItem object with the specified name, price, and quantity.
	 *
	 * @param name   The name of the menu item.
	 * @param price  The price of the menu item.
	 * @param amount The quantity of the menu item.
	 */
	public MenuItem(String name, double price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	/**
	 * Gets the name of the menu item.
	 *
	 * @return The name of the menu item.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the price of the menu item.
	 *
	 * @return The price of the menu item.
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Gets the quantity of the menu item.
	 *
	 * @return The quantity of the menu item.
	 */
	public int getAmount() {
		return this.amount;
	}
}
