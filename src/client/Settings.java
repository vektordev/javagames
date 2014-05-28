package client;

import exceptions.WrongSettingsException;

/**
 * Settings contains all the settings the user can make. These are queried by
 * console.
 */
public class Settings {
	protected int deltaTimeMillis;
	protected int noOfPickups;
	protected boolean multiplayer;

	/**
	 * @param mp(boolean): contains whether the game is a multiplayer game or not
	 * @param pu(int): contains the maximum number of pickups on the field (needs to be bigger then 0)
	 * @param dt(int): contains the deltaTime (must be bigger then 0)
	 * @throws WrongSettingsException: if deltaTime or number of pickups is less then 0.
	 */
	public Settings(boolean mp, int pu, int dt) throws WrongSettingsException {
		if(pu <= 0 || dt <= 0)
			throw new WrongSettingsException("Number of Pickups: " + pu + ", delta Time: " + dt + ", is no valid setting!");
		multiplayer = mp;
		this.noOfPickups = pu;
		this.deltaTimeMillis = dt;
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
