import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SmartBall extends JLabel implements ActionListener {

	//Instance fields
	private int dx; //The ball's displacement in x
	private int dy; //The ball's displacement in y
	private int throwPosition; //The launch position
	private Timer smartBallTimer = new Timer(10, this); //The smart ball's move timer
	private int startX; //The start position (x)
	private int startY; //The start position (y)

	public SmartBall() {

		//Set the image of the smart ball
		setIcon( new ImageIcon ("Images/redBall.png"));

		//Call the reset smart ball method
		resetSmartBall();

		//Start the move timer
		smartBallTimer.start();

	}

	//Getter for the start position (x)
	public int getStartX() {

		return startX;

	}

	//Getter for the start position (y)
	public int getStartY() {

		return startY;

	}

	//This method calculates the distance and direction between the bull and itself
	private void calculateSlope() {

		//Set dx and dy to the distance between in x or y divided by 100 (to scale)
		dx = (GUI.bull.getX() - getX()) / 100;
		dy = (GUI.bull.getY() - getY()) / 100;

		//If dx and dy = 0, change it's launch position and recalculate dx and dy
		if (dx == 0 && dy == 0) {
			resetThrowPosition();
			calculateSlope();
		}
	}

	//This method randomly selects a launch position for the smart ball
	private void resetThrowPosition() {

		//Generate a number between 0 and 3
		throwPosition = (int)(Math.random() * 4);

		//Depending on the number generated, set the corresponding start position
		if (throwPosition == 0) {
			startX = 20;
			startY = 100;
		} 
		else if (throwPosition == 1) {
			startX = 550;
			startY = 100;
		}
		else if (throwPosition == 2) {
			startX = 20;
			startY = 550;
		}
		else {
			startX = 550;
			startY = 550;
		}

		//Move the smart ball to its start position
		setLocation(startX, startY);	
	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

		//Checks if the bull has started to move
		if (GUI.firstMove == false) {

			//move the smart ball to the new coordinates
			setLocation(getX() + dx, getY() + dy);

			//If touch the bottom wall, reset the smart ball
			if (getY() + getHeight() > GUI.FRAME_HEIGHT - 28)
				resetSmartBall();
			//If touch the right wall, reset the smart ball
			else if (getX() + getWidth() > GUI.FRAME_WIDTH - 6) 
				resetSmartBall();
			//If touch the top wall, reset the smart ball
			else if (getY() < 5) 
				resetSmartBall();
			//If touch the left wall, reset the smart ball
			else if (getX() < 5) 
				resetSmartBall();

			//Checks if the ball is visible and touches the bull
			if (getBounds().intersects(GUI.bull.getBounds())) {

				//Checks if the bull has a shield
				if (PowerUpShield.touchShield) {
					
					//Remove the shield from the bull
					PowerUpShield.touchShield = false;
					GUI.bull.setBorder(null);
					
					//Reset the smart ball to its start position
					setLocation(startX, startY);
				}
				//If the bull has no shield, start the bull's death timer
				else 
					GUI.bull.getDeathTimer().start();
			}
		}
	}

	//This method resets the smart ball
	private void resetSmartBall() {

		//Pick a random launch position
		resetThrowPosition();
		
		//Calculate its dx and dy
		calculateSlope();

	}

}
