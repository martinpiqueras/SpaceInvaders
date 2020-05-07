package com.space.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import com.space.game.utilities.ConstantUtilities;
import com.space.game.utilities.InvalidParamException;

public class Spaceship extends Sprite {

	public static final int SPEED = 3;

	private static final int SIZE_X = 15;
	private static final int SIZE_Y = 8;

	private static final int SHIP_POS_Y = ConstantUtilities.HEIGHT - 40;
	private static final int SHIP_POS_X = ConstantUtilities.WIDTH / 2;

	private static final Color COLOR = new Color(250, 128, 20);
	private static final int LEFT = -1;
	private static final int RIGHT = 1;

	private int direction;

	public Spaceship() throws InvalidParamException {
		super(SHIP_POS_X, SHIP_POS_Y, SIZE_X, SIZE_Y);
		direction = RIGHT;
	}

	public boolean isFacingRight() {
		return direction == RIGHT;
	}

	public void turnRight() {
		direction = RIGHT;
	}

	public void turnLeft() {
		direction = LEFT;
	}

	@Override
	public void move() {
		x = x + direction * SPEED;
		super.move();
	}

	@Override
	public void draw(Graphics graphics) {
		Color savedCol = graphics.getColor();
		graphics.setColor(COLOR);
		graphics.fillRect(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
		Polygon polygon = createPolygon();
		graphics.fillPolygon(polygon);
		graphics.setColor(savedCol);
	}

	private Polygon createPolygon() {
		Polygon polygon = new Polygon();

		if (direction == RIGHT) {
			setUpRightPoint(polygon);
		} else {
			setUpLeftPoint(polygon);
		}
		return polygon;
	}

	private void setUpRightPoint(Polygon polygon) {
		polygon.addPoint(x + SIZE_X / 2, SHIP_POS_Y + SIZE_Y / 2);
		polygon.addPoint(x + SIZE_X, SHIP_POS_Y);
		polygon.addPoint(x + SIZE_X / 2, SHIP_POS_Y - SIZE_Y / 2);
	}

	private void setUpLeftPoint(Polygon polygon) {
		polygon.addPoint(x - SIZE_X / 2, SHIP_POS_Y + SIZE_Y / 2);
		polygon.addPoint(x - SIZE_X, SHIP_POS_Y);
		polygon.addPoint(x - SIZE_X / 2, SHIP_POS_Y - SIZE_Y / 2);
	}

	public Sprite fireMissile() throws InvalidParamException {
		return new Missile(getX(), getY());
	}
}
