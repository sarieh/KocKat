package moveable;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import Enums.direction;
import main.MapFrame;

public class Ash extends Ghost {

	private ImageIcon r, l;

	public Ash(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		r = new ImageIcon(getClass().getResource("AshRight.png"));
		l = new ImageIcon(getClass().getResource("AshLeft.png"));
		doAction();
	}

	@Override
	public void moveRight() {
		setxPos(getxPos() + getSpeed() < MapFrame.rightMost ? getxPos() + getSpeed() : MapFrame.rightMost);
		setImage(direction.right);
	}

	@Override
	public void moveLeft() {
		setxPos((getxPos() - getSpeed() > MapFrame.leftMost) ? getxPos() - getSpeed() : MapFrame.leftMost);
		setImage(direction.left);
	}

	@Override
	public void moveDown() {
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void setImage(direction dir) {
		try {
			if (dir == direction.right) {
				setImageIcon(r);
			} else {
				setImageIcon(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		if (g != null)
			g.drawImage(getImageIcon().getImage(), getxPos(), getyPos(), getSize(), getSize(), null);
	}

	public void doAction() {
		Random ran = new Random();
		int dir = ran.nextInt(10);
		if (dir % 2 == 0) {
			setDir(direction.right);
		} else {
			setDir(direction.left);
		}
	}

	@Override
	public void move() {

		switch (getDir()) {
		case right:
			moveRight();
			if (getxPos() == MapFrame.rightMost) {
				setDir(direction.left);
			}
			break;
		case left:
			moveLeft();
			if (getxPos() == MapFrame.leftMost) {
				setDir(direction.right);
			}
			break;
		}
	}

}
