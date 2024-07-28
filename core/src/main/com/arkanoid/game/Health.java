package com.arkanoid.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Health {

    private static float heartX;

    private static float heartY;

    private static int healthCounter = 3;

    public static void drawLives(SpriteBatch batch, int numberOfLives) {
        for (int i = 0; i < numberOfLives; i++) {
            heartX = (190 + i * 40);
            heartY = -5;
            batch.draw(Assets.heartTexture, heartX, heartY, 40, 40);
        }
    }

    public static int getHealthCounter() { return healthCounter; }

    public static void setHealthCounter(int healthCounter) {
        Health.healthCounter = healthCounter;
    }
}
