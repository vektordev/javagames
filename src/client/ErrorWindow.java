package client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ErrorWindow extends JFrame {

	public ErrorWindow(String ename, String emsg){
		super("ERROR - " + ename);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(0,1));
		this.add(new JLabel("An error has occured. Errormessage:"));
		this.add(new JTextField(emsg));
		JButton exitBtn = new JButton("OK");
		exitBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
			
		});
		this.add(exitBtn);
		this.setVisible(true);
		
	}
	
	public void close(){
		this.setVisible(false);
	}

}
