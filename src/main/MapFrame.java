package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
	private Timer timer;
	private String scoreString = "the SCORE is : 0";
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
		timer = new Timer(100 / 60, this);
		timer.start();

		int i = 100;

		while (cat.isAlive(ghosts)) {
			moveStep();
			sleepAmoment(i);

			if (i > 40)
				i = i - 2;
		}
		frame.setVisible(false);
		System.exit(0);
	}

	private void setFrameProperties() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.addKeyListener(this);
	}

	private void sleepAmoment(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void moveStep() {
		for (moveable object : moveable) {
			object.move();
		}
		
		for (Food object : food) {
			if (object.consumed(cat.getxPos(), cat.getyPos()))
				cat.setScore(cat.getScore() + object.additionScore());
		}

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

		if (userData.getGhostsNum() == 15) {
			numOfCasper = 5;
			numOfDolley = 5;
			numOfAsh = 5;
		} else {
			numOfAsh = getRandomNumber(userData.getGhostsNum());
			numOfDolley = getRandomNumber(userData.getGhostsNum() - numOfAsh);
			rest = userData.getGhostsNum() - numOfAsh - numOfDolley;
			numOfCasper = rest > 0 ? rest : 0;

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

	private int getRandomNumber(int num) {
		if (num > 0)
			return random.nextInt(userData.getGhostsNum()) + 1;
		else
			return 0;
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
		downMost = mapLength - (int) (1.5 * squareLength);

		setSize(mapLength, mapLength);
		frame.setSize(mapLength, mapLength);

	}

	private void setSquareLength() {
		squareLength = mapLength / numOfSquares;
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
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		scoreString = "the SCORE is : " + cat.getScore();
		frame.repaint();
	}

}
