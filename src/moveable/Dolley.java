package moveable;

import java.awt.Graphics;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Enums.direction;
import main.MapFrame;

public class Dolley extends Ghost {

	ImageIcon u, d;

	public Dolley(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		d = new ImageIcon(getClass().getResource("DollyDown.png"));
		u = new ImageIcon(getClass().getResource("DollyUp.png"));
		doAction();
	}

	@Override
	public void moveRight() {
	}

	@Override
	public void moveLeft() {
	}

	@Override
	public void moveDown() {
		setyPos(getyPos() + getSpeed() < MapFrame.downMost ? getyPos() + getSpeed() : MapFrame.downMost);
		setImage(direction.down);
	}

	@Override
	public void moveUp() {
		setyPos(getyPos() - getSpeed() > MapFrame.upMost ? getyPos() - getSpeed() : MapFrame.upMost);
		setImage(direction.up);
	}

	@Override
	public void setImage(direction dir) {
		try {
			if (dir == direction.down) {
				setImageIcon(d);
			} else if (dir == direction.up) {
				setImageIcon(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImageIcon().getImage(), getxPos(), getyPos(), getSize(), getSize(), null);
	}

	public void doAction() {
		Random ran = new Random();
		int dir = ran.nextInt(10);
		if (dir % 2 == 0) {
			setDir(direction.up);
		} else {
			setDir(direction.down);
		}
	}

	@Override
	public void move() {

		switch (getDir()) {
		case up:
			moveUp();
			if (getyPos() == MapFrame.upMost) {
				setDir(direction.down);
			}
			break;
		case down:
			moveDown();
			if (getyPos() == MapFrame.downMost) {
				setDir(direction.up);
			}
		}
	}

}
