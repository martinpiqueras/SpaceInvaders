package com.space.game.model;

import java.awt.*;

import com.space.game.utilities.InvalidParamException;

public class Missile extends Sprite {

  public static final int DY = -2;
  public static final int SIZE_X = 5;
  public static final int SIZE_Y = 8;
  private static final Color COLOR = new Color(128, 50, 20);


  public Missile(int x, int y) throws InvalidParamException {
    super(x, y, SIZE_X, SIZE_Y);
  }

  @Override
  public void draw(Graphics g) {
    Color savedCol = g.getColor();
    g.setColor(COLOR);
    g.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
    g.setColor(savedCol);
  }

  @Override
  public void move() {
    y = y + DY;
    super.move();
  }
}
