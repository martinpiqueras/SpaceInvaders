package com.space.game.model;

import java.awt.Graphics;

import com.space.game.utilities.ConstantUtilities;
import com.space.game.utilities.InvalidParamException;

public abstract class Sprite {

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public Sprite(int x, int y, int width, int height) throws InvalidParamException {
		if (x < 0)
			throw new InvalidParamException();
		if (y < 0)
			throw new InvalidParamException();
		if (height < 0)
			throw new InvalidParamException();

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void move() {
		handleBoundary();
	}

	protected void handleBoundary() {
		if (x < 0)
			x = 0;
		else if (x > ConstantUtilities.WIDTH)
			x = ConstantUtilities.WIDTH;
	}

	public abstract void draw(Graphics g);
}
