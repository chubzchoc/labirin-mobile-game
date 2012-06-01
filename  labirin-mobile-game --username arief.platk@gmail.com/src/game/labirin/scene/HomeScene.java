package game.labirin.scene;

import game.labirin.Base;

import java.io.IOException;

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

public class HomeScene extends Base{
	
	private BitmapTextureAtlas mTextureBack;
	private BitmapTextureAtlas mTextureButton;

	private TextureRegion mTextureRegion;
	private TextureRegion mTextureRegionMenuPlay;
	private TextureRegion mTextureRegionMenuAbout;
	private TextureRegion mTextureRegionMenuSetting;
	private TextureRegion mTextureRegionMenuHelp;
	
	public Music mMainMusic;
	
	private HelpScene mHelpScene;
	private CreditsScene mCreditsScene;
	
	public void onLoadResources() {
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		this.mTextureBack = new BitmapTextureAtlas(512, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureBack, this, "background.png", 0, 0);

		this.mTextureButton = new BitmapTextureAtlas(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionMenuPlay = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "btn_play.png", 0, 0);
		this.mTextureRegionMenuAbout = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "btn_credits.png", 100, 0);
		this.mTextureRegionMenuSetting = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "btn_sound.png", 100, 50);
		this.mTextureRegionMenuHelp = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "btn_help.png", 150, 0);
		
		this.mEngine.getTextureManager().loadTextures(this.mTextureBack,
						this.mTextureButton);
		
		try{
			mMainMusic = MusicFactory.createMusicFromAsset(getMusicManager(), this, "sound/Labirin_Main Theme by Fandy (pressed).mp3");
			mMainMusic.setLooping(true);
		} catch (final IOException ex) {
			Debug.e(ex);
		}
		
		mHelpScene = new HelpScene(this);
		mCreditsScene = new CreditsScene(this);
		
	}

	public Scene onLoadScene() {
		
		Scene mScene = new Scene();

		Sprite sprBack = new Sprite(0, 0, this.mTextureRegion);
		Sprite sprButtonPlay = new Sprite(115, 60, this.mTextureRegionMenuPlay) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				startActivity(new Intent(HomeScene.this,
						LevelScene.class));
				finish();
//				mMainMusic.stop();
				return true;
			}
		};
		Sprite sprButtonHelp = new Sprite(110, 10, 40, 40,
				this.mTextureRegionMenuHelp) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
//				startActivity(new Intent(HomeScene.this,
//						HelpScene.class));
				mEngine.setScene(mHelpScene.onLoadScene());
				return true;
			}
		};
		Sprite sprButtonSeting = new Sprite(60, 10, 40, 40,
				this.mTextureRegionMenuSetting) {
		};
		Sprite sprButtonCredits = new Sprite(10, 10, 40, 40,
				this.mTextureRegionMenuAbout) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
//				startActivity(new Intent(HomeScene.this,
//						CreditsScene.class));
				mEngine.setScene(mCreditsScene.onLoadScene());
				return true;
			}
		};
		
		mScene.registerTouchArea(sprButtonCredits);
		mScene.registerTouchArea(sprButtonPlay);
		mScene.registerTouchArea(sprButtonHelp);
		mScene.attachChild(sprBack);
		mScene.attachChild(sprButtonPlay);
		mScene.attachChild(sprButtonCredits);
		mScene.attachChild(sprButtonSeting);
		mScene.attachChild(sprButtonHelp);
		
		mMainMusic.play();

		return mScene;
	}
	
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
        if(pKeyCode == KeyEvent.KEYCODE_BACK && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
        	if(mHelpScene.helpSceneEnable) {
        		mEngine.setScene(onLoadScene());
        		mHelpScene.helpSceneEnable = false;
        	} else if(mCreditsScene.creditsSceneEnable) {
        		mEngine.setScene(onLoadScene());
        		mCreditsScene.creditsSceneEnable = false;
        	} else {
        		finish();
        	}
        }
    	return true;
	}
    
}