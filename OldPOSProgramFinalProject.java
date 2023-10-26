/*
Author: Steven Luo
Date: 02/27/2023

This program is a text/console based point-of-sale
software with the ability to process and store to-go
and dine-in orders. It also has the ability to display
receipts, reset orders, and store order information such
as name, phone number, table number, and amount of people. 
*/

import java.util.Scanner;

public class OldPOSProgramFinalProject {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to LuoPOS System! (Main menu)");

		// Initialize menu used for POS program
		String[][][] menu = { 
				{ // Beverages (1)
					{ "Coke", "2.99" }, { "Pepsi", "2.00" }, { "Sprite", "2.00" }, { "Lemonade", "2.50" },
					{ "Iced Tea", "2.50" }, { "Coffee", "2.50" } },
				{ // Starters (2)
					{ "Wings", "5.00" }, { "Nachos", "6.00" }, { "Mozzarella Sticks", "4.00" },
					{ "Garlic Bread", "3.50" }, { "Fried Calamari", "7.00" }, { "Bruschetta", "4.50" } },
				{ // Main Courses (3)
					{ "Steak", "18.00" }, { "Chicken Alfredo", "14.00" }, { "Lasagna", "12.00" },
					{ "Fish and Chips", "15.00" }, { "Burger", "10.00" }, { "Pizza", "13.00" } },
				{ // Desserts (4)
					{ "Cheesecake", "4.00" }, { "Brownie Sundae", "5.00" }, { "Apple Pie", "4.50" },
					{ "Ice Cream", "3.00" }, { "Chocolate Cake", "4.50" }, { "Tiramisu", "5.50" } } };

		// Create an order array that stores the items and their prices
		// for a corresponding order number. The last index in the second
		// level of the order array is reserved for the order info.
		String[][][] order = new String[10][11][2];

		// Create an orderNum variable used as an index to track individual orders
		int orderNum = 0;

		while (true) {
			if (orderNum < 10) {
				System.out.print(
						"\nPlease pick an option: \n1. To-Go Order\n2. Dine-In Order\n3. Display Orders\n4. Reset All Orders\n5. Exit\n> ");
				String option = input.nextLine();
				if (option.equals("1")) {
					toGoOrder(menu, order, orderNum);
					// Add 1 to orderNum to store the next order in order array.
					orderNum++;
				} else if (option.equals("2")) {
					dineInOrder(menu, order, orderNum);
					// Add 1 to orderNum to store the next order in order array.
					orderNum++;
				} else if (option.equals("3")) {
					displayOrderReceipts(order);
				} else if (option.equals("4")) {
					orderNum = resetAllOrders(order, orderNum);
				} else if (option.equals("5")) {
					System.out.println("Goodbye!");
					break;
				} else {
					System.out.println("Invalid option! Please try again.");
				}
			} else {
				System.out.print(
						"\nYou have reached the maximum amount of orders (10). Would you like to display orders or reset all orders (0 to reset, 1 to display)?\n> ");
				String maxOption = input.nextLine();
				if (maxOption.equals("0")) {
					orderNum = resetAllOrders(order, orderNum);
				} else if (maxOption.equals("1")) {
					displayOrderReceipts(order);
				} else {
					System.out.println("Invalid option! Please try again.");
				}
			}
		}
	}
	
	public static void dineInOrder(String[][][] menu, String[][][] order, int orderNum) {
		Scanner input = new Scanner(System.in);

		// Get table number
		String tableNumber = getTableNumber();

		// Get number of people dining in
		String numOfPeople = getNumOfPeople();

		// Create dine in order.
		createOrder(menu, order, orderNum, tableNumber, numOfPeople);
	}
	
	public static void toGoOrder(String[][][] menu, String[][][] order, int orderNum) {
		Scanner input = new Scanner(System.in);

		// Get name for to-go order
		System.out.print("Enter a name for your order.\n> ");
		String name = input.nextLine();

		// Get phone number
		String phoneNumber = getPhoneNumber();

		// Create to-go order.
		createOrder(menu, order, orderNum, name, phoneNumber);
	}
	
	public static void createOrder(String[][][] menu, String[][][] order, int orderNum, String nameOrTable,
			String phoneOrPeople) {
		Scanner input = new Scanner(System.in);

		// Display menu categories and prompt user to pick one. Category number is the
		// tab number that identifies a section of the menu, such as Drinks, Starters,
		// Main Courses, or Desserts.
		int categoryNum = selectMenuCategory(menu);

		// Create an integer variable that is used to control the next item index
		// in an order.
		int item = 0;

		// Create a double variable used to store total price of an order.
		double priceTotal = 0;

		// Add name or table number and phone or amount of people to the current order.
		order[orderNum][10][0] = nameOrTable;
		order[orderNum][10][1] = phoneOrPeople;

		// Use a sentinel while loop to continuously prompt the user to input a valid
		// menu item.
		boolean enterItemLoop = true;

		while (enterItemLoop) {
			// Use a boolean variable to manage error message displayed if user
			// enters in an item that is not in the menu category selected.
			boolean noItemFound = true;

			System.out.print("Enter a menu item by its name, or enter 0 to finish order (non case-sensitive).\n> ");
			String usersOrder = input.nextLine();
			if (usersOrder.equals("0")) {
				enterItemLoop = false;
				displayReceipt(order, orderNum, priceTotal, nameOrTable, phoneOrPeople);
				break;
			}

			for (int i = 0; i < menu[categoryNum - 1].length; i++) {
				if (usersOrder.equalsIgnoreCase(menu[categoryNum - 1][i][0])) {
					// Item is found, so assign noItemFound to false to prevent error
					// message from displaying at the end.
					noItemFound = false;

					// Use a boolean variable to control sentinel while loop.
					boolean reEnterItemLoop = true;

					// Display what item and order number the user is on.
					System.out.printf(
							"Order #%d (Max 10 Orders)\n-----------------------------------------\nItem #%d: %s $%s (Max 10 Items).\n",
							(orderNum + 1), (item + 1), menu[categoryNum - 1][i][0], menu[categoryNum - 1][i][1]);

					// Add menu item and item price to order.
					order[orderNum][item][0] = menu[categoryNum - 1][i][0];
					order[orderNum][item][1] = menu[categoryNum - 1][i][1];
					// Add price to priceTotal, which will be used in the receipt.
					priceTotal += Double.parseDouble(order[orderNum][item][1]);

					// Create an if statement to check if the order is reaching max items
					if (item == 9) {
						System.out.println("The maximum item amount in an order is 10.");
						while (true) {
							System.out.print(
									"Would you like to reset your current order or display receipt (1: Reset, 2: Display)?\n> ");
							String resetOrDisplay = input.nextLine();
							if (resetOrDisplay.equals("1")) {
								// Reset item index, priceTotal, and prompt the user to select
								// another category.
								resetCurrentOrder(order, orderNum, item);
								item = 0;
								priceTotal = 0;
								categoryNum = selectMenuCategory(menu);

								// Skip the prompt which asks the user if they would like to
								// enter in another item or end the order.
								reEnterItemLoop = false;
								break;
							} else if (resetOrDisplay.equals("2")) {
								// Display receipt and return to main menu to enter in another order.
								displayReceipt(order, orderNum, priceTotal, nameOrTable, phoneOrPeople);
								reEnterItemLoop = false;
								enterItemLoop = false;
								break;
							} else {
								System.out.println("Invalid option! Please try again.");
							}
						}
					}

					// Create a second sentinel while loop to continuously prompt the user
					// to enter a valid option, either Y/Yes or N/No, non case-sensitive.
					while (reEnterItemLoop) {
						System.out.print(
								"Would you like to re-enter in an item in this category (Y/N, 0 to end order)?\n> ");
						String answer = input.nextLine();
						if (answer.equalsIgnoreCase("No") || answer.equalsIgnoreCase("N")) {
							// Prompt the user to select another menu category and add 1
							// to item index to add in another item into the order array.
							categoryNum = selectMenuCategory(menu);
							reEnterItemLoop = false;
							item++;
						} else if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("Y")) {
							// Add 1 to item index to add in another item into the order array,
							// and redisplay the category which the user selected.
							item++;
							menuFormatting(menu, categoryNum);
							reEnterItemLoop = false;
						} else if (answer.equals("0")) {
							// End order and display receipt.
							reEnterItemLoop = false;
							enterItemLoop = false;
							displayReceipt(order, orderNum, priceTotal, nameOrTable, phoneOrPeople);
						} else {
							System.out.println("Invalid option, please try again.");
						}
					}
				}
			}

			// If noItemFound is true, display an error message and the
			// tab/category of the menu that the user selected.
			if (noItemFound) {
				System.out.print("Could not find item, please enter the item again.");
				menuFormatting(menu, categoryNum);
			}
		}
	}

	public static int resetAllOrders(String[][][] order, int orderNum) {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print("Are you sure you want to reset all orders (0 to Reset, 1 to Return to Main Menu)?\n> ");
			String decisionStr = input.nextLine();
			// Check if input is either 1 or 0.
			if (decisionStr.length() == 1 && decisionStr.charAt(0) >= '0' && decisionStr.charAt(0) <= '1') {
				int decision = Integer.parseInt(decisionStr);
				if (decision == 0) {
					orderNum = 0;
					// Reassign all orders and their items and prices to null,
					// effectively resetting all orders.
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							order[i][j][0] = null;
							order[i][j][1] = null;
						}
					}
					System.out.println("Orders have been reset.");
					break;
				} else if (decision == 1) {
					break;
				}
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}
		return orderNum;
	}
	
	public static void resetCurrentOrder(String[][][] order, int orderNum, int item) {
		// Reassign current order items and their prices to null,
		// effectively resetting the current order.
		for (item = 0; item < 10; item++) {
			order[orderNum][item][0] = null;
			order[orderNum][item][1] = null;
		}
	}
	
	public static int selectMenuCategory(String[][][] menu) {
		Scanner input = new Scanner(System.in);
		int categoryNum;
		while (true) {
			System.out.printf(
					"\nSelect a menu category to begin entering your order (1, 2, 3, 4).\n| %-10s | %-12s | %-17s| %-11s |\n> ",
					"Drinks (1)", "Starters (2)", "Main Courses (3)", "Desserts (4)");
			String categoryStr = input.nextLine();
			// Check if the input is a valid number between 1 and 4.
			if (categoryStr.length() == 1 && categoryStr.charAt(0) >= '1' && categoryStr.charAt(0) <= '4') {
				categoryNum = Integer.parseInt(categoryStr);
				menuFormatting(menu, categoryNum);
				return categoryNum;
			} else {
				System.out.println("Invalid option! Please try again.");
			}
		}
	}

	public static void displayCategoryItems(String[][][] menu, int categoryNum) {
		int count = 1;
		for (int i = 0; i < menu[categoryNum - 1].length; i++, count++) {
			System.out.print("| " + menu[categoryNum - 1][i][0] + " $" + menu[categoryNum - 1][i][1] + " ");

			if (count % 3 == 0) {
				System.out.print("|\n");
			}
		}
	}

	public static void menuFormatting(String[][][] menu, int categoryNum) {
		switch (categoryNum) {
		case 1:
			System.out.println("\nDrinks\n--------------------------------------------------");
			displayCategoryItems(menu, categoryNum);
			System.out.println("--------------------------------------------------");
			break;
		case 2:
			System.out.println("\nStarters\n----------------------------------------------------------------");
			displayCategoryItems(menu, categoryNum);
			System.out.println("----------------------------------------------------------------");
			break;
		case 3:
			System.out.println("\nMain Courses\n----------------------------------------------------------");
			displayCategoryItems(menu, categoryNum);
			System.out.println("----------------------------------------------------------");
			break;
		case 4:
			System.out.println("\nDesserts\n-------------------------------------------------------------");
			displayCategoryItems(menu, categoryNum);
			System.out.println("-------------------------------------------------------------");
			break;
		default:
			System.out.println("No tab number selected.");
		}
	}

	public static String getTableNumber() {
		Scanner input = new Scanner(System.in);
		String tableStr;
		while (true) {
			System.out.print("Enter a table number (1-10).\n> ");
			tableStr = input.nextLine();
			// Check if the table number is between 1 and 10.
			if (tableStr.length() == 1 && tableStr.charAt(0) >= '1' && tableStr.charAt(0) <= '9'
					|| tableStr.equals("10")) {
				return tableStr;
			} else {
				System.out.println("Invalid input, please try again.");
			}
		}
	}

	public static String getNumOfPeople() {
		Scanner input = new Scanner(System.in);
		String numStr;
		while (true) {
			System.out.print("Enter number of people dining in (Max 6).\n> ");
			numStr = input.nextLine();
			// Check if the number of people dining is between 1 and 6.
			if (numStr.length() == 1 && numStr.charAt(0) >= '1' && numStr.charAt(0) <= '6') {
				return numStr;
			} else {
				System.out.println("Invalid input, please try again.");
			}
		}
	}
	
	public static String getPhoneNumber() {
		// Use a sentinel while loop to continuously prompt the user to input
		// a valid phone number.
		Scanner input = new Scanner(System.in);
		String phoneNumber = "";
		while (true) {
			System.out.print("Enter a 10-digit phone number (ex: 1234567890).\n> ");
			phoneNumber = input.nextLine();
			// Check if phoneNumber is 10 digits.
			if (phoneNumber.length() == 10) {
				break;
			} else {
				System.out.println("Invalid input, please try again.");
			}
		}
		return phoneNumber;
	}

	public static void displayOrderReceipts(String[][][] order) {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print("Enter in an order number (1-10, 0 to exit)\n> ");
			String orderStr = input.nextLine();
			// Check if order number is between 1 and 10.
			if (orderStr.length() == 1 && orderStr.charAt(0) >= '0' && orderStr.charAt(0) <= '9'
					|| orderStr.equals("10")) {
				int orderNum = Integer.parseInt(orderStr) - 1;
				if ((orderNum + 1) == 0) {
					break;
				} else if (order[orderNum][0][0] == null) {
					System.out.println("No order found.");
				} else if (orderNum < 10 && orderNum >= 0 && order[orderNum][0][0] != null) {
					double priceTotal = 0;
					for (int item = 0; item < 10; item++) {
						if (order[orderNum][item][1] != null) {
							priceTotal += Double.parseDouble(order[orderNum][item][1]);
						} else {
							break;
						}
					}

					// Display Receipt Items for Order Number
					displayReceiptItems(order, orderNum);

					// To display order info, recreate nameOrTable and phoneOrPeople variables again
					String nameOrTable = order[orderNum][10][0];
					String phoneOrPeople = order[orderNum][10][1];

					// Display Price and Info for Order Number
					displayPriceAndInfo(priceTotal, orderNum, nameOrTable, phoneOrPeople);

					break;
				}
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}
	}

	public static void displayReceipt(String[][][] order, int orderNum, double priceTotal, String nameOrTable,
			String phoneOrPeople) {
		// Display Receipt Items
		displayReceiptItems(order, orderNum);

		// Display Receipt Price and Info
		displayPriceAndInfo(priceTotal, orderNum, nameOrTable, phoneOrPeople);
	}

	public static void displayReceiptItems(String[][][] order, int orderNum) {
		System.out.printf("%9s %s %9s\n---------------------------\n", "", "Receipt", "");
		for (int item = 0; item < 10; item++) {
			if (order[orderNum][item][0] != null) {
				System.out.printf("%20s $%s\n", order[orderNum][item][0], order[orderNum][item][1]);
			} else {
				break;
			}
		}
	}
	
	public static void displayPriceAndInfo(double priceTotal, int orderNum, String nameOrTable, String phoneOrPeople) {
		final double TAX = priceTotal * 0.092;
		double priceWithTax = priceTotal + TAX;
		System.out.printf("---------------------------\nPrice: $%.2f%13s\nTax: $%.2f\nTotal: $%.2f", priceTotal,
				"Order #" + (orderNum + 1), TAX, priceWithTax);
		// Display to-go receipt if a phone number is detected or a dine-in
		// receipt if there is no phone number.
		if (phoneOrPeople.length() == 10) {
			System.out.printf("\n%s\n%s\n", "Name: " + nameOrTable, "Phone: " + phoneOrPeople);
		} else {
			System.out.printf("\n%s\n%s\n", "Table Number: " + nameOrTable, "Amount of People: " + phoneOrPeople);
		}
	}

}


