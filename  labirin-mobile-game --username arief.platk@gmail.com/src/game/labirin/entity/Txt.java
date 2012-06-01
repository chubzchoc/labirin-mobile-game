package game.labirin.entity;

import game.labirin.util.LabirinConstanta;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.HorizontalAlign;

import android.graphics.Color;

public class Txt implements LabirinConstanta{
	private ChangeableText mScore;
	private Font mFontA;
	private Font mFontB;
	private Font mFontP;
	private Text mText;
	
	public Txt(BaseGameActivity pActivity) {
		BitmapTextureAtlas textureA = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlas textureB = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlas textureP = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mFontA = FontFactory.createFromAsset(textureA, pActivity, "font/ARBERKLEY.TTF", 14, true, Color.BLACK);
		mFontB = FontFactory.createFromAsset(textureB, pActivity, "font/ARBERKLEY.TTF", 34, true, Color.BLACK);
		mFontP = FontFactory.createFromAsset(textureP, pActivity, "font/Plok.ttf", 20, true, Color.WHITE);
		pActivity.getEngine().getTextureManager().loadTextures(textureA, textureB, textureP);
		pActivity.getEngine().getFontManager().loadFonts(mFontA, mFontB, mFontP);
	}

	public void createScore(int x, int y, Scene scene) {
		mScore = new ChangeableText(x, y, mFontA, "Score : 0", "Score : XXXX".length());
		scene.getChild(LAYER_SCORE).attachChild(mScore);
	}
	
	public ChangeableText changeScore() {
		return mScore;
	}

	public void createX(int x, int y, Scene scene) {
		mScore = new ChangeableText(x, y, mFontA, "0", "XXXX".length());
		scene.getChild(LAYER_SCORE).attachChild(mScore);
	}
	
	public ChangeableText changeX() {
		return mScore;
	}
	
	public void addTextGameOver(float pX, float pY, Scene scene) {
		mText = new Text(pX, pY, mFontP, "Game\nOver");
		mText.setColor(0, 1, 0);
		scene.getChild(LAYER_SCORE).attachChild(mText);
	}
	
	public void addTextWin(float pX, float pY, Scene scene) {
		mText = new Text(pX, pY, mFontP, "Terima Kasih\nTelah Mencoba", HorizontalAlign.CENTER);
		mText.setColor(0, 1, 0);
		scene.getChild(LAYER_SCORE).attachChild(mText);		
	}
	
	public void addNextLevel(float pX, float pY, Scene scene) {
		mText = new Text(pX, pY, mFontB, "Next Level");
		scene.getChild(LAYER_SCORE).attachChild(mText);
	}
}
