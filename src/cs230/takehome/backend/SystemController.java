package cs230.takehome.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs230.takehome.entities.User;

public class SystemController {
	
	// other classes should *not* instantiate this class.  It is "pure static".
	private SystemController() throws Exception {
		throw new Exception("Attempt to instantiate a SystemController");
	}
	
	/**
	 * Verify whether the username and password provided match a user in the
	 * database.  Return a Boolean indicating yes or no.
	 * 
	 * @param username the username to check
	 * @param password the password to check for matching the username
	 * @return the matching User object if the username and password match
	 * a database entry, or null otherwise
	 */
	public static User login(String username, String password) {
		User theUser = DatabaseController.getUser(username);
		
		if (theUser == null)
			return null;
		if (!theUser.getPassword().equals(password)) {
			return null;
		}
		else {
			return theUser;
		}
	}

	// this ADMIN ONLY method returns the list of all the users (and their data)
	// TODO: shouldn't this return a List of User objects?
	public static List<User> getAllUsers() {
		return DatabaseController.getAllUsers();
	}
	
	// this method attempts to add a user to the database with the
	// provided details
	public static boolean addUser(String username, String password,
			String displayName) {
		return DatabaseController.addUser(username, password, displayName);
	}
	
	// this method attempts to remove a user from the database
	// based on the provided username
	public static boolean removeUser(String username) {
		return DatabaseController.removeUser(username);
	}

	// this method attempts to update the user (as stored in the DB)
	// by removing the specified friend from their list
	public static boolean removeFriend(User theUser, String theFriend) {
		boolean result = theUser.removeFriend(theFriend);
		if (result)
			DatabaseController.updateUser(theUser);
		return result;
	}
	
	// this method attempts to update the user (as stored in the DB)
	// by removing the specified board game from their favorites list
	public static boolean removeGame(User theUser, String theGame) {
		boolean result = theUser.removeFavorite(theGame);
		if (result)
			DatabaseController.updateUser(theUser);
		return result;
	}

}
