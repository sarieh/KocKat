package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Enums.direction;
import Food.Food;
import Food.Fruit;
import Food.Poison;
import moveable.Ash;
import moveable.Casper;
import moveable.Dolley;
import moveable.Drawable;
import moveable.Ghost;
import moveable.KocCat;
import moveable.moveable;

public class MapFrame extends JPanel implements KeyListener, ActionListener {

	public static int squareLength;
	public static int mapLength;
	private int numOfSquares;
	private JFrame frame;
	private AskingUser userData;
	private KocCat cat;
	private ArrayList<Ghost> ghosts;
	private ArrayList<moveable> moveable;
	private ArrayList<Drawable> drawable;
	private ArrayList<Food> food;
	private Random random;
	private Timer UITimer;
	private String scoreString = "SCORE : 0";
	private JLabel score, gameOver;

	public static int leftMost, rightMost, downMost, upMost;

	public MapFrame(int numOfSquares, AskingUser userData) {
		this.numOfSquares = numOfSquares;
		this.userData = userData;
		ghosts = new ArrayList<>();
		food = new ArrayList<>();
		random = new Random();
		moveable = new ArrayList<>();
		drawable = new ArrayList<>();

		setFrameProperties();
		setMapLength();
		setScreenVisible();
		addElementToMap();

		moveable.addAll(ghosts);
		moveable.add(cat);

		drawable.addAll(food);
		drawable.addAll(ghosts);
		drawable.add(cat);

		frame.add(this);
		UITimer = new Timer(1000 / 60, this);
		UITimer.start();

		int i = 120;
		while (cat.isAlive(ghosts)) {
			moveStep();
			sleepAmoment(i);

			if (i > 50)
				i = i - 2;
		}
		gameOver();
	}

	private void setFrameProperties() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.addKeyListener(this);
		frame.setLayout(null);
		frame.setUndecorated(true);

		score = new JLabel(scoreString);
		score.setFont(new Font("", Font.BOLD, 19));
		score.setBounds(10, 10, 200, 30);
		score.setForeground(Color.RED);

		frame.add(score);
	}

	private void addElementToMap() {
		createCat();
		addGhosts();
		addFood();

	}

	private void createCat() {
		int midX, midY;
		int speed = 15;

		midX = (int) (0.5 * (mapLength - squareLength));
		midY = (int) (0.5 * (mapLength - squareLength));

		cat = new KocCat(midX, midY, squareLength, speed);

	}

	private void addGhosts() {

		int numOfCasper;
		int numOfDolley;
		int rest;
		int numOfAsh;

		int numberEntered = userData.getGhostsNum();
		int oneThird = numberEntered / 3;
		rest = numberEntered % 3;

		if (numberEntered % 3 == 0) {
			numOfCasper = oneThird;
			numOfDolley = oneThird;
			numOfAsh = oneThird;
		} else {
			numOfAsh = oneThird + (1);
			numOfDolley = oneThird + (rest - 1 > 0 ? 1 : 0);
			numOfCasper = oneThird;
		}

		for (int i = 0; i < numOfCasper; i++) {
			ghosts.add(new Casper((random.nextInt(rightMost)), random.nextInt(downMost), squareLength));
		}
		for (int i = 0; i < numOfDolley; i++) {
			ghosts.add(new Dolley((random.nextInt(rightMost)), random.nextInt(downMost), squareLength));
		}
		for (int i = 0; i < numOfAsh; i++) {
			ghosts.add(new Ash((random.nextInt(rightMost)), random.nextInt(downMost), squareLength));
		}

	}

	private void addFood() {
		int numOfFruits = userData.getFruitsNum();
		int numOfPoisenedFood = userData.getPoisonedNum();

		for (int i = 0; i < numOfFruits; i++) {
			food.add(new Fruit((random.nextInt(rightMost)), random.nextInt(downMost), squareLength));
		}
		for (int i = 0; i < numOfPoisenedFood; i++) {
			food.add(new Poison((random.nextInt(rightMost)), random.nextInt(downMost), squareLength));
		}

	}

	private void moveStep() {
		for (moveable object : moveable) {
			object.move();
		}

		for (Food object : food) {
			if (object.consumed(cat.getxPos(), cat.getyPos())) {
				int newScore = cat.getScore() + object.additionScore();
				cat.setScore(newScore);
				object.changePosition();
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		ImageIcon im = new ImageIcon(getClass().getResource("Background.png"));
		g.drawImage(im.getImage(), 0, 0, mapLength, mapLength, null);
		for (Drawable object : drawable) {
			object.draw(g);
		}
	}

	private void setScreenVisible() {
		while (!AskingUser.isAdded()) {
			frame.setVisible(false);
		}
		frame.setVisible(true);
	}

	private void setMapLength() {

		Dimension dim = getToolkit().getScreenSize();
		mapLength = (int) (dim.getHeight());
		setSquareLength();

		leftMost = 0;
		upMost = 0;
		rightMost = mapLength - squareLength;
		downMost = mapLength - squareLength;

		setSize(mapLength, mapLength);
		frame.setSize(mapLength, mapLength);

	}

	private void setSquareLength() {
		squareLength = mapLength / numOfSquares;
	}

	private void sleepAmoment(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void gameOver() {
		for (Food fd : food)
			fd.getTimer().stop();
		gameOver = new JLabel("Game Over !!!");
		gameOver.setFont(new Font("", Font.BOLD, 45));
		gameOver.setBounds(mapLength / 2 - 150, mapLength / 2 - 60, 300, 60);
		gameOver.setOpaque(true);
		gameOver.setBackground(Color.YELLOW);
		gameOver.setForeground(Color.RED);

		JLabel exitLabel = new JLabel("Press ESC to Exit.");
		exitLabel.setFont(new Font("", Font.BOLD, 30));
		exitLabel.setBounds(mapLength / 2 - 140, mapLength / 2, 300, 60);
		exitLabel.setForeground(Color.YELLOW);

		frame.add(gameOver, 0);
		frame.add(exitLabel, 1);

		frame.repaint();
		UITimer.stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int pressedKey = e.getKeyCode();
		if (pressedKey == KeyEvent.VK_LEFT) {
			cat.setDir(direction.left);
		} else if (pressedKey == KeyEvent.VK_RIGHT) {
			cat.setDir(direction.right);
		} else if (pressedKey == KeyEvent.VK_UP) {
			cat.setDir(direction.up);
		} else if (pressedKey == KeyEvent.VK_DOWN) {
			cat.setDir(direction.down);
		} else if (pressedKey == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		scoreString = "SCORE : " + cat.getScore();
		score.setText(scoreString);
		frame.repaint();
	}

}
