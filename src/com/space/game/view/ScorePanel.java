package com.space.game.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.space.game.utilities.ConstantUtilities;

public class ScorePanel extends JPanel {

	private static final int LBL_WIDTH = 200;
	private static final int LBL_HEIGHT = 30;

	private JLabel invadersLbl;
	private JLabel missilesLbl;

	public ScorePanel() {
		setBackground(new Color(180, 180, 180));

		initInvaidersLabel(0);
		initMissilesLabel(ConstantUtilities.MAX_MISSILES);

		add(Box.createHorizontalStrut(10));

	}

	private void initInvaidersLabel(int invidersShot) {
		invadersLbl = new JLabel();
		setTextInvaiders(0);
		invadersLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		add(invadersLbl);
	}

	private void setTextInvaiders(int invidersShot) {
		invadersLbl.setText("Invaders shot down: " + invidersShot);
	}

	private void setTextMissiles(int missilesRemaining) {
		missilesLbl.setText("Missiles remaining: " + missilesRemaining);
	}

	private void initMissilesLabel(int missilesRemaining) {
		missilesLbl = new JLabel();
		setTextMissiles(ConstantUtilities.MAX_MISSILES);
		missilesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		add(missilesLbl);
	}

}
