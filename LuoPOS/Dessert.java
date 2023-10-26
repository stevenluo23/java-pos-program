package LuoPOS;

/**
 * The Dessert class represents a dessert item on the restaurant menu. It is a
 * specific type of MenuItem and inherits its properties from the MenuItem
 * class. Each dessert has a name, price, and quantity (amount).
 */
public class Dessert extends MenuItem {

	/**
	 * Constructs a Dessert object with the specified name, price, and quantity.
	 *
	 * @param name   The name of the dessert.
	 * @param price  The price of the dessert.
	 * @param amount The quantity of the dessert.
	 */
	public Dessert(String name, double price, int amount) {
		super(name, price, amount);
	}
}
