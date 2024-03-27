package cs230.takehome.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs230.takehome.entities.User;

/**
 * The DatabaseController class is the primary interaction class with the
 * database.  However, it currently "fakes" this interaction, and stores
 * all data in memory (meaning it is cleared if the application is ever
 * restarted).
 * 
 * @author Peter Ohmann
 */
public class DatabaseController {
	// the "database"!
	// NOTE: You can ignore this for your work.  Just pretend there
	//       is a real DB!
	private static List<User> theDB = new ArrayList<User>(
			Arrays.asList(
					new User("pohmann", "secure", "Peter"),
					new User("juser", "user", "John_User")
					)
			);
	
	// add a user to the db
	public static boolean addUser(User newUser) {
		for (User u : theDB) {
			if (newUser.getUsername().equals(u.getUsername()))
				return false;
		}
		theDB.add(newUser);
		return true;
	}
	
	// remove a user from the db
	public static boolean removeUser(String username) {
		for (User u : theDB) {
			if (username.equals(u.getUsername())) {
				return theDB.remove(u);
			}
		}
		return false;
	}
	
	// get a user; null if not in DB
	public static User getUser(String username) {
		for (User u : theDB) {
			if (username.equals(u.getUsername()))
				return u;
		}

		return null;
	}
	
	// get the list of all the users in the DB
	public static List<User> getAllUsers() {
		return new ArrayList<User>(theDB);
	}
	
	// update a user (including their friends and favorite board game
	// list) in the database
	public static boolean updateUser(User theUser) {
		boolean inDB = false;
		for (User u : theDB) {
			if (theUser.getUsername().equals(u.getUsername())) {
				inDB = true;
				theDB.remove(u);
				break;
			}
		}
		if (inDB)
			return theDB.add(theUser);
		else
			return false;
	}
	
}