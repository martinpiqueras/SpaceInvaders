package com.space.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.space.game.controller.GameController;
import com.space.game.utilities.ConstantUtilities;
import com.space.game.utilities.InvalidParamException;

public class GamePanel extends JPanel {

	private static final String OVER = "Game Over!";
	private static final String REPLAY = "R to replay";

	public GamePanel() {
		setPreferredSize(new Dimension(ConstantUtilities.WIDTH, ConstantUtilities.HEIGHT));
		setBackground(Color.GRAY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		try {
			super.paintComponent(g);

			GameController controller = new GameController();

			controller.draw(g);

			if (controller.isOver()) {
				printGameOver(g);
			}
		} catch (InvalidParamException e) {

			e.printStackTrace();
		}

	}

	private void printGameOver(Graphics g) {
		Color saved = g.getColor();
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Arial", 20, 20));
		FontMetrics fm = g.getFontMetrics();
		centreString(OVER, g, fm, ConstantUtilities.HEIGHT / 2);
		centreString(REPLAY, g, fm, ConstantUtilities.HEIGHT / 2 + 50);
		g.setColor(saved);
	}

	private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
		int width = fm.stringWidth(str);
		g.drawString(str, (ConstantUtilities.WIDTH - width) / 2, yPos);
	}
}
