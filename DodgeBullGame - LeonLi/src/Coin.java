import javax.swing.*;

public class Coin extends JLabel  {

	//Instance fields
	private int x; //The x position
	private int y; //The y position

	//Constructor method
	public Coin() {

		//Set the image of the coin
		setIcon( new ImageIcon ("Images/coin.png"));

		//Position the coin at a random location on the screen (max y is 645 max x is 670)
		x = (int)(Math.random() * (670 - 5)) + 5;
		y = (int)(Math.random() * (645 - 60)) + 60;
		setLocation(x, y);
	}

	//Getter for the x position
	public int getX() {

		return x;

	}
	
	//Getter for the y position
	public int getY() {

		return y;

	}
	
	//This method runs when a coin is touched
	public void touchCoin() {

		//Reposition the coin at a random location on the screen
		x = (int)(Math.random() * (670 - 5)) + 5;
		y = (int)(Math.random() * (645 - 60)) + 60;
		setLocation(x, y);

		//Increase the score by 50 and update the score label
		GUI.score += 50;
		GUI.lblScore.setText(GUI.scoreToString());

	}
}