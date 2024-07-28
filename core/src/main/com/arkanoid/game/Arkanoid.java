package com.arkanoid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;


public class Arkanoid extends ApplicationAdapter {


	private Block blockLevel1;


	private Block blockLevel2;


	@Override
	public void create() {
		// Creating blocks for different levels of the game
		blockLevel1 = new Block(1);
		blockLevel2 = new Block(2);

		// Music
		Assets.load();
		Assets.music.setLooping(true);
		Assets.music.play();
		Assets.music.setVolume(0.2f);

		// Font
		Assets.font.getData().setScale(Assets.initialSize);
		Assets.font2.getData().setScale(Assets.initialSize);

		// Cursor
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(Assets.pm, 0, 0));

		Assets.batch = new SpriteBatch();

		// Creating Display
		OrthographicCamera camera = new OrthographicCamera(GameState.SCREEN_WIDTH, GameState.SCREEN_HEIGHT);
		camera.setToOrtho(false, GameState.SCREEN_WIDTH, GameState.SCREEN_HEIGHT);
		Assets.batch.setProjectionMatrix(camera.combined);


		Gdx.input.setInputProcessor(new InputAdapter() {

			@Override
			// Button Handling
			public boolean keyDown(int keycode) {

				// Exit
				if (keycode == Keys.ESCAPE) {
					Gdx.app.exit();
					return true;
				}

				// Move left
				if (keycode == Keys.A) {
					GameState.setWLewo(true);
					return true;
				}

				// Move right
				if (keycode == Keys.D) {
					GameState.setWPrawo(true);
					return true;
				}

				// Space button handling
				if (GameState.isRunning() && keycode == Keys.SPACE) {
					if (GameState.isSpaceVisible()) {
						GameState.setSpaceVisible(false);
						if (Ball.isStartingBall()) {
							Ball.setBallSpeed(400);
							Paddle.setPaddleSpeed(10f);
							Ball.setStartingBall(false);
						}
					} else {
						Ball.setBallSpeed(0);
						Paddle.setPaddleSpeed(0f);
						Ball.setStartingBall(true);
						GameState.setSpaceVisible(true);
					}
					return true;
				}
				//Wł/wył muzyki
				if (keycode == Keys.M) {
					OnOffMusic();
					return true;
				}

				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				// Move left
				if (keycode == Keys.A) {
					GameState.setWLewo(false);
					return true;
				}

				// Move right
				if (keycode == Keys.D) {
					GameState.setWPrawo(false);
					return true;
				}

				return false;
			}


			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {

				// Convert screen coordinates to game coordinates
				float gameX = screenX / (float) Gdx.graphics.getWidth() * Gdx.graphics.getWidth();
				float gameY = Gdx.graphics.getHeight() - screenY / (float) Gdx.graphics.getHeight() * Gdx.graphics.getHeight();

				// "New Game" button
				if (GameState.isNewGameVisible() && gameX >= (float) GameState.SCREEN_WIDTH/2 - 95 && gameX <= (float) GameState.SCREEN_WIDTH/2 + 95 && gameY >= (float) GameState.SCREEN_HEIGHT/2 && gameY <= (float) GameState.SCREEN_HEIGHT/2 + 28) {
					GameState.setRunning(true);
					GameState.setSpaceVisible(true);
					GameState.setNewGameVisible(false);
					Ball.setBallSpeed(0);
					Paddle.setPaddleSpeed(0f);
					return true;
				}

				// "Exit" button
				if (!GameState.isRunning() && GameState.isNewGameVisible() && gameX >= (float) GameState.SCREEN_WIDTH/2 - 45 && gameX <= (float) GameState.SCREEN_WIDTH/2 + 45 && gameY >= (float) GameState.SCREEN_HEIGHT/2 - 45 && gameY <= (float) GameState.SCREEN_HEIGHT/2) {
					Gdx.app.exit();
					return true;
				}

				// Turn music on/off
				if (gameX >= 495 && gameX <=  540 && gameY >=  5 && gameY <= 55) {
					OnOffMusic();
					return true;
				}
				return false;
			}
		});
	}


	public void OnOffMusic() {
		if (Assets.music.isPlaying()) {
			Assets.music.pause();
		}
		else {
			Assets.music.play();
		}
	}


	public void WinScreen() {
		// Set parameters
		Ball.setBallSpeed(0);
		Ball.setBallX((float) GameState.SCREEN_WIDTH / 2);
		Ball.setBallY((float) GameState.SCREEN_HEIGHT / 2);
		Paddle.setPaddleSpeed(0f);
		Paddle.setPaddleX((float) GameState.SCREEN_WIDTH/2 - 40);

		//Display text
		Assets.font.draw(Assets.batch, "YOU ARE A WINNER!!!", (float) GameState.SCREEN_WIDTH /2 - 210, (float) GameState.SCREEN_HEIGHT/2 - 20);
		Assets.font.draw(Assets.batch, "Your Score: " + GameState.getScore(), (float) GameState.SCREEN_WIDTH/2 - 145, (float) GameState.SCREEN_HEIGHT/2 - 63);

		// Set timer for 10 seconds
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				Gdx.app.exit();
			}
		}, 10);
	}


	public void LosingScreen() {
		// Set parameters
		Ball.setBallSpeed(0);
		Paddle.setPaddleSpeed(0);

		//Display text
		Assets.font.draw(Assets.batch, "You Lose!", (float) GameState.SCREEN_WIDTH / 2 - 95, (float) GameState.SCREEN_HEIGHT / 2 - 20);
		Assets.font.draw(Assets.batch, "Your Score: " + GameState.getScore(), (float) GameState.SCREEN_WIDTH / 2 - 145, (float) GameState.SCREEN_HEIGHT / 2 - 63);

			// Set timer for 10 seconds
			Timer.schedule(new Timer.Task() {
				@Override
				public void run() {
					Gdx.app.exit();
				}
			}, 10);
	}


	public void Gameplay(int level) {
		// Paddle movement
		if (GameState.isWLewo()) {
			Paddle.moveLeft();
		}
		if (GameState.isWPrawo()) {
			Paddle.moveRight();
		}

		if (GameState.isLoser()) {
			GameState.setSpaceVisible(false);
			LosingScreen();
		}

		if (GameState.isWinner() && level == 2) {
			GameState.setSpaceVisible(false);
			WinScreen();
		}

		if (GameState.isSpaceVisible()) {
			Assets.font2.draw(Assets.batch, "Press 'SPACE' to start",(float) GameState.SCREEN_WIDTH/2 - 220,(float) GameState.SCREEN_WIDTH/2);
		}

		if(level == 1) {
			blockLevel1.handleCollisionWithBall();

		} else if (level == 2) {
			blockLevel2.handleCollisionWithBall();
		}

		Assets.font.draw(Assets.batch, "Health:",25, 30);
		Assets.font.draw(Assets.batch, "Score:" + GameState.getScore(), 25, 590);

		if(level == 1) {
			Assets.font.draw(Assets.batch, "Level:1", 380, 590);
		}
		else if (level == 2) {
			Assets.font.draw(Assets.batch, "Level:2", 380, 590);
		}

		// Health
		Health.drawLives(Assets.batch, Health.getHealthCounter());

		// Ball movement and wall collisions
		Ball.move();
		Ball.checkWallCollision();
		Ball.checkCeilingCollision();
		Ball.checkFloorCollision();

		// Check paddle-ball collision
		Paddle.checkCollisionWithBall();

		//Draw blocks
		if(level == 1) {
			blockLevel1.drawBlocks(Assets.batch);

		}
		else if (level == 2) {
			blockLevel2.drawBlocks(Assets.batch);
		}

		if(level == 1) {
			if(GameState.getScore() >= 270) {
			Ball.setBallSpeed(0);
			Paddle.setPaddleSpeed(0);
			Paddle.setPaddleX((float) GameState.SCREEN_WIDTH/2 - 40);
			Ball.setBallX((float) GameState.SCREEN_WIDTH/2);
			Ball.setBallY((float) GameState.SCREEN_HEIGHT/2);
			Ball.setStartingBall(true);
			GameState.setLevelComplete(true);
			GameState.setSpaceVisible(true);
			Ball.setBallDirectionX(0);
			return;
			}
		}
		else if (level == 2) {
			if(GameState.getScore() >= 630)
				GameState.setWinner(true);
		}

		Ball.draw(Assets.batch);
	}


	@Override
	public void render() {
		Assets.batch.begin();

		Texture currentBg = GameState.isRunning() ? Assets.bg2 : Assets.bg;
		Texture currentMusic = Assets.music.isPlaying() ? Assets.MusicOn : Assets.MusicOff;
		Assets.batch.draw(currentBg, 0, 0);
		Assets.batch.draw(currentMusic, 510, 10);

		if (GameState.isNewGameVisible()) {
			Assets.font.draw(Assets.batch, "New Game", (float) GameState.SCREEN_WIDTH /2 - 85, (float) GameState.SCREEN_HEIGHT/2 + 28);
			Assets.font.draw(Assets.batch, "Exit", (float) GameState.SCREEN_WIDTH/2 - 40, (float) GameState.SCREEN_HEIGHT/2 - 15);
		}

		// Start game
		if (GameState.isRunning()) {
			if(!GameState.isLevelComplete()){
				Gameplay(1);
			}
			if(GameState.isLevelComplete()) {
				Gameplay(2);
			}
			Paddle.draw(Assets.batch);
		}
		Assets.batch.end();
	}


	@Override
	public void dispose() {
		new Assets().dispose();
	}
}