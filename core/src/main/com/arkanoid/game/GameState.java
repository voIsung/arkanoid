package com.arkanoid.game;

public class GameState {

    private static boolean running = false;

    private static boolean newGameVisible = true;

    private static boolean wLewo = false;

    private static boolean wPrawo = false;

    private static boolean spaceVisible = true;

    private static boolean levelComplete = false;

    private static boolean winner = false;

    private static boolean loser = false;

    private static int score = 0;

    public static final int SCREEN_WIDTH = 573;

    public static final int SCREEN_HEIGHT = 614;

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        GameState.running = running;
    }

    public static boolean isNewGameVisible() {
        return newGameVisible;
    }

    public static void setNewGameVisible(boolean newGameVisible) {
        GameState.newGameVisible = newGameVisible;
    }

    public static boolean isWLewo() {
        return wLewo;
    }

    public static void setWLewo(boolean wLewo) {
        GameState.wLewo = wLewo;
    }

    public static boolean isWPrawo() {
        return wPrawo;
    }

    public static void setWPrawo(boolean wPrawo) {
        GameState.wPrawo = wPrawo;
    }

    public static boolean isSpaceVisible() {
        return spaceVisible;
    }

    public static void setSpaceVisible(boolean spaceVisible) {
        GameState.spaceVisible = spaceVisible;
    }

    public static boolean isLevelComplete() {
        return levelComplete;
    }

    public static void setLevelComplete(boolean levelComplete) {
        GameState.levelComplete = levelComplete;
    }

    public static boolean isWinner() {
        return winner;
    }

    public static void setWinner(boolean winner) {
        GameState.winner = winner;
    }

    public static boolean isLoser() {
        return loser;
    }

    public static void setLoser(boolean loser) {
        GameState.loser = loser;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameState.score = score;
    }
}
