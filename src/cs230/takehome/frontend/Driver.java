package cs230.takehome.frontend;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Driver {

	// the static UserInteraction object to use for all user actions
	private static UserInteraction ui = new UserInteraction();

	/**
	 * Get the selected menu option based on user entry.
	 * This reads one line from the provided Scanner.
	 * 
	 * @param s the Scanner from which to read the user's input
	 * @param minChoice the minimum allowed option (inclusive)
	 * @param maxChoice the maximum allowed option (inclusive)
	 * @return the selected integer, or -1 if invalid input is entered
	 */
	private static int getSingleMenuEntry(Scanner s, int minChoice, int maxChoice) {
		String choice = s.nextLine();
		try {
			int numChoice = Integer.parseInt(choice);
			if (numChoice < minChoice || numChoice > maxChoice)
				throw new NumberFormatException("Invalid selection");
			return numChoice;
		}
		catch (Exception e) {
			// here if either a non-integer is entered or it is outside
			// the legal range of values (per min/maxChoice)
			return -1;
		}
	}
	
	/**
	 * Get the selected menu option based on user entry.
	 * This reads lines from the provided Scanner until the user enters
	 * a menu option (number) that matches one of the options provided
	 * (i.e., is between 1 and the number of options).
	 * 
	 * @param s the Scanner from which to read the user's input
	 * @param options the menu options the user has
	 * @return the selected menu option, as an integer (1 more than the
	 * position in the options array)
	 */
	private static int getMenuOption(Scanner s, List<String> options) {		
		int choice = -1;
		while (choice == -1) {
			System.out.println("Choose an option:");
			for (int i = 0; i < options.size(); i++) {
				System.out.println((i+1) + ": " + options.get(i));
			}
			choice = getSingleMenuEntry(s, 1, options.size());
			if (choice == -1)
				System.out.println("Invalid option.");
		}
		
		return choice;
	}
	
	// print the header for the current menu
	private static void printHeader(String title) {
		String dashes = "";
		for (int i = 0; i < title.length(); i++)
			dashes += "-";

		System.out.println(dashes);
		System.out.println(title);
		System.out.println(dashes);
	}
	
	private static void userMenu(Scanner s) {
		printHeader("User Menu");
		
		int choice = getMenuOption(s, Arrays.asList("View Profile", "Remove Friend", "Remove Favorite Game", "Logout"));
		
		switch(choice) {
		case 1:
			ui.viewProfile();
			break;
		case 2:
			if (!ui.removeFriend(s))
				System.out.println("Failed to remove friend.  (Invalid username?)");
			break;
		case 3:
			if (!ui.removeGame(s))
				System.out.println("Failed to remove game.  (Invalid game name?)");
			break;
		case 4:
			ui.logout();
			break;
		default:
			System.err.println("Internal error: Unsupported option.");
			System.exit(1);
		}
	}

	private static void topMenu(Scanner s) {
		printHeader("Welcome to Board Game Manager!");
		System.out.println("Please log in.");

		String username = "";
		while (username.isEmpty()) {
			System.out.print("Username: ");
			username = s.nextLine();
		}

		System.out.print("Password: ");
		String password = s.nextLine();

		boolean success = ui.login(username, password);
		if (success)
			System.out.println("Redirecting to main menu.");
	}

	// main just forever prints the relevant menu
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		while (true) {
			if (ui.getLoggedInUser() == null)
				topMenu(s);
			else
				userMenu(s);
		}
	}

}
