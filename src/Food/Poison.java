package Food;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Enums.direction;

public class Poison extends Food {

	ImageIcon poison5, poison10;

	public Poison(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		poison5 = new ImageIcon(getClass().getResource("poison5.png"));
		poison10 = new ImageIcon(getClass().getResource("poison10.png"));
		setTimer(new Timer(getPreiod(), this));
		setImageIcon(poison5);
		getTimer().start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		grow();
	}

	@Override
	public void grow() {
		setAge(getAge() - 1);
		if (getAge() == -10)
			setImageIcon(poison10);
	}

	@Override
	public void setDefault() {
		setImageIcon(poison5);
		getTimer().restart();
		grow();
	}

	@Override
	public void setImage(direction right) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImageIcon().getImage(), getxPos(), getyPos(), getSize(), getSize(), null);
	}

	@Override
	public int additionScore() {
		return 10 * getAge();
	}

}