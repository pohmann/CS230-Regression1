package cs230.takehome.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * A user in our system.
 * 
 * @author Peter Ohmann
 */
public class User {
	private String username;
	private String password;
	private List<BoardGame> favorites;
	private List<User> friends;
	
	/**
	 * Create a user with an empty favorites and friends list.
	 * 
	 * @param username the unique username for this user
	 * @param password their password (for logging in)
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.favorites = new ArrayList<BoardGame>();
		this.friends = new ArrayList<User>();
	}

	/**
	 * Get the user's current password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Update the user's password.
	 * 
	 * @param password the new password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the user's unique username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Get the user's list of favorite board games.
	 * 
	 * @return the favorites list
	 */
	public List<BoardGame> getFavorites() {
		return favorites;
	}

	/**
	 * Get the user's list of followed friends (other users).
	 * 
	 * @return the friends list
	 */
	public List<User> getFriends() {
		return friends;
	}
	
	/**
	 * Add a new board game to this user's favorites list.
	 * 
	 * @param g the board game to add
	 * @return true if succesfully added, or false if the game already
	 * exists in the favorites list
	 */
	public boolean addFavorite(BoardGame g) {
		return this.favorites.add(g);
	}
	
	/**
	 * Remove a favorite game, by name.
	 * 
	 * @param gameName the name of the game to remove
	 * @return true if the game is successfully removed,
	 * or false otherwise (e.g., if the game isn't in the list)
	 */
	public boolean removeFavorite(String gameName) {
		for (BoardGame g : this.favorites) {
			if (g.getName().equals(gameName)) {
				this.favorites.remove(g);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Add a user to this user's friends list (by name).
	 * 
	 * @param friendName the name of the friend, as a string
	 * @return true if the friend was successfully added, or
	 * false otherwise (e.g., if the name doesn't exist in the DB)
	 */
	public boolean addFriend(String friendName) {
		// TODO
		return false;
	}
	
	/**
	 * Remove a friend from the list, by username.
	 * 
	 * @param friendUName the username of the friend to remove
	 * @return true if the friend is successfully removed,
	 * or false otherwise (e.g., if the friend isn't in the list)
	 */
	public boolean removeFriend(String friendUName) {
		for (User u : this.friends) {
			if (u.getUsername().equals(friendUName)) {
				this.friends.remove(u);
				return true;
			}
		}
		return false;
	}
}
