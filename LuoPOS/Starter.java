package LuoPOS;

/**
 * The Starter class represents a starter item on the restaurant menu. It is a
 * specific type of MenuItem and inherits its properties from the MenuItem
 * class. Each starter has a name, price, and quantity (amount).
 */
public class Starter extends MenuItem {

	/**
	 * Constructs a Starter object with the specified name, price, and quantity.
	 *
	 * @param name   The name of the starter.
	 * @param price  The price of the starter.
	 * @param amount The quantity of the starter.
	 */
	public Starter(String name, double price, int amount) {
		super(name, price, amount);
	}
}
