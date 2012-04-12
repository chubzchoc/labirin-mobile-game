public class Menu extends BaseGameActivity {
	private Camera mCamera;
	private Texture mTextureBack;
	private Texture mTextureButton;
	private Texture mTextureHelp;
	private Texture mTextureAbout;
	private TextureRegion mTextureRegion;
	private TextureRegion mTextureRegionMenuPlay;
	private TextureRegion mTextureRegionMenuAbout;
	private TextureRegion mTextureRegionMenuSetting;
	private TextureRegion mTextureRegionMenuHelp;
	private TextureRegion mTextureRegionHelp;
	private TextureRegion mTextureRegionAbout;
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

		this.mTextureAbout = new Texture(2048, 2048,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mTextureRegionAbout = TextureRegionFactory.createFromAsset(
				this.mTextureAbout, this, "gambar/about.png", 0, 0);
		this.mEngine.getTextureManager().loadTextures(this.mTextureBack,
				this.mTextureButton, this.mTextureHelp, this.mTextureAbout);
	}

	public Scene onLoadScene() {

		createMenuScene();

		return createMenuScene();
	}

	protected Scene createMenuScene() {

		Scene mScene = new Scene(1);

		Sprite sprBack = new Sprite(0, 0, this.mTextureRegion);
		Sprite sprButtonPlay = new Sprite(115, 60, this.mTextureRegionMenuPlay) {

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

	private Scene createSceneAbout() {
		Scene mSceneAbout = new Scene(2);

		Sprite sprBackAbout = new Sprite(0, 0, this.mTextureRegionAbout);
		mSceneAbout.getLastChild().attachChild(sprBackAbout);
		return mSceneAbout;
	}
}