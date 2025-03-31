package cs230.takehome.frontend;

import java.util.List;
import java.util.Scanner;

import cs230.takehome.backend.SystemController;
import cs230.takehome.entities.User;

public class UserInteraction {

	private User loggedInUser = null;

	private SystemController theSystemController;

	// Construct a UserInteraction using the basic (no parameter)
	// SystemController as the single underlying controller object.
	// TODO: Someday, we should refactor the single SystemController class
	//       into multiple classes for better organization of functionalities.
	public UserInteraction() {
		this.theSystemController = new SystemController();
		this.loggedInUser = null;
	}

	// attempt to login, print message, and return success or failure
	public boolean login(String username, String password) {
		User result = this.theSystemController.login(username, password);
		if (result != null) {
			System.out.println("Login successful!");
			this.loggedInUser = result;
			return true;
		}
		else {
			System.out.println("Login failed!  Incorrect username or password.");
			this.loggedInUser = null;
			return false;
		}
	}

	// returns true if there is a user to log out, otherwise false
	public boolean logout() {
		if (this.loggedInUser == null) {
			return false;
		}
		else {
			this.loggedInUser = null;
			return true;
		}
	}

	/**
	 * Get the formatted display name (without underscores) for the
	 * current logged in user.  This method should give back exactly
	 * the User's display name, but with all underscores removed
	 * (i.e., replaced with blank).
	 * 
	 * @return the current user's display name, formatted properly
	 * for printing (with all underscores removed)
	 */
	public String formatDisplayName() {
		String formattedName = this.loggedInUser.getUnformattedDisplayName();
		int nameLength = formattedName.length();
		for (int i = 0; i < nameLength; i++) {
			if (formattedName.charAt(i) == '_')
				formattedName = formattedName.substring(0, i) + formattedName.substring(i+1);
		}
		return formattedName;
	}

	// print out the profile data for the current logged in user
	public void viewProfile() {
		if (this.loggedInUser == null) {
			System.out.println("ERROR: No logged in user!");
			return;
		}

		System.out.println("Name: " + formatDisplayName());
		System.out.println("Username: " + this.loggedInUser.getUsername());
		System.out.println("Password: *****");
		System.out.println("Friends list: (not yet implemented)");
		System.out.println("Favorite games list: (not yet implemented)");
	}

	// get the list of all users in the system
	public List<User> getAllUsers() {
		return this.theSystemController.getAllUsers();
	}

	// ask for details and then attempt to add a user to the
	// database
	public boolean addUser(Scanner s) {
		System.out.print("Username: ");
		String username = s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();
		System.out.print("Display name: ");
		String displayName = s.nextLine();

		return this.theSystemController.addUser(username, password, displayName);
	}

	// ask for a username and then remove that user from the
	// database
	public boolean removeUser(Scanner s) {
		System.out.print("Username: ");
		String username = s.nextLine();

		return this.theSystemController.removeUser(username);
	}

	// ask for a friend username to remove, and then attempt to
	// remove that user from the current logged in user's friends
	// list
	public boolean removeFriend(Scanner s) {
		System.out.print("Friend Username: ");
		String friendName = s.nextLine();

		if (this.loggedInUser == null)
			return false;
		else
			return this.theSystemController.removeFriend(this.loggedInUser, friendName);
	}

	// ask for a game name to remove, and then attempt to
	// remove that game from the current logged in user's favorite
	// games list
	public boolean removeGame(Scanner s) {
		System.out.print("Game name: ");
		String gameName = s.nextLine();

		if (this.loggedInUser == null)
			return false;
		else
			return this.theSystemController.removeGame(this.loggedInUser, gameName);
	}

	/**
	 * Get the object for the current user logged in via
	 * this UserInteraction class.
	 * 
	 * @return the User object for the logged in user
	 */
	public User getLoggedInUser() {
		return this.loggedInUser;
	}

}
