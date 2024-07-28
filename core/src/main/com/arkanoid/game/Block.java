package com.arkanoid.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Block {

    private Array<Rectangle> blocks;

    private static int numberOfRows;

    private static int numberOfColumns;

    public static final float blockWidth = 55;

    public static final float blockHeight = 20;

    public static final float startX = (float) GameState.SCREEN_WIDTH / 2 - 245;

    public static final float startY = (float) GameState.SCREEN_HEIGHT / 2 + 200;

    public Block(int level) {
        blocks = new Array<>();
        createBlocks(level);
    }

    public void drawBlocks(SpriteBatch batch) {
        for (Rectangle block : blocks) {
            if (block != null) {
                Assets.batch.draw(Assets.KwadratTexture, block.x, block.y, block.width, block.height);
            }
        }
    }

    private void createBlocks(int level) {
        float blockWidth = Block.blockWidth;
        float blockHeight = Block.blockHeight;

        Block.setNumberOfRows((level == 1) ? 3 : 4);
        Block.setNumberOfColumns(9);

        for (int i = 0; i < Block.getNumberOfRows(); i++) {
            for (int j = 0; j < Block.getNumberOfColumns(); j++) {
                float blockX = Block.startX + j * (blockWidth);
                float blockY = Block.startY - i * (blockHeight);

                Rectangle block = new Rectangle(blockX, blockY, blockWidth, blockHeight);
                blocks.add(block);
            }
        }
    }

    public void handleCollisionWithBall() {
        Iterator<Rectangle> iterator = blocks.iterator();
        while (iterator.hasNext()) {
            Rectangle block = iterator.next();
            if (Ball.getBallY() < block.y + block.height &&
                    Ball.getBallY() + Ball.BallHeight > block.y &&
                    Ball.getBallX() < block.x + block.width &&
                    Ball.getBallX() + Ball.BallWidth > block.x) {

                GameState.setScore(GameState.getScore() + 10);
                iterator.remove();


                Ball.setBallDirectionY(Ball.getBallDirectionY() * -1);
                break;
            }
        }
    }

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static void setNumberOfRows(int numberOfRows) {
        Block.numberOfRows = numberOfRows;
    }

    public static int getNumberOfColumns() {
        return numberOfColumns;
    }

    public static void setNumberOfColumns(int numberOfColumns) {
        Block.numberOfColumns = numberOfColumns;
    }

    public Array<Rectangle> getBlocks() {
        return blocks;
    }
}
