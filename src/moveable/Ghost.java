package moveable;

import java.awt.Graphics;
import java.util.Random;

import Enums.direction;

public abstract class Ghost extends Drawable implements moveable {

	private int speed;
	private direction dir;

	public Ghost(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		this.speed = new Random().nextInt(7) + 4;
		// TODO Auto-generated constructor stub
	}

	public direction getDir() {
		return dir;
	}

	public void setDir(direction dir) {
		this.dir = dir;
		setImage(dir);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public abstract void setImage(direction dir);

	@Override
	public abstract void draw(Graphics g);
}
