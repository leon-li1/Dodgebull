/*
 * Name: Leon Li
 * Date: June 9, 2017
 * Course code: ICS3U1-01
 * Title: Dodgebull game
 * Description: An arcade game where you try to survive as long as you can while dodging the balls coming at you (Player vs. AI)
 * Features: Splash screen, menu screen, instructions, game timer, arrays, random numbers, score, multiple lives, power ups/downs, coins,
 * calculating slope (AI), death timer and game over screen.
 * Areas of concern: If I had a bit more time, I could have made my game much more better by incorporating more and better images,
 * having a high score table, and sounds (improve aesthetics).
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadingScreen extends JWindow implements ActionListener {

	//Class fields
	public static GUI gui; //This sets the name of the gui frame
	
	//Instance fields
	private JPanel panel = new JPanel(); //creates a new panel
	private JProgressBar loadingBar = new JProgressBar(); //creates the progress bar
	private Timer progressBarTimer = new Timer(10, this); //timer
	private int count = 0; //counts the loading time
	private int max = 150; //time reached in order to exit progress bar

	//Constructor method
	public LoadingScreen() {

		//Calls the set up method
		objectsSetup();

		//Start the progress bar timer
		progressBarTimer.start();
	}
	
	//This method sets up the objects in the class
	private void objectsSetup() {

		//Panel setup
		panel.setLayout(new BorderLayout()); 
		panel.setBackground(Color.RED);
		JLabel splashImage = new JLabel(new ImageIcon("images/bullAnimation.gif"));
		splashImage.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
		panel.add(splashImage); 

		//Progress bar set up
		loadingBar.setMaximum(max);
		loadingBar.setForeground(new Color(255, 0, 0));
		loadingBar.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
		panel.add(loadingBar, BorderLayout.SOUTH);

		//Frame setup
		setContentPane(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	//Main method
	public static void main(String[] args) {

		//Runs the constructor method
		new LoadingScreen();
	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {
		
		//Checks if the source was the game start button
		if (event.getSource() == progressBarTimer) {

			//Set the value of the progress bar to count
			loadingBar.setValue(count);

			//Checks if the count is equal to the max
			if (count == max) {

				//Close the current window, stop the timers and open the main menu
				LoadingScreen.this.dispose();
				progressBarTimer.stop();
				new MainMenu();

			}

			//Increase count by 1
			count++;

		}

	}
}
/*public class LoadingScreen {
	
	public static GUI gui;
	public static void main(String[] args) {
		gui = new GUI();
		//new GameOver();
	}
}*/