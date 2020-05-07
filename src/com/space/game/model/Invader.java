package com.space.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.space.game.utilities.InvalidParamException;

public class Invader extends Sprite {

	public static final int SPEED = 1;
	public static final int SIZE_X = 15;
	public static final int SIZE_Y = 9;
	private static final Color COLOR = new Color(10, 50, 188);

	public Invader(int x, int y) throws InvalidParamException {
		super(x, y, SIZE_X, SIZE_Y);
	}

	@Override
	public void draw(Graphics graphics) {
		Color savedCol = graphics.getColor();
		graphics.setColor(COLOR);
		graphics.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
		graphics.setColor(savedCol);
	}

	@Override
	public void move() {
		x = x + new Random().nextInt(3) - 1;
		y = y + SPEED;
		super.move();
	}

	/**
	 * 
	 * Has invader collided with another sprite?
	 * 
	 * returns true if this Invader has collided with other Sprite; false
	 * otherwise
	 * 
	 * @param other
	 * @return
	 */

	public boolean collidedWith(Sprite other) {
		Rectangle thisBoundingRect = new Rectangle(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(),
				getHeight());
		Rectangle otherBoundingRect = new Rectangle(other.getX() - other.getWidth() / 2,
				other.getY() - other.getHeight() / 2, other.getWidth(), other.getHeight());
		return thisBoundingRect.intersects(otherBoundingRect);
	}
}
