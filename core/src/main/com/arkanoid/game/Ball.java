package com.arkanoid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Ball {


    private static boolean startingBall = true;

    private static float BallDirectionX = 0;

    private static float BallDirectionY = -1;

    private static float BallX = (float) GameState.SCREEN_WIDTH/2;

    private static float BallY = (float) GameState.SCREEN_HEIGHT/2;

    public static final float BallWidth = 22;

    public static final float BallHeight = 24;

    public static float BallSpeed = 400;

    public static boolean isStartingBall() {
        return startingBall;
    }

    public static void setStartingBall(boolean startingBall) {
        Ball.startingBall = startingBall;
    }

    public static void move() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        Ball.setBallX(Ball.getBallX() + Ball.getBallDirectionX() * Ball.getBallSpeed() * deltaTime);
        Ball.setBallY(Ball.getBallY() + Ball.getBallDirectionY() * Ball.getBallSpeed() * deltaTime);
    }

    public static void checkWallCollision() {
        if (Ball.getBallX() - 20 < 0 || Ball.getBallX() + Ball.BallWidth + 20 > Gdx.graphics.getWidth()) {
            Ball.setBallDirectionX(Ball.getBallDirectionX() * -1);
        }
    }

    public static void checkCeilingCollision() {
        if (Ball.getBallY() + Ball.BallHeight + 20 > Gdx.graphics.getHeight()) {
            Ball.setBallDirectionY(Ball.getBallDirectionY() * -1);
        }
    }

    public static void checkFloorCollision() {
        if (Ball.getBallY() - 20 < 0) {
            handleFloorCollision();
        }
    }

    public static void handleFloorCollision() {
        GameState.setSpaceVisible(true);
        Health.setHealthCounter(Health.getHealthCounter() - 1);

        if (Health.getHealthCounter() == 0) {
            GameState.setLoser(true);
        } else {
            resetBallPosition();
        }
    }

    public static void resetBallPosition() {
        Ball.setBallX((float) GameState.SCREEN_WIDTH / 2);
        Ball.setBallY((float) GameState.SCREEN_HEIGHT / 2);
        Ball.setBallSpeed(0);
        Ball.setBallDirectionX(0);
        Ball.setBallDirectionY(-1);
        Paddle.setPaddleSpeed(0);
        Paddle.setPaddleX((float) GameState.SCREEN_WIDTH / 2 - 40);
        setStartingBall(true);
    }

    public static void draw(SpriteBatch batch){
        Assets.batch.draw(Assets.pilkaTexture, Ball.getBallX(), Ball.getBallY(), Ball.BallWidth, Ball.BallHeight);
    }

    public static float getBallX() {
        return BallX;
    }

    public static void setBallX(float ballX) {
        BallX = ballX;
    }

    public static float getBallY() {
        return BallY;
    }

    public static void setBallY(float ballY) {
        BallY = ballY;
    }

    public static float getBallSpeed() {
        return BallSpeed;
    }

    public static void setBallSpeed(float speed) {
        BallSpeed = speed;
    }

    public static float getBallDirectionX() {
        return BallDirectionX;
    }

    public static void setBallDirectionX(float ballDirectionX) {
        BallDirectionX = ballDirectionX;
    }

    public static float getBallDirectionY() {
        return BallDirectionY;
    }

    public static void setBallDirectionY(float ballDirectionY) {
        BallDirectionY = ballDirectionY;
    }

}
