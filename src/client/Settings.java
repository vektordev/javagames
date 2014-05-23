package client;

import java.util.Scanner;
import javax.swing.WindowConstants;

/**
 * Settings contains all the settings the user can make. These are queried by
 * console.
 */
public class Settings {

	protected Scanner sysin;
	protected int deltaTimeMillis;
	protected int noOfPickups;
	protected boolean multiplayer;

	/**
	 * Default Constructor Generates initial settings by console IO.
	 */
	public Settings() {
		sysin = new Scanner(System.in);
		System.out.println("Let's play a game!");
		System.out
				.println("Player 1 controls: WASD. Player 2 controls: Arrow keys.");
		getSettings();
	}

	/**
	 * alternative CTor generates Settings from hardcoded parameters.
	 */
	public Settings(boolean mp, int pu, int dt) {
		;
		multiplayer = mp;
		this.noOfPickups = pu;
		this.deltaTimeMillis = dt;
	}

	/**
	 * Creates the settings for the next round, using console IO.
	 */
	public void getNextSettings() {

		boolean parseable = false;
		String in;

		// Play new game or leave
		System.out.println("New game? (y/n)");

		while (!parseable) {
			in = sysin.nextLine();
			if (in.equals("y") || in.equals("Y")) {
				parseable = true;
			}
			if (in.equals("n") || in.equals("N")) {
				// parseable = true;
				System.exit(WindowConstants.EXIT_ON_CLOSE);
			}
		}

		// Choose whether to keep old settings or to change them
		System.out.println("Keep the settings? (y/n)");
		parseable = false;
		while (!parseable) {
			in = sysin.nextLine();
			if (in.equals("y") || in.equals("Y")) {
				parseable = true;
			}
			if (in.equals("n") || in.equals("N")) {
				parseable = true;
				getSettings();
			}
		}
	}

	private void getSettings() {

		boolean parseable = false;
		String input;

		// Multiplayer or singleplayer
		System.out.println("2 players? (y/n)");
		while (!parseable) {
			while (!sysin.hasNextLine())
				;
			input = sysin.nextLine();
			if (input.equals("y") || input.equals("Y")) {
				multiplayer = true;
				parseable = true;
			}
			if (input.equals("n") || input.equals("N")) {
				multiplayer = false;
				parseable = true;
			}
		}

		// choose gamespeed
		System.out.println("Game speed? (1-3)");
		int in = 0;
		while (in <= 0 || in > 3) {
			in = sysin.nextInt();
		} // end of while
		deltaTimeMillis = 200 / in;

		// choose the maximal number of collectibles
		System.out.println("Number of collectibles? (1-10)");
		in = 0;
		while (in <= 0 || in > 10) {
			in = sysin.nextInt();
		} // end of while
		noOfPickups = in;
	}

	public int getDeltaTimeMillis() {
		return deltaTimeMillis;
	}

	public int getNoOfPickups() {
		return noOfPickups;
	}

	public boolean isMP() {
		return multiplayer;
	}

}
