package client;
import gamelogic.GameState;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import client.rendering.Renderer;


import gamelogic.GameState;

/**
 * Creates an application from a Renderer, a GameState and a Timer.
 */
public class ClientApplication implements ActionListener {
	
	protected GameState state;
	protected Renderer rnd;
	protected Timer timer;
	protected InputSystem in;
	protected Settings settings;
	
	/**
	 * Default-Constructor
	 */
	public ClientApplication() {
		//Functionality moved to function startGameWithSettings(Settings s)
	}

	private void setUp() {
		rnd.getFrame().dispose();
		rnd = null;
		settings.getNextSettings();
		state = new GameState(600, 400, settings);
		rnd = new Renderer(state, 600, 400);
		timer = new Timer(settings.getDeltaTimeMillis(), this);
		in = new InputSystem();
		rnd.getRenderWindow().addKeyListener(in);
		rnd.setGameState(state);
		rnd.getRenderWindow().setComponentSystem(state.cSys);
		in.controlled1 = state.entities[0].getAssocControllableComponent();
		if (settings.isMP())
			in.controlled2 = state.entities[1].getAssocControllableComponent();
		run();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("Timer!");
		if (state.running()) {
			in.applyInput();
			state.run();
			rnd.render();
		} else {
			timer.stop();
			new EndWindow(this);
			//setUp();
		} // end of if-else
	}
	
	public void startGameWithSettings(Settings s){
		int xRes = 800;
		int yRes = 640;
		settings = s;
		state = new GameState(xRes, yRes, settings);
		rnd = new Renderer(state, xRes, yRes);
		timer = new Timer(settings.getDeltaTimeMillis(), this);
		in = new InputSystem();
		rnd.getRenderWindow().addKeyListener(in);
		in.controlled1 = state.entities[0].getAssocControllableComponent();
		if (settings.isMP())
			in.controlled2 = state.entities[1].getAssocControllableComponent();
		run();
	}

	/**
	 * starts the application
	 */
	public void run() {
		timer.start();
	}

	public static void main(String[] args) {
		ClientApplication cApp = new ClientApplication();
		SetupWindow sWnd = new SetupWindow(cApp);
		//cApp.run();

	}
}
