package client;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class SetupWindow extends JFrame {

	//Statics
	private static int MAX_PICKUPS = 10;
	private static int MAX_SPEED = 3;
	
	//Visual Elements:
	private JRadioButton singleplayer;
	private JRadioButton multiplayer;
	private JComboBox veGameSpeed;
	private JComboBox vePickUps;
	private Settings mySettings;
	
	//Other
	private ClientApplication cApp;
	
	public SetupWindow(ClientApplication ca){
		super("Settings");
		
		cApp = ca;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(0,1));
		
		//Headline of the window
		JLabel headline = new JLabel("Settings");
		
		//Select number of player 1 or 2
		JPanel PlayerSelection = new JPanel(new GridLayout(1,0));
		ButtonGroup vePlayerNumber = new ButtonGroup();
		singleplayer = new JRadioButton("1 Player");
		singleplayer.setSelected(true);
		multiplayer = new JRadioButton("2 Players");
		multiplayer.setSelected(false);
		vePlayerNumber.add(singleplayer);
		vePlayerNumber.add(multiplayer);
		PlayerSelection.add(singleplayer);
		PlayerSelection.add(multiplayer);
		
		//Select gamespeed
		JPanel gsPanel = new JPanel(new GridLayout(1,0));
		String speeds[]  = new String[MAX_SPEED];
		for(int i = 0; i < speeds.length;i++)	//Create a string-array with ascending numbers(starting at 1)
			speeds[i] = new Integer(i + 1).toString();
		veGameSpeed = new JComboBox(speeds);
		gsPanel.add(new JLabel("Gamespeed:"));
		gsPanel.add(veGameSpeed);
		
		//Select number of available pickups
		JPanel puPanel = new JPanel(new GridLayout(1,0));
		String pickups[] = new String[MAX_PICKUPS];
		for(int i = 0; i < pickups.length;i++)	//Create a string-array with ascending numbers(starting at 1)
			pickups[i] = new Integer(i + 1).toString();
		vePickUps = new JComboBox(pickups);
		puPanel.add(new JLabel("Number of pickups:"));
		puPanel.add(vePickUps);
		
		
		//start button
		JButton startButton = new JButton("start game");
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}
			
		});
		
		//Add components to the window
		//this.add(headline);
		//this.add(new JSeparator(JSeparator.HORIZONTAL));
		this.add(PlayerSelection);
		this.add(gsPanel);
		this.add(puPanel);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		this.add(startButton);
		
		this.setBounds(0, 0, 240, 300);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}

	public SetupWindow(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SetupWindow(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SetupWindow(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public void defaultSettings(){
		//TO DO
	}
	
	public Settings getCurrentSettings(){
		mySettings = new Settings(
				multiplayer.isSelected(), 
				vePickUps.getSelectedIndex() + 1,
				200 / (veGameSpeed.getSelectedIndex() + 1)
		);
		return mySettings;
	}

	public void startGame(){
		getCurrentSettings();
		cApp.startGameWithSettings(mySettings);
		this.setVisible(false);
	}
}
