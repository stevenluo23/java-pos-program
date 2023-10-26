package LuoPOS;

/**
 * The Beverage class represents a beverage item on the restaurant menu. It is a
 * specific type of MenuItem and inherits its properties from MenuItem class.
 * Each beverage has a name, price, and quantity (amount).
 */
public class Beverage extends MenuItem {

	/**
	 * Constructs a Beverage object with the specified name, price, and quantity.
	 *
	 * @param name   The name of the beverage.
	 * @param price  The price of the beverage.
	 * @param amount The quantity of the beverage.
	 */
	public Beverage(String name, double price, int amount) {
		super(name, price, amount);
	}
}
