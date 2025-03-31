package cs230.takehome.backend;

import java.util.List;

import cs230.takehome.entities.User;

/**
 * The SystemController class handles all of the processing between the
 * front-end and the database.
 * 
 * @author Peter Ohmann
 */
public class SystemController {
	private DatabaseController myDBController;

	// Construct a SystemController using the basic (no parameter)
	// DatabaseController as the underlying database access.
	public SystemController() {
		this.myDBController = new DatabaseController();
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
	public User login(String username, String password) {
		User theUser = this.myDBController.getUser(username);
		
		if (theUser == null)
			return null;
		if (!theUser.getPassword().equals(password)) {
			return null;
		}
		else {
			return theUser;
		}
	}

	// this method returns the list of all the users (and their saved data)
	public List<User> getAllUsers() {
		return this.myDBController.getAllUsers();
	}
	
	// this method attempts to add a user to the database with the
	// provided details
	public boolean addUser(String username, String password,
			String displayName) {
		User theUser = new User(username, password, displayName);
		return this.myDBController.addUser(theUser);
	}
	
	// this method attempts to remove a user from the database
	// based on the provided username
	public boolean removeUser(String username) {
		return this.myDBController.removeUser(username);
	}

	// this method attempts to update the user (as stored in the DB)
	// by removing the specified friend from their list
	public boolean removeFriend(User theUser, String theFriend) {
		boolean result = theUser.removeFriend(theFriend);
		if (result)
			this.myDBController.updateUser(theUser);
		return result;
	}
	
	// this method attempts to update the user (as stored in the DB)
	// by removing the specified board game from their favorites list
	public boolean removeGame(User theUser, String theGame) {
		boolean result = theUser.removeFavorite(theGame);
		if (result)
			this.myDBController.updateUser(theUser);
		return result;
	}

}
