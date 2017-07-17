import java.awt.event.*;
import javax.swing.*;

public class Ball extends JLabel implements ActionListener {

	//Instance fields
	private int startX; //The start position (x)
	private int startY; //The start position (y)
	private int dx = 5; //The ball's displacement in x
	private int dy = 5; //The ball's displacement in y
	private Timer ballTimer; //The move timer for the ball

	//Constructor method
	public Ball (int ballSpeed, int throwPosition) {
		
		//Set Image Icon of the ball
		setIcon( new ImageIcon (new ImageIcon ("Images/redBall.png").getImage().getScaledInstance(30, 30, 0)));
		
		//Set the start position of the ball
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

		//Create the ball's move timer and start it
		ballTimer = new Timer(ballSpeed, this);
		ballTimer.start();
		
	}

	//Getter for the start position (x)
	public int getStartX() {

		return startX;

	}

	//Getter for the start position (y)
	public int getStartY() {

		return startY;

	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

		//Checks if the bull has started moving
		if (GUI.firstMove == false) {
			
			//If touch the bottom wall, multiply dy by -1
			if (getY() + getHeight() > GUI.FRAME_HEIGHT - 28)
				dy = -dy;

			//If touch the right wall, multiply dx by -1
			if (getX() + getWidth() > GUI.FRAME_WIDTH - 6)
				dx = -dx;

			//If touch the top wall, multiply dy by -1
			if (getY() < 5)
				dy = -dy;

			//If touch the left wall, multiply dx by -1
			if (getX() < 5)
				dx = -dx;

			//move the ball to the new coordinates
			setBounds(getX() + dx, getY() + dy, 30, 30);

			//Checks if the ball is visible and touches the bull
			if (isVisible() && getBounds().intersects(GUI.bull.getBounds())) {
				
				//Checks if the bull has a shield
				if (PowerUpShield.touchShield) {
					
					//Remove the shield from the bull
					PowerUpShield.touchShield = false;
					GUI.bull.setBorder(null);
					
					//Reset the ball to its start position
					setLocation(startX, startY);
				}
				//If the bull has no shield, start the bull's death timer
				else
					GUI.bull.getDeathTimer().start();
			}
		}

	}

}
