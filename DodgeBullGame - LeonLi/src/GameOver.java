import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameOver extends JFrame implements ActionListener {

	//Instance fields
	private JLabel lblCongrats = new JLabel ("Thanks for playing!"); //Label for the title
	private JButton exitGame = new JButton("Exit Game"); //Button to exit game 
	private JTextArea scoreInfo = new JTextArea (); //Text are to display the final score of the game
	private JLabel lblScore = new JLabel(); //Label to show the final score (part 1)
	private JLabel lblAnimation = new JLabel(); //Label to display the game animation
	private Font titleFont = new Font ("Calibri", Font.BOLD, 48); //Font for the title
	private Font textFont = new Font ("Calibri", Font.PLAIN, 26); //Font for the text area and the labels
	private Font buttonFont = new Font ("Calibri", Font.BOLD, 32); //Font for the button
	private Clip clip; //Variable to enable sound (temporarily stores the sound file)

	//Constructor method
	public GameOver() {

		//Call methods
		frameSetup();
		printStats();
		buttonSetup();
		repaint();

		/*//Play the Dodgebull theme song
		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("sounds/dodgebullSong.wav")));
			clip.start();

		} catch (Exception e) {
			
			System.out.println("Error");

		}*/
	}

	//This method sets up the frame
	private void frameSetup() {

		//Remove GUI frame
		LoadingScreen.gui.dispose();

		//Frame setup
		setSize(600, 600);
		setResizable(false);
		setLayout(null);
		setTitle("Dodgebull Exit Screen");
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.RED);

		//Title setup
		lblCongrats.setBounds(100, 15, 400, 60);
		lblCongrats.setFont(titleFont);
		lblCongrats.setForeground(Color.white);
		add(lblCongrats);

		//logo setup
		lblAnimation.setBounds(120, 60, 360, 300);
		lblAnimation.setIcon(new ImageIcon ("Images/minotaur.jpg"));
		add(lblAnimation);

	}

	//This method prints the final stats of the game
	private void printStats() {

		//Text area setup
		scoreInfo.setBounds(200, 355, 245, 70);
		scoreInfo.setFont(textFont);
		scoreInfo.setForeground(Color.white);
		scoreInfo.setBackground(Color.RED);
		scoreInfo.setLineWrap(true);
		scoreInfo.setWrapStyleWord(true);
		scoreInfo.setEditable(false);
		scoreInfo.setText("Time survived:\t" + GUI.cntTime + "\nCoins collected: " + GUI.cntCoins);
		add(scoreInfo);

		//Score label setup
		lblScore.setBounds(90, 440, 520, 50);
		lblScore.setFont(titleFont);
		lblScore.setForeground(Color.white);
		lblScore.setText("Your final score is: " + GUI.score);
		add(lblScore);
	}

	//This method sets up the button
	private void buttonSetup() {

		exitGame.setBounds(75, 500, 450, 50);
		exitGame.setFont(buttonFont);
		exitGame.setForeground(Color.white);
		exitGame.setOpaque(false);
		exitGame.setContentAreaFilled(false);
		exitGame.setBorderPainted(false);
		exitGame.setFocusPainted(false);
		exitGame.addActionListener(this);
		add(exitGame);

	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

			//Exit the program
			System.exit(0);

	}

}
