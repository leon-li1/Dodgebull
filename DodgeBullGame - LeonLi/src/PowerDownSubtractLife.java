import java.awt.event.*;
import javax.swing.*;

public class PowerDownSubtractLife extends JLabel implements ActionListener {

	//Instance fields
	private int x; //The x position
	private int y; //The y position
	private boolean showSubtractLife; //A condition to show the power up/down
	private int timerTicks; //Counter for the timer
	private Timer tmrSubtractLife = new Timer (50, this); //Timer to show/hide the power up/down
	
	//Constructor method
	public PowerDownSubtractLife() {
		
		//Set the image of the power up/down
		setIcon(new ImageIcon("Images/skull.png"));
		
	}
	
	//Getter for the power up/down timer
	public Timer getTmrSubtractLife() {
	
		return tmrSubtractLife;
	
	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

		//Increment the counter by 1
		timerTicks++;

		//Check to see if 4 seconds has passed
		if (timerTicks % 80 == 0) {

			//Switch the value of a condition
			showSubtractLife = !showSubtractLife;

			//Check to see if the condition is true
			if (showSubtractLife) {

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
			
			//Call the lose life method and hide the power up/down
			GUI.bull.loseLife();
			setVisible(false);
		}
	}
}