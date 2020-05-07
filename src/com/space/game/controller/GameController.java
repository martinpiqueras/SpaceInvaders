package com.space.game.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.space.game.model.Invader;
import com.space.game.model.Missile;
import com.space.game.model.Spaceship;
import com.space.game.model.Sprite;
import com.space.game.utilities.ConstantUtilities;
import com.space.game.utilities.InvalidParamException;

public class GameController  {


	private List<Sprite> sprites = new ArrayList<Sprite>();
	private Spaceship spaceShip;

	private boolean isGameOver;
	private int numMissilesInPlay;
	private int numInvadersDestroyed;

	public GameController() throws InvalidParamException {
		initializeSprites();
		resetGame();
	}

	public void update() throws InvalidParamException {
		moveSprites();
		clearMissiles();
		createRandomInvaders();
		checkCollisions();
		checkGameOver();
	}

	public void keyPressed(int keyCode) throws InvalidParamException {
		if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
			spaceShip.turnLeft();
		else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
			spaceShip.turnRight();
		else if (keyCode == KeyEvent.VK_SPACE)
			fireMissile();
		else if (keyCode == KeyEvent.VK_R && isGameOver)
			resetGame();
		else if (keyCode == KeyEvent.VK_X)
			System.exit(0);
	}

	public void draw(Graphics g) {
		for (Sprite aSprite : sprites)
			aSprite.draw(g);
	}

	public boolean isOver() {
		return isGameOver;
	}

	public int getNumMissiles() {
		return numMissilesInPlay;
	}

	public int getNumInvadersDestroyed() {
		return numInvadersDestroyed;
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	public Spaceship getSpaceShip() {
		return spaceShip;
	}

	private void moveSprites() {
		for (Sprite next : sprites) {
			next.move();
		}
	}

	private void resetGame() {
		isGameOver = false;
		numMissilesInPlay = 0;
		numInvadersDestroyed = 0;		
	}

	private void initializeSprites() throws InvalidParamException {
		sprites.clear();
		spaceShip = new Spaceship();
		sprites.add(spaceShip);
	}

	private void fireMissile() throws InvalidParamException {
		Sprite missile = spaceShip.fireMissile();

		if (numMissilesInPlay < ConstantUtilities.MAX_MISSILES) {
			numMissilesInPlay++;
			sprites.add(missile);
		}
	}

	private void clearMissiles() {

		List<Sprite> missilesToRemove = new ArrayList<Sprite>();

		for (Sprite next : sprites) {
			if (next.getY() < 0) {
				missilesToRemove.add(next);
				numMissilesInPlay--;
			}
		}

		sprites.removeAll(missilesToRemove);
	}

	private void createRandomInvaders() throws InvalidParamException {
		if (new Random().nextInt(250) < 2) {
			Invader i = new Invader(new Random().nextInt(ConstantUtilities.WIDTH), 20);
			sprites.add(i);			
		}
	}

	private void checkCollisions() {
		List<Sprite> toBeRemoved = new ArrayList<Sprite>();
		for (Sprite next : sprites) {
			if (next instanceof Invader) {
				checkInvaderHit((Invader) next, toBeRemoved);
			}
		}

		sprites.removeAll(toBeRemoved);
	}

	private void checkInvaderHit(Invader target, List<Sprite> missilesToRemove) {
		for (Sprite next : sprites) {
			if (next instanceof Missile) {
				if (target.collidedWith(next)) {
					missilesToRemove.add(target);
					missilesToRemove.add(next);
					invaderHit();
				}
			}
		}
	}

	private void invaderHit() {
		numMissilesInPlay--;
		numInvadersDestroyed++;		
	}

	private void checkGameOver() throws InvalidParamException {
		for (Sprite next : sprites) {
			if (next.getY() > ConstantUtilities.HEIGHT) {
				isGameOver = true;
				break;
			}
		}

		if (isGameOver) {
			initializeSprites();
		}
	}

}
