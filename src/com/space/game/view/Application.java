package com.space.game.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.space.game.controller.GameController;
import com.space.game.utilities.ConstantUtilities;
import com.space.game.utilities.InvalidParamException;

public class Application extends JFrame {

	private GamePanel gamePanel = new GamePanel();
	private ScorePanel scorePanel = new ScorePanel();

	public static void main(String[] args) {
		try {
			new Application();
		} catch (InvalidParamException e) {
			e.printStackTrace();
		}
	}

	public Application() throws InvalidParamException {
		super("Space Invaders");

		prepareFrame();
		addTimer();
	}

	private void prepareFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		add(gamePanel);
		add(scorePanel, BorderLayout.NORTH);
		addKeyListener(new KeyHandler());
		pack();
		centreOnScreen();

	}

	private void centreOnScreen() {
		Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
	}

	private void addTimer() {
		Timer t = new Timer(ConstantUtilities.TIMER_INTERVAL_CHECK, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				gamePanel.repaint();

			}
		});
		t.start();
	}

	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			try {
				new GameController().keyPressed(e.getKeyCode());
			} catch (InvalidParamException e1) {
				e1.printStackTrace();
			}
		}
	}
}
