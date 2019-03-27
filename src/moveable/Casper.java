package moveable;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import Enums.direction;
import main.MapFrame;

public class Casper extends Ghost {

	int xSpeed = getSpeed();
	int ySpeed = getSpeed();

	public Casper(int xPos, int yPos, int size) {
		super(xPos, yPos, size);

		xSpeed = getSpeed();
		ySpeed = getSpeed();
		super.setImageIcon(new ImageIcon(getClass().getResource("Casper.png")));
		doAction();
	}

	@Override
	public void moveRight() {
		move();
	}

	@Override
	public void moveLeft() {
		move();
	}

	@Override
	public void moveDown() {
		move();
	}

	@Override
	public void moveUp() {
		move();
	}

	@Override
	public void setImage(direction dir) {

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImageIcon().getImage(), getxPos(), getyPos(), getSize(), getSize(), null);
	}

	public void doAction() {
		move();
	}

	@Override
	public void move() {

		if (getxPos() + xSpeed > MapFrame.rightMost) {
			xSpeed = -xSpeed;
		}
		if (getxPos() + xSpeed < MapFrame.leftMost) {
			xSpeed = -xSpeed;
		}

		if (getyPos() + xSpeed < MapFrame.upMost) {
			ySpeed = -ySpeed;
		}
		if (getyPos() + xSpeed > MapFrame.downMost) {
			ySpeed = -ySpeed;
		}

		setxPos(getxPos() + xSpeed);
		setyPos(getyPos() + ySpeed);
	}
}
