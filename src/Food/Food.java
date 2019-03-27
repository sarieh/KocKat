package Food;

import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import Enums.Status;
import main.MapFrame;
import moveable.Drawable;

public abstract class Food extends Drawable implements ActionListener {

	private ImageIcon imageIcon;
	private int age;
	private final int period = 2000;
	private Timer timer;
	private Random random;
	private Status status;
	private int newSize;
	private final int initialAge = 1;

	Food(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		random = new Random();
		setStatus(Status.small);
		setImageSize(getStatus());
	}

	public int getInitialAge() {
		return initialAge;
	}

	public int getNewSize() {
		return newSize;
	}

	public void setNewSize(int newSize) {
		this.newSize = newSize;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public int getPeriod() {
		return period;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean consumed(int catX, int catY) {
		double centerCatX = catX + 0.5 * MapFrame.squareLength;
		double centerCatY = catY + 0.5 * MapFrame.squareLength;
		boolean case1 = centerCatX >= getxPos() && centerCatX <= getxPos() + MapFrame.squareLength;
		boolean case2 = centerCatY >= getyPos() && centerCatY <= getyPos() + MapFrame.squareLength;
		if (case1 && case2) {
			return true;
		}
		return false;
	}

	public void changePosition() {
		setxPos(random.nextInt(MapFrame.rightMost));
		setyPos(random.nextInt(MapFrame.downMost));
		setDefault();
	}

	public void setImageSize(Status status) {
		switch (status) {
		case small:
			setNewSize((int) (0.6 * getSize()));
			break;
		case medium:
			setNewSize((int) (0.8 * getSize()));
			break;
		case large:
			setNewSize(getSize());
			break;
		}
	}

	public abstract void grow();

	public abstract int additionScore();

	public abstract void setDefault();
}
