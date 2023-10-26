package LuoPOS;

/**
 * The ToGoOrder class represents a to-go order placed by a customer. It is a
 * specific type of Order and inherits its properties from the Order class. Each
 * to-go order has a customer name, phone number, and a list of items.
 */
public class ToGoOrder extends Order {

	private String customerName;
	private String phoneNumber;

	/**
	 * Constructs a ToGoOrder object with the specified customer name and phone
	 * number.
	 *
	 * @param customerName The name of the customer placing the to-go order.
	 * @param phoneNumber  The phone number of the customer placing the to-go order.
	 */
	public ToGoOrder(String customerName, String phoneNumber) {
		super(); // Calls the constructor of the superclass (Order) to initialize the order items list.
		this.setCustomerName(customerName);
		this.setPhoneNumber(phoneNumber);
	}

	/**
	 * Gets the phone number associated with this to-go order.
	 *
	 * @return The phone number of the customer placing the to-go order.
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number for this to-go order.
	 *
	 * @param phoneNumber The phone number of the customer placing the to-go order.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the customer name associated with this to-go order.
	 *
	 * @return The name of the customer placing the to-go order.
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * Sets the customer name for this to-go order.
	 *
	 * @param customerName The name of the customer placing the to-go order.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
