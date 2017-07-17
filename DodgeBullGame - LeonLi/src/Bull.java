
import java.awt.event.*;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Bull extends JLabel implements ActionListener {

	//Constants for bull's move amount and start position
	private static final int START_X = 310;
	private static final int START_Y = 310;

	//Instance fields
	private int cntLostLife = -1; //Counts the number of times you lose a life
	private int lives = 3; //Amount of lives bull has left
	private Timer deathTimer = new Timer (250, this); //The timer that creates a death animation
	private int deathState; //Is the number of the current state in the death animation
	private Clip clip; //Variable to enable sound (temporarily stores the sound file)

	//Constructor method
	public Bull() {

		//Sets the starting location and image for the bull
		setLocation(START_X,START_Y);
		setIcon(new ImageIcon(new ImageIcon ("Images/bull.png").getImage().getScaledInstance(60, 60, 0)));
	}

	//Getter for the bull's lives
	public int getLives() {
		return lives;
	}

	//Setter for the bull's lives
	public void setLives(int lives) {
		this.lives = lives;
	}

	//Getter for the bull's death timer
	public Timer getDeathTimer() {

		return deathTimer;

	}

	//This method adds a life to the bull
	public void addLife() {

		//Check to see if the bull's lives isn't 3 (the max lives)
		if (GUI.bull.getLives() != 3) {

			//Sets one of the bull live labels back to the bull image
			GUI.lives[cntLostLife].setIcon(new ImageIcon (new ImageIcon ("Images/bull.png").getImage().getScaledInstance(30, 25, 0)));

			//Add 1 life to the bull's lives or life
			GUI.bull.setLives(GUI.bull.getLives() + 1);

			//Decrease the lose life counter by 1
			cntLostLife--;
		}
	}

	//This method allows the bull to lose a life
	public void loseLife() {

		//Call the reset method
		reset();

		//Checks if the bull has a shield
		if (PowerUpShield.touchShield) {

			//Remove the shield from the bull
			setBorder(null);
			PowerUpShield.touchShield = false;

		}
		
		//Check to see if cntLostlife isn't 2 (the max index)
		if (cntLostLife != 2)

			//Increase the lose life counter by 1
			cntLostLife++;

		//Sets one of the bull live labels to the bull dead image
		GUI.lives[cntLostLife].setIcon(new ImageIcon (new ImageIcon ("Images/bullDead.png").getImage().getScaledInstance(30, 25, 0)));

		//Subtract 1 life from the bull's lives or life
		GUI.bull.setLives(GUI.bull.getLives() - 1);

		//Checks if the bull's lives is 0 
		if (GUI.bull.getLives() == 0) 

			//Call the game over method
			new GameOver();

	}

	//This method resets the position of the bull
	public void reset() {

		//Sets the bull to its starting coordinates
		setLocation(START_X, START_Y);

		//Reset the image of the bull
		setIcon(new ImageIcon(new ImageIcon ("Images/bull.png").getImage().getScaledInstance(60, 60, 0)));

	}

	//This method  implements the action listener
	public void actionPerformed(ActionEvent event) {

		//When the death timer runs
		//Increment the death state by 1
		deathState++;

		//When the death state is 1, change the image of the bull to a dead image
		if (deathState == 1) {
			setIcon( new ImageIcon ( new ImageIcon ("Images/dead.gif").getImage().getScaledInstance(60, 60, 0)));
		}
		//When the death state is 4, call the lose life and reset GUI methods, stop the timer and reset the death state to 0
		else if (deathState == 4) {
			loseLife();
			GUI.resetGUI();
			deathTimer.stop();
			deathState = 0;
		}
	}


}
