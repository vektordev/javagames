package client;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import exceptions.NotPartOfGameException;

public class EndWindow extends JFrame {

	private ClientApplication cApp;
	
	public EndWindow(ClientApplication ca){
		super("Game Over!");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cApp = ca;
		
		this.setLayout(new GridLayout(0,1));
		int noPlayers = 2;
		int pLength[] = new int[noPlayers];
		String goMsg = new String();
		
		for(int i = 0; i < noPlayers; i++){
			try{
				pLength[i] = cApp.state.getLengthOfPlayer(i);
			} catch(NotPartOfGameException e){
				noPlayers = i;
			}
		}
		
		switch(noPlayers){
			case 0:
				goMsg = "ERROR! no players found!";
			break;
			
			case 1:
				goMsg = "The snake of the first player is " + pLength[0] + " blocks long.";
			break;
			
			case 2:
				goMsg = "The snake of the first player is " + pLength[0] + " blocks long and " +
						"the snake of the second player is " + pLength[1] + " blocks long.";
				if(pLength[0] == pLength[1])
					goMsg += " Both snakes have the same length.";
				else{
					if(pLength[0] > pLength[1])
						goMsg += " The first player wins.";
					else
						goMsg += " The second player wins.";
				}
			break;
			
			default:
				//random shit message
				goMsg = "WHAT DID YOU DO? NO! STOP IT! YOU WILL DESTROY EVERYTHING!";
				break;
		}
		
		JTextArea infobox = new JTextArea(goMsg);
		infobox.setWrapStyleWord(true);
		infobox.setLineWrap(true);
		infobox.setEditable(false);
		this.add(infobox);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		//buttons
		JButton replayButton = new JButton("replay");
		JButton setupButton = new JButton("change Settings & replay");
		JButton closeButton = new JButton("close");
		this.add(replayButton);
		this.add(setupButton);
		this.add(closeButton);
		
		//Setup Button-Listeners
		replayButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cApp.startGameWithSettings(cApp.settings);
			}
			
		});
		setupButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new SetupWindow(cApp);
				setVisible(false);
			}
			
		});
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(WindowConstants.EXIT_ON_CLOSE);
			}
			
		});
		
		this.setBounds(0, 0, 240, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public EndWindow(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public EndWindow(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public EndWindow(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

}
