package com.arkanoid.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class DesktopLauncher {


	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Arkanoid");
		config.setWindowedMode(GameState.SCREEN_WIDTH, GameState.SCREEN_HEIGHT);
		config.setWindowIcon("images/icon32.png");
		config.setResizable(false);
		new Lwjgl3Application(new Arkanoid(), config);
	}
}