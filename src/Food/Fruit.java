package Food;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Enums.direction;

public class Fruit extends Food {

	ImageIcon blueIcon, greenIcon;

	public Fruit(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		blueIcon = new ImageIcon(getClass().getResource("blue5.png"));
		greenIcon = new ImageIcon(getClass().getResource("greenFruit10.png"));
		setTimer(new Timer(getPreiod(), this));
		setImageIcon(blueIcon);
		getTimer().start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		grow();
	}

	@Override
	public void grow() {
		setAge(getAge() + 1);
		if (getAge() == 5)
			setImageIcon(greenIcon);
		if (getAge() == 10) {
			changePosition();
			setImageIcon(blueIcon);
			getTimer().restart();
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImageIcon().getImage(), getxPos(), getyPos(), getSize(), getSize(), null);
	}

	@Override
	public void setImage(direction right) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefault() {
		setImageIcon(blueIcon);
		getTimer().restart();
		grow();
	}

	@Override
	public int additionScore() {
		return 5 * getAge();
	}
}
