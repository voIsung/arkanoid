package com.arkanoid.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;



public class Assets implements Disposable{


    public static Texture bg;

    public static Texture bg2;

    public static Texture paletkaTexture;

    public static Texture paletkaTexture2;

    public static Texture pilkaTexture;

    public static Texture KwadratTexture;

    public static Texture MusicOn;

    public static Texture MusicOff;

    public static Music music;

    public static SpriteBatch batch;

    public static BitmapFont font;

    public static BitmapFont font2;

    public static Pixmap pm;

    public static Texture heartTexture;

    public static float initialSize = 1.0f;

    public static void load() {

        // Textures
        bg = new Texture("images/background.png");
        bg2 = new Texture("images/background2.png");
        paletkaTexture = new Texture("images/paddle1.png");
        paletkaTexture2 = new Texture("images/paddle2.png");
        pilkaTexture = new Texture("images/ball.png");
        KwadratTexture = new Texture("images/rectangle.png");
        MusicOn = new Texture("images/MusicOn.png");
        MusicOff = new Texture("images/MusicOff.png");
        heartTexture = new Texture("images/health.png");
        pm = new Pixmap(Gdx.files.internal("images/cursor.png"));

        // Font
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("font2.fnt"));

        // Music
        music = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));
    }


    @Override
    public void dispose() {
        bg.dispose();
        bg2.dispose();
        paletkaTexture.dispose();
        pilkaTexture.dispose();
        KwadratTexture.dispose();
        MusicOn.dispose();
        MusicOff.dispose();
        music.dispose();
        pm.dispose();
        batch.dispose();
        font.dispose();
        heartTexture.dispose();
    }
}