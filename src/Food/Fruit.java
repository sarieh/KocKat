package Food;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Enums.Status;
import Enums.direction;

public class Fruit extends Food {

	private ImageIcon blueIcon, greenIcon;

	public Fruit(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
		blueIcon = new ImageIcon(getClass().getResource("blue5.png"));
		greenIcon = new ImageIcon(getClass().getResource("greenFruit10.png"));
		setAge(getInitialAge());
		setTimer(new Timer(getPeriod(), this));
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

		switch (getAge()) {
		case 4:
			setStatus(Status.medium);
			setImageSize(getStatus());
			break;
		case 7:
			setImageIcon(greenIcon);
			setStatus(Status.large);
			setImageSize(getStatus());
			break;
		case 10:
			changePosition();
			setImageIcon(blueIcon);
			getTimer().restart();
			break;
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImageIcon().getImage(), getxPos(), getyPos(), getNewSize(), getNewSize(), null);
	}

	@Override
	public void setImage(direction right) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefault() {
		setImageIcon(blueIcon);
		setStatus(Status.small);
		setImageSize(getStatus());
		setAge(getInitialAge());
		getTimer().restart();
	}

	@Override
	public int additionScore() {
		return (5 * this.getAge());
	}
}
