package LuoPOS;

/**
 * The DineInOrder class represents a dine-in order placed by a customer. It is
 * a specific type of Order and inherits its properties from the Order class.
 * Each dine-in order has a table number, the number of people, and a list of
 * items.
 */
public class DineInOrder extends Order {

	private int tableNumber;
	private int numberOfPeople;

	/**
	 * Constructs a DineInOrder object with the specified table number and the
	 * number of people.
	 *
	 * @param tableNumber    The number of the table where the dine-in order is
	 *                       placed.
	 * @param numberOfPeople The number of people in the dine-in group.
	 */
	public DineInOrder(int tableNumber, int numberOfPeople) {
		super(); // Calls the constructor of the superclass (Order) to initialize the order items list.
		this.setTableNumber(tableNumber);
		this.setNumberOfPeople(numberOfPeople);
	}

	/**
	 * Gets the table number associated with this dine-in order.
	 *
	 * @return The number of the table where the dine-in order is placed.
	 */
	public int getTableNumber() {
		return this.tableNumber;
	}

	/**
	 * Sets the table number for this dine-in order.
	 *
	 * @param tableNumber The number of the table where the dine-in order is placed.
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * Gets the number of people in the dine-in group.
	 *
	 * @return The number of people in the dine-in group.
	 */
	public int getNumberOfPeople() {
		return this.numberOfPeople;
	}

	/**
	 * Sets the number of people for this dine-in order.
	 *
	 * @param numberOfPeople The number of people in the dine-in group.
	 */
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
}
