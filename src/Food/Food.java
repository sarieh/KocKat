package Food;

import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import main.MapFrame;
import moveable.Drawable;

public abstract class Food extends Drawable implements ActionListener {

	ImageIcon imageIcon;
	private int age;
	private int preiod;
	private Timer timer;
	private Random random;

	Food(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		random = new Random();
		setAge(0);
		setPreiod(1000);
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public int getPreiod() {
		return preiod;
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

	public void setPreiod(int preiod) {
		this.preiod = preiod;
	}

	public boolean consumed(int catX, int catY) {
		double centerCatX = catX + 0.5 * MapFrame.squareLength;
		double centerCatY = catY + 0.5 * MapFrame.squareLength;
		boolean case1 = centerCatX >= getxPos() && centerCatX <= getxPos() + MapFrame.squareLength;
		boolean case2 = centerCatY >= getyPos() && centerCatY <= getyPos() + MapFrame.squareLength;
		if (case1 && case2) {
			changePosition();
			return true;
		}
		return false;
	}

	public void changePosition() {
		setxPos(random.nextInt(MapFrame.rightMost));
		setyPos(random.nextInt(MapFrame.downMost));
		setAge(0);
		setDefault();
	}

	public abstract void grow();

	public abstract int additionScore();

	public abstract void setDefault();

}
