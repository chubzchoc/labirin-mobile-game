package game.labirin.scene;

import java.io.IOException;

import game.labirin.Base;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.Debug;

import android.content.Intent;
import android.view.KeyEvent;

public class LevelScene extends Base{

	private GameScene mGameMenu;
	
	private BitmapTextureAtlas mTextureMenuLevel1;
	private BitmapTextureAtlas mTextureButtonLevel1;
	
	private TextureRegion mTextureRegionMenuLevel1;
	private TextureRegion mTextureRegionButtonLevel1;
	private TextureRegion mTextureRegionButtonLevel2;
	private TextureRegion mTextureRegionButtonLevel3;
	private TextureRegion mTextureRegionButtonLevel4;
	private TextureRegion mTextureRegionButtonLevel5;
	private TextureRegion mTextureRegionButtonLevel6;
	private TextureRegion mTextureRegionButtonLevel7;
	private TextureRegion mTextureRegionButtonLevel8;
	private TextureRegion mTextureRegionArrow1;
	private TextureRegion mTextureRegionArrow2;

	private Music mMainMusic;
	
	public void onLoadResources() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		this.mTextureMenuLevel1 = new BitmapTextureAtlas(512, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionMenuLevel1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureMenuLevel1, this, "level_back.png", 0, 0);
		this.mTextureButtonLevel1 = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionButtonLevel1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level1.png", 0, 0);
		this.mTextureRegionButtonLevel2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level2.png", 64, 64);
		this.mTextureRegionButtonLevel3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level3.png", 128, 0);
		this.mTextureRegionButtonLevel4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level4.png", 192, 0);
		this.mTextureRegionButtonLevel5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level5.png", 0, 128);
		this.mTextureRegionButtonLevel6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level6.png", 64, 128);
		this.mTextureRegionButtonLevel7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level7.png", 128, 128);
		this.mTextureRegionButtonLevel8 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level8.png", 192, 128);
		this.mTextureRegionArrow1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "left.png", 0, 192);
		this.mTextureRegionArrow2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "right.png", 192, 192);

		this.mEngine.getTextureManager().loadTextures(this.mTextureMenuLevel1, this.mTextureButtonLevel1);
		
		mGameMenu = new GameScene(this, mCamera);
		
		try{
			mMainMusic = MusicFactory.createMusicFromAsset(getMusicManager(), this, "sound/Labirin_Main Theme by Fandy (pressed).mp3");
			mMainMusic.setLooping(true);
		} catch (final IOException ex) {
			Debug.e(ex);
		}
	
	}

	public Scene onLoadScene() {
		
		mMainMusic.play();
		Scene mLevelMenuScene = new Scene();

		Sprite sprBackLevel1 = new Sprite(0, 0, this.mTextureRegionMenuLevel1);
		Sprite sprButtonLevel1 = new Sprite(15, 20,
				this.mTextureRegionButtonLevel1) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
//				startActivity(new Intent(LevelMenu.this, GameMenu.class));
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(1));
				return true;
			}
		};
		Sprite sprButtonLevel2 = new Sprite(92, 20,
				this.mTextureRegionButtonLevel2) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
//				startActivity(new Intent(LevelMenu.this, GameMenu.class));
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(2));
				return true;
			}
		};
		Sprite sprButtonLevel3 = new Sprite(166, 20,
				this.mTextureRegionButtonLevel3) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(3));
				return true;
			}
		};
		Sprite sprButtonLevel4 = new Sprite(240, 20,
				this.mTextureRegionButtonLevel4) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(4));

				return true;
			}
		};
		Sprite sprButtonLevel5 = new Sprite(15, 99,
				this.mTextureRegionButtonLevel5) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(5));

				return true;
			}
		};
		Sprite sprButtonLevel6 = new Sprite(92, 99,
				this.mTextureRegionButtonLevel6) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(6));

				return true;
			}
		};
		Sprite sprButtonLevel7 = new Sprite(166, 99,
				this.mTextureRegionButtonLevel7) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(7));

				return true;
			}
		};
		Sprite sprButtonLevel8 = new Sprite(240, 99,
				this.mTextureRegionButtonLevel8) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mMainMusic.stop();
				mEngine.setScene(mGameMenu.createNewGame(8));

				return true;
			}
		};
		Sprite sprButtonArrow = new Sprite(15, 170, this.mTextureRegionArrow1) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonArrow1 = new Sprite(240, 170, this.mTextureRegionArrow2) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};

		mLevelMenuScene.registerTouchArea(sprButtonArrow);
		mLevelMenuScene.registerTouchArea(sprButtonArrow1);

		mLevelMenuScene.registerTouchArea(sprButtonLevel1);
		mLevelMenuScene.registerTouchArea(sprButtonLevel2);
		mLevelMenuScene.registerTouchArea(sprButtonLevel3);
		mLevelMenuScene.registerTouchArea(sprButtonLevel4);
		mLevelMenuScene.registerTouchArea(sprButtonLevel5);
		mLevelMenuScene.registerTouchArea(sprButtonLevel6);
		mLevelMenuScene.registerTouchArea(sprButtonLevel7);
		mLevelMenuScene.registerTouchArea(sprButtonLevel8);
		mLevelMenuScene.attachChild(sprBackLevel1);
		mLevelMenuScene.attachChild(sprButtonLevel1);
		mLevelMenuScene.attachChild(sprButtonLevel2);
		mLevelMenuScene.attachChild(sprButtonLevel3);
		mLevelMenuScene.attachChild(sprButtonLevel4);
		mLevelMenuScene.attachChild(sprButtonLevel5);
		mLevelMenuScene.attachChild(sprButtonLevel6);
		mLevelMenuScene.attachChild(sprButtonLevel7);
		mLevelMenuScene.attachChild(sprButtonLevel8);
		mLevelMenuScene.attachChild(sprButtonArrow);
		mLevelMenuScene.attachChild(sprButtonArrow1);

		return mLevelMenuScene;
	}
	
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
        if(pKeyCode == KeyEvent.KEYCODE_BACK && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
        	finish();
        	startActivity(new Intent(LevelScene.this, HomeScene.class));
        }
    	return true;
	}
}
