import java.awt.event.*;
import javax.swing.*;

public class PowerUpAddLife extends JLabel implements ActionListener {
	
	//Instance fields
	private int x; //The x position
	private int y; //The y position
	private boolean showAddLife; //A condition to show the power up/down
	private int timerTicks; //Counter for the timer
	private Timer tmrAddLife = new Timer (50, this); //Timer to show/hide the power up/down
	
	//Constructor method
	public PowerUpAddLife() {
	
		//Set the image of the power up
		setIcon(new ImageIcon (new ImageIcon ("Images/redHeart.png").getImage().getScaledInstance(18, 18, 0)));
		
	}

	//Getter for the power up's timer
	public Timer getTmrAddLife() {
		
		return tmrAddLife;
	
	}
	
	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

		//Increment the counter by 1
		timerTicks++;

		//Check to see if 3 seconds has passed
		if (timerTicks % 60 == 0) {

			//Switch the value of a condition
			showAddLife = !showAddLife;

			//Check to see if the condition is true
			if (showAddLife) {

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

			//Call the add life method and hide the power up/down
			GUI.bull.addLife();
			setVisible(false);
		}
	}
	
}
