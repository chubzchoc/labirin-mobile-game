package com.labirin.arief;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;



public class Menu extends BaseGameActivity {
	private Camera mCamera;
	// MENU UTAMA //
  private Texture mTextureBack;
	private Texture mTextureButton;
	private Texture mTextureHelp;
	private Texture mTextureAbout;
	 
    // MUNU LEVEL //
  private Texture mTextureMenuLevel1;
	private Texture mTextureButtonLevel1;

	// MENU UTAMA //
	private TextureRegion mTextureRegion;
	private TextureRegion mTextureRegionMenuPlay;
	private TextureRegion mTextureRegionMenuAbout;
	private TextureRegion mTextureRegionMenuSetting;
	private TextureRegion mTextureRegionMenuHelp;
	private TextureRegion mTextureRegionHelp;
	private TextureRegion mTextureRegionAbout;

	// MUNU LEVEL //
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
	private final int CAMERA_WIDTH = 320;
	private final int CAMERA_HEIGHT = 240;

	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

	public Engine onLoadEngine() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera));
	}

	public void onLoadResources() {
		// TODO Auto-generated method stub

		// MUNU UTAMA //

		this.mTextureBack = new Texture(2048, 2048,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegion = TextureRegionFactory.createFromAsset(
				this.mTextureBack, this, "gambar/back_menu.png", 0, 0);

		this.mTextureButton = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionMenuPlay = TextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "gambar/button_play.png", 100, 100);
		this.mTextureRegionMenuAbout = TextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "gambar/button_about.png", 100, 50);
		this.mTextureRegionMenuSetting = TextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "gambar/button_setting.png", 50, 50);
		this.mTextureRegionMenuHelp = TextureRegionFactory.createFromAsset(
				this.mTextureButton, this, "gambar/button_help.png", 170, 50);

		this.mTextureHelp = new Texture(2048, 2048,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionHelp = TextureRegionFactory.createFromAsset(
				this.mTextureHelp, this, "gambar/help.png", 0, 0);

		this.mTextureAbout = new Texture(2048, 2048, TextureOptions.BILINEAR);
		this.mTextureRegionAbout = TextureRegionFactory.createFromAsset(
				this.mTextureAbout, this, "gambar/about.png", 0, 0);
		
		
		// MENU LEVEL //
		
		this.mTextureMenuLevel1 = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionMenuLevel1 = TextureRegionFactory.createFromAsset(
				this.mTextureMenuLevel1, this, "level/level_back.png", 0, 0);
		this.mTextureButtonLevel1 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionButtonLevel1 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level1.png", 0, 0);
		this.mTextureRegionButtonLevel2 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level2.png", 64, 64);
		this.mTextureRegionButtonLevel3 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level3.png", 128, 0);
		this.mTextureRegionButtonLevel4 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level4.png", 192, 0);
		this.mTextureRegionButtonLevel5 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level5.png", 0, 128);
		this.mTextureRegionButtonLevel6 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level6.png", 64, 128);
		this.mTextureRegionButtonLevel7 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level7.png", 128, 128);
		this.mTextureRegionButtonLevel8 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/level8.png", 192, 128);
		this.mTextureRegionArrow1 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/panah.png", 0, 192);
		this.mTextureRegionArrow2 = TextureRegionFactory.createFromAsset(
				this.mTextureButtonLevel1, this, "level/panah2.png", 192, 192);

		this.mEngine.getTextureManager().loadTextures(this.mTextureBack,
				this.mTextureButton, this.mTextureHelp, this.mTextureAbout,
				this.mTextureMenuLevel1, this.mTextureButtonLevel1);
	}

	public Scene onLoadScene() {

		createMenuScene();

		return createMenuScene();
	}

	protected Scene createMenuScene() {

		Scene mScene = new Scene(1);

		Sprite sprBack = new Sprite(0, 0, this.mTextureRegion);
		Sprite sprButtonPlay = new Sprite(115, 60, this.mTextureRegionMenuPlay) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mEngine.setScene(createScenePlay());
				return true;
			}
		};

		Sprite sprButtonHelp = new Sprite(110, 10, 40, 40,
				this.mTextureRegionMenuHelp) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mEngine.setScene(createSceneHelp());
				return true;
			}
		};

		Sprite sprButtonSeting = new Sprite(60, 10, 40, 40,
				this.mTextureRegionMenuSetting) {
		};
		Sprite sprButtonAbout = new Sprite(10, 10, 40, 40,
				this.mTextureRegionMenuAbout) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				mEngine.setScene(createSceneAbout());
				return true;

			}

		};
		mScene.registerTouchArea(sprButtonAbout);
		mScene.registerTouchArea(sprButtonPlay);
		mScene.registerTouchArea(sprButtonHelp);
		mScene.getLastChild().attachChild(sprBack);
		mScene.getLastChild().attachChild(sprButtonPlay);
		mScene.getLastChild().attachChild(sprButtonAbout);
		mScene.getLastChild().attachChild(sprButtonSeting);
		mScene.getLastChild().attachChild(sprButtonHelp);

		return mScene;
	}

	private Scene createSceneHelp() {
		Scene mSceneHelp = new Scene(2);

		Sprite sprBackHelp = new Sprite(0, 0, this.mTextureRegionHelp);
		mSceneHelp.getLastChild().attachChild(sprBackHelp);
		return mSceneHelp;
	}

	private Scene createScenePlay() {
		Scene mScenePlay = new Scene(2);

		Sprite sprBackLevel1 = new Sprite(0, 0, this.mTextureRegionMenuLevel1);
		Sprite sprButtonLevel1 = new Sprite(15, 20,
				this.mTextureRegionButtonLevel1) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonLevel2 = new Sprite(92, 20,
				this.mTextureRegionButtonLevel2) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonLevel3 = new Sprite(166, 20,
				this.mTextureRegionButtonLevel3) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonLevel4 = new Sprite(240, 20,
				this.mTextureRegionButtonLevel4) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonLevel5 = new Sprite(15, 99,
				this.mTextureRegionButtonLevel5) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonLevel6 = new Sprite(92, 99,
				this.mTextureRegionButtonLevel6) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonLevel7 = new Sprite(166, 99,
				this.mTextureRegionButtonLevel7) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				return true;
			}
		};
		Sprite sprButtonLevel8 = new Sprite(240, 99,
				this.mTextureRegionButtonLevel8) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

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

		mScenePlay.registerTouchArea(sprButtonArrow);
		mScenePlay.registerTouchArea(sprButtonArrow1);

		mScenePlay.registerTouchArea(sprButtonLevel1);
		mScenePlay.registerTouchArea(sprButtonLevel2);
		mScenePlay.registerTouchArea(sprButtonLevel3);
		mScenePlay.registerTouchArea(sprButtonLevel4);
		mScenePlay.registerTouchArea(sprButtonLevel5);
		mScenePlay.registerTouchArea(sprButtonLevel6);
		mScenePlay.registerTouchArea(sprButtonLevel7);
		mScenePlay.registerTouchArea(sprButtonLevel8);
		mScenePlay.getLastChild().attachChild(sprBackLevel1);
		mScenePlay.getLastChild().attachChild(sprButtonLevel1);
		mScenePlay.getLastChild().attachChild(sprButtonLevel2);
		mScenePlay.getLastChild().attachChild(sprButtonLevel3);
		mScenePlay.getLastChild().attachChild(sprButtonLevel4);
		mScenePlay.getLastChild().attachChild(sprButtonLevel5);
		mScenePlay.getLastChild().attachChild(sprButtonLevel6);
		mScenePlay.getLastChild().attachChild(sprButtonLevel7);
		mScenePlay.getLastChild().attachChild(sprButtonLevel8);
		mScenePlay.getLastChild().attachChild(sprButtonArrow);
		mScenePlay.getLastChild().attachChild(sprButtonArrow1);

		return mScenePlay;
	}

	private Scene createSceneAbout() {
		Scene mSceneAbout = new Scene(2);

		Sprite sprBackAbout = new Sprite(0, 0, this.mTextureRegionAbout);
		mSceneAbout.getLastChild().attachChild(sprBackAbout);
		return mSceneAbout;
	}
}

