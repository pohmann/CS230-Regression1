package cs230.takehome.frontend;

import java.util.List;
import java.util.Scanner;

import cs230.takehome.backend.SystemController;
import cs230.takehome.entities.User;

public class UserInteraction {

	private static User loggedInUser = null;

	// other classes should *not* instantiate this class.  It is "pure static".
	private UserInteraction() throws Exception {
		throw new Exception("Attempt to instantiate a UserInteraction");
	}

	// attempt to login, print message, and return success or failure
	public static boolean login(String username, String password) {
		User result = SystemController.login(username, password);
		if (result != null) {
			System.out.println("Login successful!");
			loggedInUser = result;
			return true;
		}
		else {
			System.out.println("Login failed!  Incorrect username or password.");
			loggedInUser = null;
			return false;
		}
	}

	// returns true if there is a user to log out, otherwise false
	public static boolean logout() {
		if (loggedInUser == null) {
			return false;
		}
		else {
			loggedInUser = null;
			return true;
		}
	}

	// get the formatted display name (no underscores) for the
	// current logged in user
	private static String formatDisplayName() {
		String formattedName = loggedInUser.getUnformattedDisplayName();
		int nameLength = formattedName.length();
		for (int i = 0; i < nameLength; i++) {
			if (formattedName.charAt(i) == '_')
				formattedName = formattedName.substring(0, i) + formattedName.substring(i+1);
		}
		return formattedName;
	}

	// print out the profile data for the current logged in user
	public static void viewProfile() {
		if (loggedInUser == null) {
			System.out.println("ERROR: No logged in user!");
			return;
		}

		System.out.println("Name: " + formatDisplayName());
		System.out.println("Username: " + loggedInUser.getUsername());
		System.out.println("Password: *****");
		System.out.println("Friends list: (not yet implemented)");
		System.out.println("Favorite games list: (not yet implemented)");
	}

	// get the list of all users in the system
	public static List<User> getAllUsers() {
		return SystemController.getAllUsers();
	}

	// ask for details and then attempt to add a user to the
	// database
	public static boolean addUser(Scanner s) {
		System.out.print("Username: ");
		String username = s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();
		System.out.print("Display name: ");
		String displayName = s.nextLine();

		return SystemController.addUser(username, password, displayName);
	}

	// ask for a username and then remove that user from the
	// database
	public static boolean removeUser(Scanner s) {
		System.out.print("Username: ");
		String username = s.nextLine();

		return SystemController.removeUser(username);
	}

	// ask for a friend username to remove, and then attempt to
	// remove that user from the current logged in user's friends
	// list
	public static boolean removeFriend(Scanner s) {
		System.out.print("Friend Username: ");
		String friendName = s.nextLine();

		if (loggedInUser == null)
			return false;
		else
			return SystemController.removeFriend(loggedInUser, friendName);
	}

	// ask for a game name to remove, and then attempt to
	// remove that game from the current logged in user's favorite
	// games list
	public static boolean removeGame(Scanner s) {
		System.out.print("Game name: ");
		String gameName = s.nextLine();

		if (loggedInUser == null)
			return false;
		else
			return SystemController.removeGame(loggedInUser, gameName);
	}

	/**
	 * Get the object for the current user logged in via
	 * this UserInteraction class.
	 * 
	 * @return the User object for the logged in user
	 */
	public static User getLoggedInUser() {
		return loggedInUser;
	}

}
