package moveable;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Enums.direction;

public abstract class Drawable {

	private int xPos;
	private int yPos;
	private int size;
	private ImageIcon image;

	public Drawable(int xPos, int yPos, int size) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.size = size;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ImageIcon getImageIcon() {
		return this.image;
	}

	public void setImageIcon(ImageIcon image) {
		this.image = image;
	}

	public abstract void setImage(direction right);

	public abstract void draw(Graphics g);

}
