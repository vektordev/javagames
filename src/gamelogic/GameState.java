package gamelogic;
import exceptions.NotPartOfGameException;
import gamelogic.entity.ComponentSystem;
import gamelogic.entity.Entity;
import gamelogic.entity.EntityFactory;
import gamelogic.physics.CollisionDispatcher;
import client.Settings;

/**
 * Contains all the game logic and associated data. No rendering.
 */
public class GameState {
	
	protected int numPlayers;
	protected boolean isRunning;
	protected int xSize;
	protected int ySize;
	protected int[] playerLength;
	public EntityFactory eFac;
	public ComponentSystem cSys;
	public CollisionDispatcher cDis;
	public Entity[] entities;
	
	/**
	 * default Constructor accepts as parameter the desired x/y resolution in pixels
	 * (20 = 1 tile) as well as Settings.
	 */
	public GameState(int xRes, int yRes, Settings settings) {
		;
		isRunning = true;
		cSys = new ComponentSystem();
		eFac = new EntityFactory(cSys);
		cDis = new CollisionDispatcher();
		xSize = xRes;
		ySize = yRes;
		playerLength = new int[2];
		entities = new Entity[(xRes * yRes / 200) + 9];
		// entities = new Entity[1209];
		entities[0] = eFac.createEntity(0, 0, 1);
		entities[0].updateVelocity();
		numPlayers = 1;
		if (settings.isMP()) {
			entities[1] = eFac.createEntity(xRes - 20, yRes - 20, 3);
			entities[1].updateVelocity();
			numPlayers++;
		} // end of if
		entities[2] = eFac.createWorldBorder(1);
		entities[2].setPos(xRes, yRes);
		entities[3] = eFac.createWorldBorder(2);
		entities[3].setPos(xRes, yRes);
		entities[4] = eFac.createWorldBorder(3);
		entities[5] = eFac.createWorldBorder(4);
		createBackground(xRes, yRes);
		spawnPickup(settings.getNoOfPickups());
		// spawnRock();
		// spawnRock();
		// spawnRock();
		// spawnRock();
	}

	private void createBackground(int x, int y) {
		for (int i = 0; i < (x); i += 20) {
			for (int j = 0; j < (y); j += 20) {
				// System.out.println("Pos: " + i + " " + j);
				entities[getFreeEntitiesSpace()] = eFac.createGrassTile(i, j);
			} // end of for
		} // end of for
	}

	/**
	 * Steps the simulation.
	 */
	public void run() {
		// detect collisions
		cDis.dispatchCollisions(cSys.getpComps());

		// grow all snakes, buffer next positions
		for (int i = 0; i < entities.length; i++) {

			if (entities[i] != null) {
				if (entities[i].StillElementsToGrow()){
					int newIndex = getFreeEntitiesSpace();
					entities[newIndex] = eFac.createSnakeTail(entities[i], isMultiplayer());
					playerLength[entities[i].getSnakeheadID()]++;
					entities[i].growUp();
					entities[newIndex].bufferNextPos();
				}
				entities[i].bufferNextPos();
			}
		}

		// step all entities
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null) {

				entities[i].run();
			}
		}

		// move all entities to buffered positions
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null) {
				entities[i].moveToBuffPos();
				if (entities[i].hasRenderableComponent()) {
					entities[i].render();
				} // end of if
			}

		}

		// clean up all entities that are flagged for destruction
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && entities[i].shallBeDestroyed()) {
				if (entities[i].physicalComponentIsPickUp()) {
					spawnPickup();
					spawnRock();// Muahahaha!
				} else {
					// assuming that this is a Snake
					isRunning = false;
				}
				deleteComponents(entities[i]);
				entities[i] = null;
			}
		} // end of for
		if ((entities[0] == null || entities[0].hasControllableComponent())
				&& (entities[1] == null || entities[1].hasControllableComponent())) {
			isRunning = false;
		} // end of if
		if (!isRunning) {
			try {
				//printScore();
				Thread.sleep(1500);
			} catch (Exception e) {
				System.out.println("Houston!!!");
			} finally {
			} // end of try
		} // end of if
	}

	private void printScore() {
		System.out.println();
		System.out.println();
		System.out.println();
		if (numPlayers > 1) {
			System.out.println("" + numPlayers + " players");
			boolean[] alive = new boolean[numPlayers];
			for (int i = 0; i < alive.length; i++) {
				alive[i] = false;
				if (entities[i] != null && entities[i].hasSnake()) {
					alive[i] = true;
				} // end of if
				System.out.println("Player " + (i + 1)
						+ " has reached a length of " + playerLength[i]
						+ " and has " + ((alive[i]) ? "" : "not ")
						+ "survived.");
			} // end of for
			if (alive[0]) {
				System.out.println("Player 1 won");
			} else {
				if (alive[1]) {
					System.out.println("Player 2 won");
				} else {
					if (playerLength[0] > playerLength[1]) {
						System.out.println("Player 1 won");
					} else {
						if (playerLength[1] > playerLength[0]) {
							System.out.println("Player 2 won");
						} else {
							System.out.println("draw");
						} // end of if-else
					}
				} // end of if-else
			} // end of if-else
		} else {
			System.out.println("The player has reached a length of "
					+ playerLength[0] + ".");
		} // end of if-else
		System.out.println();
		System.out.println();
		System.out.println();
	}

	private void spawnPickup(int n) {
		for (int i = 0; i < n; i++) {
			spawnPickup();
		} // end of for
	}

	private void spawnRock() {
		int xCoord = ((int) (Math.random() * ((int) (xSize / 20)))) * 20;
		int yCoord = ((int) (Math.random() * ((int) (ySize / 20)))) * 20;
		int index = getFreeEntityPosition();

		entities[index] = eFac.createBlock(xCoord, yCoord);

		moveToValidPosition(entities[index]);
	}

	private void spawnPickup() {
		int xCoord = ((int) (Math.random() * ((int) (xSize / 20)))) * 20;
		int yCoord = ((int) (Math.random() * ((int) (ySize / 20)))) * 20;
		int index = getFreeEntityPosition();
		
		entities[index] = eFac.createPickup(xCoord, yCoord);
		
		moveToValidPosition(entities[index]);
	}

	private void moveToValidPosition(Entity thing) {
		
		//TO FIX: endless loop. fix: ensure that the PhysicalComponent of thing is in cSys.getpComps
		while (cDis.checkCollisionsNoDispatch(thing.getPosition(), cSys.getpComps())) {
			System.out.println("new pos");
			thing.setPos(
					((int) (Math.random() * ((int) (xSize / 20)))) * 20, 
					((int) (Math.random() * ((int) (ySize / 20)))) * 20
			);
		}
		System.out.println("\n");
	}

	private int getFreeEntityPosition() {
		int index = -1;
		for (int i = 0; i < entities.length && (index == -1); i++) {
			if (entities[i] == null) {
				index = i;
			}
		}
		if (index == -1) {
			System.out.println("Houston, standby, we may have had a Problem");
		}
		return index;
	}

	private int getFreeEntitiesSpace() {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == null) {
				return i;
			}
		} // end of for
		System.out.println("Entities is full");
		return 0;
	}

	private void deleteComponents(Entity e) {
		cSys.removeComponent(e);
		/* OLD IMPLEMENTATION
		if (e.rComp != null) {
			cSys.getrComps()[e.rComp.index] = null;
		} // end of if
		if (e.pComp != null) {
			cSys.pComps[e.pComp.index] = null;
		} // end of if
		if (e.cComp != null) {
			cSys.cComps[e.cComp.index] = null;
		} // end of if*/
	}

	protected boolean isMultiplayer() {
		return (numPlayers > 1);
	}
	
	/*
	 * GETTER AND SETTER METHODS
	 */
	
	public boolean running(){
		return isRunning;
	}
	
	public int getLengthOfPlayer(int p) throws NotPartOfGameException{
		if(p > 2 || p < 0)
			throw new NotPartOfGameException("Invalid player identifier, player-id needs to be 0 or 1.");
		else{
			if(numPlayers == 1 && p == 1)
				throw new NotPartOfGameException("The game was a singleplayer game. So the player-id needs to be 0.");
			else
				return this.playerLength[p];
		}
	}
}
