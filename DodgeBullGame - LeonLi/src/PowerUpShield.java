import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PowerUpShield extends JLabel implements ActionListener {

	//class fields
	public static boolean touchShield; //Condition to let me know if the bull has a shield or not
	
	//Instance fields
	private int x; //The x position
	private int y; //The y position
	private boolean showShield; //A condition to show the power up/down
	private Timer tmrShield = new Timer (50, this); //Timer to show/hide the power up/down
	private int timerTicks; //Counter for the timer

	//Constructor method
	public PowerUpShield() {

		//Set the image of the power up
		setIcon( new ImageIcon ( new ImageIcon ("Images/shield.png").getImage().getScaledInstance(18, 18, 0)));
	}

	//Getter for the power up's timer
	public Timer getTmrShield() {
		
		return tmrShield;
	
	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

		//Increment the counter by 1
		timerTicks++;

		//Check to see if 2 seconds has passed
		if (timerTicks % 40 == 0) {

			//Switch the value of a condition
			showShield = !showShield;

			//Check to see if the condition is true
			if (showShield) {
				
				//Reposition the power up/down at a random location on the screen and show the power up/down
				x = (int)(Math.random() * (670 - 5)) + 5;
				y = (int)(Math.random() * (645 - 60)) + 60;
				setLocation(x, y);
				setVisible(true);

			}
			//Otherwise if the condition is not true, hide the power up/down
			else
				setVisible(false);

		}
		
		//Checks if it's visible and if the bull is touching it
		if (isVisible() && getBounds().intersects(GUI.bull.getBounds())) {

			//Allow the bull to have a shield and hide the power up/down
			touchShield = true;
			GUI.bull.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.CYAN));
			setVisible(false);
		}
	}
}