package LuoPOS;

/**
 * The Entree class represents an entree item on the restaurant menu. It is a
 * specific type of MenuItem and inherits its properties from the MenuItem
 * class. Each entree has a name, price, and quantity (amount).
 */
public class Entree extends MenuItem {

	/**
	 * Constructs an Entree object with the specified name, price, and quantity.
	 *
	 * @param name   The name of the entree.
	 * @param price  The price of the entree.
	 * @param amount The quantity of the entree.
	 */
	public Entree(String name, double price, int amount) {
		super(name, price, amount);
	}
}
