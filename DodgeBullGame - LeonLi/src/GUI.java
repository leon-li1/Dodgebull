import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements KeyListener, ActionListener {

	//Class fields (the objects found in game)
	public static Bull bull = new Bull();
	public static JLabel[] lives = new JLabel[3];
	public static JLabel lblScore = new JLabel ("SCORE: 00");
	public static Ball[] ball = new Ball[5];
	public SmartBall smartBall = new SmartBall();
	public Coin coin = new Coin();
	public PowerUpAddLife addLife = new PowerUpAddLife();
	public PowerDownSubtractLife loseLife = new PowerDownSubtractLife();
	public PowerUpShield shield = new PowerUpShield();

	//Static variables (to be accessed from other classes)
	public static int score; //This holds the score
	public static int cntTime; //The game time counter
	public static boolean firstMove = true; //This lets me know if the character starts moving
	public static int cntCoins; //Counts the number of coins collected
	public static int ballIndex; //Lets me know which ball to show in the ball array

	//Constants for the frame width, frame height and the bull's move amount
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 700;
	private static final int MOVE_AMOUNT = 15;

	//Instance fields
	private JPanel GUIPanel = new JPanel(); //A Panel to hold all the objects
	private JLabel lblLives = new JLabel ("LIVES:"); //Label next to the bull lives images
	private JLabel lblGameTime = new JLabel ("TIME: 00"); //Label to display the time (the game clock)
	private Timer gameTimer = new Timer (50, this); //The game timer
	private static int cntTimeTicks = 0; //The game time tick counter (Is reset when the bull loses a life)
	private Font font = new Font ("Calibri", Font.BOLD, 24); //This is the font for the labels
	private int[] speedArray = {15, 20, 25}; //A speed array  to allow for "random" speeds
	private int speedIndex; //Lets me know which speed to use in the speed array
	private int ballSpeed; //The ball speed (varies for each ball)
	private int throwPosition; //The start position for a ball
	private int dx; //The bull's displacement in x
	private int dy; //The bull's displacement in y
	private boolean upKey; //Lets me know if the up arrow key is pressed
	private boolean leftKey; //Lets me know if the left arrow key is pressed
	private boolean rightKey; //Lets me know if the right arrow key is pressed
	private boolean downKey; //Lets me know if the down arrow key is pressed

	//Constructor method
	public GUI() {

		//Call other methods
		frameSetup();
		bullSetup();
		gameObjectsSetup();
		createCoin();
		createBalls();
		createSmartBall();
		createPowerUps();
		repaint();
	}

	//This method creates  the power up/down objects
	private void createPowerUps() {

		//Shield power up
		shield.setBounds(20, 20, 20, 20);
		shield.setVisible(false);
		GUIPanel.add(shield);

		//Add life power up
		addLife.setBounds(20, 20, 20, 20);
		addLife.setVisible(false);
		GUIPanel.add(addLife);

		//Lose life power down
		loseLife.setBounds(20, 20, 20, 20);
		loseLife.setVisible(false);
		GUIPanel.add(loseLife);
	}

	//This method creates the smart ball object
	public void createSmartBall() {

		smartBall = new SmartBall();
		smartBall.setBounds(smartBall.getStartX(), smartBall.getStartY(), 30, 30);
		GUIPanel.add(smartBall);

	}

	//This method creates the balls
	private void createBalls() {

		//Loops through each ball to create the object of a ball
		for (int x = 0 ; x < ball.length ; x++) {

			getRandomSpeed();
			throwPosition = (int)(Math.random() * 4);
			ball[x] = new Ball (ballSpeed, throwPosition);
			ball[x].setBounds(ball[x].getStartX(), ball[x].getStartY(), 30, 30);
			GUIPanel.add(ball[x]);
			ball[x].setVisible(false);

		}

	}

	//This method gets and returns a random speed for each ball
	private int getRandomSpeed() {

		speedIndex = (int)(Math.random() * 3); //Generates a random number between 0 and 2
		ballSpeed = speedArray[speedIndex]; //Sets the ball speed variable to one of the speeds in the array
		return ballSpeed; //returns ball speed
	}

	//This method sets up the frame
	private void frameSetup() {

		//Frame setup
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		//setContentPane(new JLabel (new ImageIcon(new ImageIcon("images/target.png").getImage().getScaledInstance(FRAME_WIDTH,FRAME_HEIGHT,0))));
		addKeyListener(this);
		setVisible(true);

		//Panel setup
		GUIPanel.setBounds(0, 0, FRAME_WIDTH - 5, FRAME_HEIGHT - 28); 
		GUIPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		GUIPanel.setLayout(null);
		add(GUIPanel);
	}

	//This method creates the bull object
	private void bullSetup() {
		
		bull.setBounds(bull.getX(), bull.getY(), 60, 60);
		GUIPanel.add(bull);
	
	}

	//This method creates the non-moving game objects 
	private void gameObjectsSetup() {

		//Loops through each of the lives and creates an image for each one
		for (int x = 0; x < lives.length; x++) {

			lives[x] = new JLabel();
			lives[x].setBounds(70 + 25 * x, 33, 30, 25);
			lives[x].setIcon(new ImageIcon(new ImageIcon("images/bull.png").getImage().getScaledInstance(30, 25, 0)));
			GUIPanel.add(lives[x]);

		}

		//Creates the lives label
		lblLives.setBounds(10, 35, 65, 25);
		lblLives.setFont(font);
		lblLives.setForeground(Color.RED);
		//lblLives.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		GUIPanel.add(lblLives);

		//Creates the score label
		lblScore.setBounds(10, 8, 170, 25);
		lblScore.setFont(font);
		lblScore.setForeground(Color.RED);
		GUIPanel.add(lblScore);

		//Creates the time label (game clock)
		lblGameTime.setBounds(585, 8, 120, 25);
		lblGameTime.setFont(font);
		lblGameTime.setForeground(Color.RED);
		GUIPanel.add(lblGameTime);

	}

	//This method sets the score label to the current score
	public static String scoreToString() {

		return "SCORE: " + score;

	}

	//This method sets the time label to the current time
	public String timeToString() {

		return "TIME: " + cntTime;

	}

	//This method clears the GUI of the balls
	public static void resetGUI() {

		//reset balls
		for (int x = 1; x < ball.length; x++) {
			ball[x].setVisible(false);
		}

		//Reset the time ticks and the ball index to 0
		cntTimeTicks = 0;
		ballIndex = 0;
	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {
		
		//Checks if the source was the count down timer
		if (event.getSource() == gameTimer) {

			//Increment the time ticks by 1
			cntTimeTicks++;
			
			//Checks if the death timer is not running
			if (bull.getDeathTimer().isRunning() == false) {
				
				//Set dx and dy to 0
				dx = 0;
				dy = 0;

				//Depending on which arrow key is pressed and if the bull is within the frame, set dx or dy to corresponding move amount
				if (leftKey && bull.getX() > 10)		
					dx = -MOVE_AMOUNT;
				if (upKey && bull.getY() > 10)
					dy = -MOVE_AMOUNT;
				if (rightKey && bull.getX() < FRAME_WIDTH - 75)
					dx = MOVE_AMOUNT;
				if (downKey && bull.getY() < FRAME_HEIGHT - 105)
					dy = MOVE_AMOUNT;

				//Move the bull according to key pressed 
				bull.setLocation(bull.getX() + dx, bull.getY() + dy);
			}

			//Checks if the bull has touched a coin
			if (bull.getBounds().intersects(coin.getBounds())) {
				
				//Increment the coin counter by 1
				cntCoins++;
				
				//Call the touch coin method from the coin class
				coin.touchCoin();
			}

			//Checks if a second has gone by
			if (cntTimeTicks % 20 == 0) {
				
				//Decrease the count by 1 and update the time label
				cntTime++;
				lblGameTime.setText(timeToString());

				//Increase the score by 10 and update the score label
				score += 10;
				lblScore.setText(scoreToString());
			}

			//Checks if 3 seconds has gone by
			if (cntTimeTicks % 60 == 0) {

				//If the last ball in the ball array is still invisible, show a ball
				if (ball[4].isVisible() == false) {
					ball[ballIndex].setVisible(true);
					ballIndex++;
				}
			}
		}
	}

	//This method implements the key listener
	public void keyReleased(KeyEvent event) {

		//Stores the key released temporarily
		int keyReleased = event.getKeyCode();

		//Checks which key has been released and switches off the corresponding condition
		if (keyReleased == 37)
			leftKey = false;
		else if (keyReleased == 38)
			upKey = false;
		else if (keyReleased == 39)
			rightKey = false;
		else if (keyReleased == 40)
			downKey = false;
	}

	//This method implements the key listener
	public void keyPressed(KeyEvent event) {

		//Stores the key pressed temporarily
		int key = event.getKeyCode();

		//Checks if key pressed is an arrow key
		if (key >= 37 && key <= 40) {

			//Checks which key has been pressed and switches on the corresponding condition
			if (key == 37)
				leftKey = true;
			else if (key == 38)
				upKey = true;
			else if (key == 39)
				rightKey = true;
			else if (key == 40)
				downKey = true;

			//Checks if it's the first time the bull moves, if it is, start the timers and switch the first move condition to false
			if (firstMove) {

				gameTimer.start();
				shield.getTmrShield().start();
				addLife.getTmrAddLife().start();
				loseLife.getTmrSubtractLife().start();
				firstMove = false;

			}
		}
	}

	//This method creates the coin object
	private void createCoin() {

		coin.setBounds(coin.getX(), coin.getY(), 20, 20);
		GUIPanel.add(coin);
	}

	//Mandatory method
	public void keyTyped(KeyEvent event) {
		
	}

}
