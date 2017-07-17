import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Instructions extends JFrame implements ActionListener  {

	
	//Instance fields
	private JLabel lblTitle = new JLabel ("Instructions"); //Label for the title
	private JButton gameStart = new JButton("Start Game"); //Button to allow the game to start
	private JTextArea instructions = new JTextArea (); //Text area to display the instructions
	private Font titleFont = new Font ("Calibri", Font.BOLD, 46); //Font for the title
	private Font textFont = new Font ("Calibri", Font.PLAIN, 26); //Font for the instructions
	private Font buttonFont = new Font ("Calibri", Font.BOLD, 32); //Font for the buttons

	//Constructor method
	public Instructions() {

		//Call methods
		frameSetup();
		printInstructions();
		buttonSetup();
	}

	//This method sets up the frame
	private void frameSetup() {
		
		//Frame setup
		setSize(600,600);
		setLayout(null);
		setTitle("Dodgebull Intructions Screen");
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color (255, 0, 0));

		//Title setup
		lblTitle.setBounds(185, 30, 400, 80);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.white);
		add(lblTitle);

	}

	//This method sets up the instructions label
	private void printInstructions() {

		instructions.setBounds(50, 95, 500, 360);
		instructions.setFont(textFont);
		instructions.setForeground(Color.white);
		instructions.setBackground(new Color (255, 0, 0));
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setEditable(false);
		instructions.setText("1. Use the Arrow Keys to move the bull.\n"
				+ "2. Try to survive as long as you can while avoiding obstacles (i.e. the balls).\n"
				+ "3. Your score increases by 10 as you start moving and collecting a coin will give you bonus score of 50 points.\n" 
				+ "4. You have three lives and there are three power up/downs: a shield, a heart and a skull.\n" 
				+ "5. A shield will block you from a heart and the heart and skull adds/subtracts a life respectively.\n");
		add(instructions);
	}

	//This method sets up the button
	private void buttonSetup() {

		gameStart.setBounds(95, 450, 400, 80);
		gameStart.setFont(buttonFont);
		gameStart.setForeground(Color.white);
		gameStart.setOpaque(false);
		gameStart.setContentAreaFilled(false);
		gameStart.setBorderPainted(false);
		gameStart.setFocusPainted(false);
		gameStart.addActionListener(this);
		add(gameStart);

	}
	
	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

			//Close the current frame and open the GUI frame
			dispose();
			LoadingScreen.gui = new GUI();
	}

}
