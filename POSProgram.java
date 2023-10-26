package LuoPOS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The main class representing the Point of Sale (POS) program. This class
 * handles the creation and management of orders in a restaurant system.
 * 
 * @author Steven Luo
 */
public class POSProgram {
	static ArrayList<Order> orders = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Main method to start the POS program. It displays the main menu and handles
	 * user inputs to create, display, and manage orders.
	 *
	 * @param args Command line arguments (not used in this program).
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the Luo POS System!");

		boolean running = true;
		while (running) {
			System.out.println("\nMain Menu:");
			System.out.println("1. Enter To-Go Order");
			System.out.println("2. Enter Dine-In Order");
			System.out.println("3. Display Orders");
			System.out.println("4. Remove Order");
			System.out.println("5. Remove All Orders");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			int choice = getUserChoiceInteger();

			switch (choice) {
			case 1:
				createToGoOrder();
				break;
			case 2:
				createDineInOrder();
				break;
			case 3:
				displayOrders();
				break;
			case 4:
				removeOrder();
				break;
			case 5:
				removeAllOrders();
				break;
			case 6:
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}

		System.out.println("Thank you for using the POS System. Goodbye!");
	}

	/* User Input Methods */
	// ------------------ //
	/**
	 * Ensures valid positive integer input from the user.
	 *
	 * @return A positive integer entered by the user.
	 */
	public static int getUserChoiceInteger() {
		int choice = -1;
		boolean validInput = false;
		while (!validInput) {
			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice > 0) {
					validInput = true;
				} else {
					System.out.println("Invalid input. Please enter a positive integer.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a positive integer.");
			}
		}
		return choice;
	}

	/**
	 * Ensures valid double input from the user.
	 *
	 * @param errorMessage The error message to display if the input is invalid.
	 * @return A valid double entered by the user.
	 */
	public static double getUserInputDouble(String errorMessage) {
		while (true) {
			try {
				return Math.ceil(Double.parseDouble(scanner.nextLine()) * 100) / 100;
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
			}
		}
	}

	/* Order Creation Methods */
	// ---------------------- //
	/**
	 * Handles the creation of a To-Go order, taking customer name and phone number
	 * as input.
	 */
	public static void createToGoOrder() {
		System.out.println("Creating To-Go Order...");

		System.out.print("Enter customer name: ");
		String customerName = scanner.nextLine();

		System.out.print("Enter phone number: ");
		String phoneNumber = scanner.nextLine();

		Order toGoOrder = new ToGoOrder(customerName, phoneNumber);
		toGoOrder = enterOrderDetails(toGoOrder);
		orders.add(toGoOrder);

		System.out.println("To-Go Order created successfully!");
	}

	/**
	 * Handles the creation of a Dine-In order, taking table number and number of
	 * people as input.
	 */
	public static void createDineInOrder() {
		System.out.println("Creating Dine In Order...");

		System.out.print("Enter table number: ");
		int tableNumber = getUserChoiceInteger();

		System.out.print("Enter number of people: ");
		int numberOfPeople = getUserChoiceInteger();

		Order dineInOrder = new DineInOrder(tableNumber, numberOfPeople);
		dineInOrder = enterOrderDetails(dineInOrder);
		orders.add(dineInOrder);

		System.out.println("Dine In Order created successfully!");
	}

	/* Order Details Methods */
	// --------------------- //
	/**
	 * Handles the details of adding items to an order.
	 *
	 * @param order The order to which items are being added.
	 * @return The updated order after adding items.
	 */
	public static Order enterOrderDetails(Order order) {
		boolean addingItems = true;
		boolean itemsAdded = false;

		while (addingItems) {
			System.out.println("\nMenu:");
			printMenu(createMenu());
			System.out.print("Enter item category (or 0 to finish order and return to main menu): ");
			String itemCategory = scanner.nextLine();

			if (itemCategory.equals("0")) {
				if (!itemsAdded) {
					System.out.println("No items added. Returning to main menu.");
					return order; // Don't create an order if no items are added
				}
				addingItems = false;
			} else {
				itemsAdded = processMenuItem(order, itemCategory, itemsAdded);
			}
		}
		return order;
	}

	/**
	 * Processes the details of adding a specific item to the order.
	 *
	 * @param order        The order to which the item is being added.
	 * @param itemCategory The category of the item (e.g., beverages, starters,
	 *                     entrees, desserts).
	 * @param itemsAdded   A boolean flag indicating whether items have already been
	 *                     added to the order.
	 * @return True if items have been added to the order, false otherwise.
	 */
	public static boolean processMenuItem(Order order, String itemCategory, boolean itemsAdded) {
		if (isValidItemCategory(itemCategory)) {
			System.out.print("Enter item name: ");
			String itemName = scanner.nextLine();

			System.out.print("Enter item price: ");
			double itemPrice = getUserInputDouble("Invalid input. Please enter a valid price.");

			System.out.print("Enter quantity: ");
			int quantity = getUserChoiceInteger();

			MenuItem item = createMenuItem(itemCategory, itemName, itemPrice, quantity);
			if (item != null) {
				order.addItem(item);
				itemsAdded = true;
			}
		} else {
			System.out.println("Invalid item category.");
		}
		return itemsAdded;
	}

	/**
	 * Creates a MenuItem object based on the item category, name, price, and
	 * quantity.
	 *
	 * @param itemCategory The category of the item (e.g., beverages, starters,
	 *                     entrees, desserts).
	 * @param itemName     The name of the item.
	 * @param itemPrice    The price of the item.
	 * @param quantity     The quantity of the item.
	 * @return The created MenuItem object.
	 */
	public static MenuItem createMenuItem(String itemCategory, String itemName, double itemPrice, int quantity) {
		switch (itemCategory.toLowerCase()) {
		case "beverages":
			return new Beverage(itemName, itemPrice, quantity);
		case "starters":
			return new Starter(itemName, itemPrice, quantity);
		case "entrees":
			return new Entree(itemName, itemPrice, quantity);
		case "desserts":
			return new Dessert(itemName, itemPrice, quantity);
		default:
			System.out.println("Invalid item category.");
			return null;
		}
	}

	/* Validation Methods */
	// ------------------ //
	/**
	 * Checks if the provided item category is valid (beverages, starters, entrees,
	 * desserts).
	 *
	 * @param itemCategory The category of the item to validate.
	 * @return True if the item category is valid, false otherwise.
	 */
	public static boolean isValidItemCategory(String itemCategory) {
		return Arrays.asList("beverages", "starters", "entrees", "desserts").contains(itemCategory.toLowerCase());
	}

	/**
	 * Validates if the provided item name contains only letters and spaces.
	 *
	 * @param itemName The item name to validate.
	 * @return True if the item name is valid, false otherwise.
	 */
	public static boolean isValidItemName(String itemName) {
		if (itemName.matches("[a-zA-Z ]+")) {
			return true;
		} else {
			System.out.println("Invalid item name. Item name must contain only letters and spaces.");
			return false;
		}
	}

	/* Order Management Methods */
	// ------------------------ //
	/**
	 * Displays the list of orders and their details.
	 */
	public static void displayOrders() {
		if (orders.isEmpty()) {
			System.out.println("No orders available.");
		} else {
			System.out.println("Orders:");
			for (int i = 0; i < orders.size(); i++) {
				System.out.println();
				System.out.println(
						(i + 1) + ". Order Type: " + (orders.get(i) instanceof ToGoOrder ? "To-Go" : "Dine-In"));
				displayReceipt(orders.get(i));
				System.out.println("---------------------------------");
			}
		}
	}

	/**
	 * Allows the user to remove a specific order from the list of orders.
	 */
	public static void removeOrder() {
		if (orders.isEmpty()) {
			System.out.println("No orders available to remove.");
		} else {
			System.out.print("Enter the order number to remove: ");
			int orderNumber = getUserChoiceInteger();

			if (orderNumber >= 1 && orderNumber <= orders.size()) {
				orders.remove(orderNumber - 1);
				System.out.println("Order removed successfully.");
			} else {
				System.out.println("Invalid order number. Please try again.");
			}
		}
	}

	/**
	 * Removes all orders from the list.
	 */
	public static void removeAllOrders() {
		orders.clear();
		System.out.println("All orders removed successfully.");
	}

	/* Menu Related Methods */
	// -------------------- //
	public static String[][] createMenu() {
		String[][] menu = {
	 		// Beverages [1][0]
			{"Coke 2.99", "Pepsi 2.00", "Sprite 2.00", "Lemonade 2.50", "Iced Tea 2.50", "Coffee 2.50"},
			
			// Starters [1][1]
			{"Wings 5.00", "Nachos 6.00", "Mozzarella Sticks 4.00", "Garlic Bread 3.50", "Fried Calamari 7.00", "Bruschetta 4.50"},
			
			// Entrees [1][2]
			{"Steak 18.00", "Chicken Alfredo 14.00", "Lasagna 12.00", "Fish and Chips 15.00", "Burger 10.00", "Pizza 13.00"},
		
			// Desserts [1][3]
			{ "Cheesecake 4.00", "Brownie Sundae 5.00", "Apple Pie 4.50", "Ice Cream 3.00", "Chocolate Cake 4.50", "Tiramisu 5.50"} 
		};
		return menu;
	}

	/**
	 * Prints the menu in a formatted manner.
	 *
	 * @param menu The 2D array representing the menu items and their prices.
	 */
	public static void printMenu(String[][] menu) {
		String[] categories = { "Beverages", "Starters", "Entrees", "Desserts" };

		for (int i = 0; i < menu.length; i++) {
			System.out.println(categories[i]);
			for (int j = 0; j < menu[i].length; j += 3) {
				System.out.printf("| %-25s | %-25s | %-25s |%n", menu[i][j], menu[i][j + 1], menu[i][j + 2]);
			}
			for (int k = 0; k < 85; k++) {
				System.out.print("-");
			}
			System.out.println();
		}
	}

	/* Receipt Related Methods */
	// ----------------------- //
	/**
	 * Displays the receipt for a given order, including details like restaurant
	 * name, items, total without tax, tax, and total with tax.
	 *
	 * @param order The order for which the receipt is displayed.
	 */
	public static void displayReceipt(Order order) {
		System.out.printf("%27s%n", "Steven's Restaurant"); // Center justified restaurant name placeholder
		if (order instanceof DineInOrder) {
			System.out.printf("Table #: %d%nAmount of People: %d%n", ((DineInOrder) order).getTableNumber(),
					((DineInOrder) order).getNumberOfPeople());
			System.out.printf("%-15s %s%n", "Type of Order:", "Dine-In");
		} else if (order instanceof ToGoOrder) {
			System.out.println("Customer Name: " + ((ToGoOrder) order).getCustomerName());
			System.out.println("Phone Number: " + ((ToGoOrder) order).getPhoneNumber());
			System.out.printf("%-15s %s%n", "Type of Order:", "To-Go");
		}

		for (MenuItem item : order.getItems()) {
			System.out.printf("%-4d %-20s $%.2f%n", item.getAmount(), item.getName(), item.getPrice());
		}

		calculateAndDisplayTotalWithAndWithoutTax(order);
	}

	/**
	 * Calculates and displays the total without tax, tax, and total with tax for a
	 * given order.
	 *
	 * @param order The order for which totals are calculated and displayed.
	 */
	public static void calculateAndDisplayTotalWithAndWithoutTax(Order order) {
		double totalWithoutTax = 0;
		for (MenuItem item : order.getItems()) {
			totalWithoutTax += item.getPrice() * item.getAmount();
		}
		System.out.printf("\n%-26s $%.2f%n", "Total Without Tax:", totalWithoutTax);

		double tax = totalWithoutTax * 0.092; // Assuming 9.2% tax rate
		System.out.printf("%-26s $%.2f%n", "Tax:", tax);

		double totalWithTax = totalWithoutTax + tax;
		System.out.printf("%-26s $%.2f%n", "Total with Tax:", totalWithTax);
	}

}
