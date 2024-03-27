package cs230.takehome.entities;

/**
 * A board game (with publication data) stored in the system.
 * Board games are immutable in our system (so their data cannot
 * be changed once created), because the data stored is all
 * original (not current) publication data.
 * 
 * @author Peter Ohmann
 */
public class BoardGame {
	private String name;
	private String publisher;
	private int publicationYear;
	
	/**
	 * Create a board game based on full information.
	 * 
	 * @param name the name of the game
	 * @param publisher the game's publisher
	 * @param publicationYear the year in which the game was first published
	 */
	public BoardGame(String name, String publisher, int publicationYear) {
		super();
		this.name = name;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
	}
	
	/**
	 * Get the unique name of the board game.
	 * 
	 * @return the name of the game
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the original publisher name for this game.
	 * 
	 * @return the publisher name
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Get the original publication year for this game.
	 * 
	 * @return the original year of publication
	 */
	public int getPublicationYear() {
		return publicationYear;
	}
}
