import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame implements ActionListener {

	//Instance fields
	private JLabel lblAnimation = new JLabel(new ImageIcon()); //Label to display the game animation
	private JLabel lblTitle = new JLabel ("Dodge-Bull!"); //label to show the title
	private JButton gameStart = new JButton("Start Game");  //Button to allow the game to start
	private JButton instructions = new JButton("Instructions"); //Button to show the game instructions
	private Font font = new Font ("Calibri", Font.BOLD, 36); //Font for the buttons
	private Font titleFont = new Font ("Calibri", Font.BOLD, 48); //Font for the buttons
	private Clip clip; //Variable to enable sound (temporarily stores the sound file)

	public MainMenu() {

		/*//Play the introduction music
		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("sounds/music.wav")));
			clip.start();

		} catch (Exception e){

			System.out.println("Error");

		}*/

		//Call the setup methods
		frameSetup();
		buttonSetup();
	}

	//This method sets up the frame
	private void frameSetup() {

		//Frame setup
		setSize(600,600);
		setLayout(null);
		setTitle("Dodgebull Introduction Screen");
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color (255, 0, 0));

		//Add the title
		lblTitle.setBounds(180, 50, 300, 50);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.WHITE);
		add(lblTitle);
		
		//Add the animation
		lblAnimation.setBounds(35, 75, 500, 360);
		lblAnimation.setIcon(new ImageIcon("Images/minotaur.jpg"));
		add(lblAnimation);
	}

	//This method sets up the 2 buttons on the frame
	private void buttonSetup() {

		//Create the game start button
		gameStart.setBounds(100, 410, 400, 80);
		gameStart.setFont(font);
		gameStart.setForeground(Color.white);
		gameStart.setOpaque(false);
		gameStart.setContentAreaFilled(false);
		gameStart.setBorderPainted(false);
		gameStart.setFocusPainted(false);
		gameStart.addActionListener(this);
		add(gameStart);

		//Create the instructions button
		instructions.setBounds(100, 460, 400, 80);
		instructions.setFont(font);
		instructions.setForeground(Color.white);
		instructions.setOpaque(false);
		instructions.setContentAreaFilled(false);
		instructions.setBorderPainted(false);
		instructions.setFocusPainted(false);
		instructions.addActionListener(this);
		add(instructions);
	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

		//Checks if the source was the game start button
		if (event.getSource() == gameStart) {

			//Close the current frame and call up the GUI frame
			dispose();
			LoadingScreen.gui = new GUI();
		}

		//Checks if the source was the instructions button
		else if (event.getSource() == instructions) {

			//Close the current frame and call up the instructions frame
			dispose();
			new Instructions();
		}
	}
}
