package moveable;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Enums.direction;
import main.MapFrame;

public class KocCat extends Drawable implements moveable {

	private int score;
	private int speed;
	private direction dir;

	public KocCat(int xPos, int yPos, int size, int speed) {
		super(xPos, yPos, size);
		this.score = 0;
		this.dir = direction.right;
		setxPos(xPos);
		setyPos(yPos);
		setSize(size);
		this.speed = speed;
		setDir(dir);
		move();
	}

	public direction getDir() {
		return dir;
	}

	public void setDir(direction dir) {
		setImage(dir);
		this.dir = dir;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImageIcon().getImage(), getxPos(), getyPos(), getSize(), getSize(), null);
	}

	@Override
	public void moveRight() {
		setxPos(getxPos() + getSpeed() < MapFrame.rightMost ? getxPos() + getSpeed() : MapFrame.rightMost);
		setImage(direction.right);
	}

	public boolean isAlive(ArrayList<Ghost> ghosts) {

		if (getScore() < 0)
			return false;
		for (Ghost ghost : ghosts) {

			double centerCatX = getxPos() + 0.5 * MapFrame.squareLength;
			double centerCatY = getyPos() + 0.5 * MapFrame.squareLength;
			boolean case1 = centerCatX >= ghost.getxPos() && centerCatX <= ghost.getxPos() + MapFrame.squareLength;
			boolean case2 = centerCatY >= ghost.getyPos() && centerCatY <= ghost.getyPos() + MapFrame.squareLength;
			if (case1 && case2)
				return false;
		}
		return true;
	}

	@Override
	public void moveLeft() {
		setxPos((getxPos() - getSpeed() > MapFrame.leftMost) ? getxPos() - getSpeed() : MapFrame.leftMost);
		setImage(direction.left);
	}

	@Override
	public void moveDown() {
		setyPos(getyPos() + getSpeed() < MapFrame.downMost ? getyPos() + getSpeed() : MapFrame.downMost);
		setImage(direction.down);
	}

	@Override
	public void moveUp() {
		setyPos((getyPos() - getSpeed() > MapFrame.upMost) ? getyPos() - getSpeed() : MapFrame.upMost);
		setImage(direction.up);
	}

	@Override
	public void setImage(direction dir) {

		try {
			if (dir == direction.right) {
				setImageIcon(new ImageIcon(getClass().getResource("right.png")));
			} else if (dir == direction.up) {
				setImageIcon(new ImageIcon(getClass().getResource("up.png")));
			} else if (dir == direction.down) {
				setImageIcon(new ImageIcon(getClass().getResource("down.png")));
			} else if (dir == direction.left) {
				setImageIcon(new ImageIcon(getClass().getResource("left.png")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ImageIcon getImageIcon() {
		return super.getImageIcon();
	}

	@Override
	public void move() {

		switch (dir) {
		case right:
			moveRight();
			break;
		case up:
			moveUp();
			break;
		case down:
			moveDown();
			break;
		case left:
			moveLeft();
			break;
		}
	}

}
