package com.arkanoid.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

public class Paddle{

    private static final float PaddleY = 45;

    private static float PaddleX = (float) GameState.SCREEN_WIDTH / 2 - 40;

    private static float PaddleSpeed = 10f;

    private static boolean paddleHit = false;

    public static final float paddleWidth = 100;

    public static final float paddleHeight = 30;

    public static void draw(SpriteBatch batch) {
        batch.draw(Assets.paletkaTexture, PaddleX, PaddleY, paddleWidth, paddleHeight);
    }

    public static void checkCollisionWithBall() {
        if (Ball.getBallY() < PaddleY + paddleHeight &&
                Ball.getBallY() + Ball.BallHeight > PaddleY &&
                Ball.getBallX() < PaddleX + paddleWidth &&
                Ball.getBallX() + Ball.BallWidth > PaddleX) {

            float paddleCenterX = PaddleX + paddleWidth / 2;
            float distanceFromCenter = Ball.getBallX() + Ball.BallWidth / 2 - paddleCenterX;
            float normalizedDistance = distanceFromCenter / (paddleWidth / 2);

            Ball.setBallDirectionY(1);

            float impactStrength = 0.5f;
            Ball.setBallDirectionX(normalizedDistance * impactStrength);

            if (!Paddle.isPaddleHit()) {
                Paddle.setPaddleHit(true);

                Texture temp = Assets.paletkaTexture;
                Assets.paletkaTexture = Assets.paletkaTexture2;
                Assets.paletkaTexture2 = temp;

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        Texture temp = Assets.paletkaTexture;
                        Assets.paletkaTexture = Assets.paletkaTexture2;
                        Assets.paletkaTexture2 = temp;

                        Paddle.setPaddleHit(false);
                    }
                }, 0.2f);
            }
        }
    }

    public static void moveLeft() {
        if (PaddleX - PaddleSpeed > 20) {
            PaddleX -= PaddleSpeed;
        } else {
            PaddleX = 20;
        }
    }

    public static void moveRight() {
        if (PaddleX + PaddleSpeed < 460) {
            PaddleX += PaddleSpeed;
        } else {
            PaddleX = 460;
        }
    }

    public static boolean isPaddleHit() {
        return paddleHit;
    }

    public static void setPaddleHit(boolean paddleHit) {
        Paddle.paddleHit = paddleHit;
    }

    public static void setPaddleSpeed(float speed) {
        PaddleSpeed = speed;
    }

    public static float getPaddleY() {
        return PaddleY;
    }

    public static float getPaddleX() {
        return PaddleX;
    }

    public static float getPaddleSpeed(){
        return PaddleSpeed;
    }

    public static void setPaddleX(float PaddleX) {
        Paddle.PaddleX = PaddleX;
    }
}