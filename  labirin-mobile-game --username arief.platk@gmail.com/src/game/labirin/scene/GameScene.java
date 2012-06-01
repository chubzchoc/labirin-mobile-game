package game.labirin.scene;

import game.labirin.entity.AnalogControl;
import game.labirin.entity.Background;
import game.labirin.entity.Ball;
import game.labirin.entity.Finish;
import game.labirin.entity.Gold;
import game.labirin.entity.Hole;
import game.labirin.entity.Txt;
import game.labirin.entity.Wall;
import game.labirin.util.LabirinConstanta;

import java.io.IOException;
import java.util.ArrayList;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.Vector2Pool;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class GameScene implements LabirinConstanta,
								  SensorEventListener,
								  IUpdateHandler,
								  IAnalogOnScreenControlListener {
	
	private int score = 0;
	private int live = 0;
	
	private int levelId;
	private int startX;
	private int startY;
	private int finishX;
	private int finishY;
	
	private BaseGameActivity activity;
	
	private Scene mScene;
	private PhysicsWorld mPhysicsWorld;
	private SensorManager mSensorManager;
	
	private static final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(0, 0, 0);
	private FixtureDef mFixtureDef;
	private Body body;
	
	private Hole mHole;
	private Background mBackground;
	private Finish mFinish;
	private Ball mBall;
	private Gold mGold;
	private Wall mWall;
	private Txt mTxt;
	private AnalogControl mControl;
	
	public Music mLevelMusic;
	private Sound mKoinSound;
	private Sound mGagalSound;
	private Sound mSuksesSound;
	
	private boolean stopEnable = false;
	private boolean mSuksesEnable;
	
	private ArrayList<Gold> goldList = new ArrayList<Gold>();
	private ArrayList<Ball> liveList = new ArrayList<Ball>();
	private ArrayList<Hole> holeList = new ArrayList<Hole>();
	
	private BitmapTextureAtlas mTexture;
	private TextureRegion mReload;
	private TextureRegion mNextLevel;
	
	public GameScene(BaseGameActivity activity, Camera camera) {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		mTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mReload = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, activity, "fail_button.png", 0, 0);
		mNextLevel = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, activity, "ok_button.png", 64, 0);
		
		activity.getEngine().getTextureManager().loadTexture(mTexture);
		
		mHole = new Hole(activity);
		mBackground = new Background(activity);
		mFinish = new Finish(activity);
		mBall = new Ball(activity);
		mGold = new Gold(activity);
		mWall = new Wall(activity);
		mTxt = new Txt(activity);
		mControl = new AnalogControl(activity, camera, this);
		
		try
		{
			mLevelMusic = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, "sound/Labiri_Arena1 by Fandy.mp3");
			mLevelMusic.setLooping(true);
			mLevelMusic.setVolume(0.7f);
			mKoinSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sound/ambil koin.mp3");
			mKoinSound.setLooping(false);
			mGagalSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sound/masuk lubang (gagal).mp3");
			mKoinSound.setLooping(false);
			mSuksesSound = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "sound/Labirin_level clear(berhasil) by Rifandi Lihawa.mp3");
			mKoinSound.setLooping(false);
		}
		catch (final IOException ex)
		{
			Debug.e(ex);
		}
		
		this.activity = activity;
		
	}
	
	public Scene createNewGame(int levelId) {
		for(int i = (holeList.size() - 1); i >= 0 ; i--)
		{
			holeList.remove(i);
		}
		for(int i = (liveList.size() - 1); i >= 0 ; i--)
		{
			liveList.remove(i);
		}
		Log.e("holelist", "holelist = "+holeList.size());
		Log.e("livelist", "livelist = "+liveList.size());
		
		mSuksesEnable = false;
		mLevelMusic.play();
		
		if (live == 0) {
			setLive(3);
		}
		this.levelId = levelId;
		mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
		
		mScene = new Scene();
		
		mPhysicsWorld = new PhysicsWorld(new Vector2(0, 0), false);
		mFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
		
		for(int i = 0; i <= LAYER_COUNT; i++) {
			mScene.attachChild(new Entity());
		}
		
		addLive(live);
		loadLevel(levelId);
		
		Rectangle top = new Rectangle(0, 0, CAMERA_WIDTH, 20);
		Rectangle left = new Rectangle(0, 0, 1, CAMERA_HEIGHT);
		Rectangle botton = new Rectangle(0, CAMERA_HEIGHT - 1, CAMERA_WIDTH, 1);
		Rectangle right = new Rectangle(CAMERA_WIDTH - 1, 0, 1, CAMERA_HEIGHT);

		PhysicsFactory.createBoxBody(mPhysicsWorld, top, BodyType.StaticBody, mFixtureDef);
		PhysicsFactory.createBoxBody(mPhysicsWorld, left, BodyType.StaticBody, mFixtureDef);
		PhysicsFactory.createBoxBody(mPhysicsWorld, botton, BodyType.StaticBody, mFixtureDef);
		PhysicsFactory.createBoxBody(mPhysicsWorld, right, BodyType.StaticBody, mFixtureDef);
		
		mScene.getChild(LAYER_WALL).attachChild(top);
		mScene.getChild(LAYER_WALL).attachChild(left);
		mScene.getChild(LAYER_WALL).attachChild(botton);
		mScene.getChild(LAYER_WALL).attachChild(right);
		
		mScene.registerUpdateHandler(mPhysicsWorld);
		mScene.registerUpdateHandler(this);
		mScene.setChildScene(mControl.createControl());
		mTxt.createScore(250, 1, mScene);
		mTxt.changeScore().setText("Score : " +score);
		
		return mScene;
		
	}

	public void onUpdate(float pSecondsElapsed) {
		
		removeGold();
		reborn();
		onFinish();
		stopSementara();
		
		
	}

	public void reset() {}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	public void onSensorChanged(SensorEvent event) {

		synchronized(this) {
			switch(event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				float gravityX = event.values[1];
				float gravityY =  event.values[0];
				Vector2 gravity = Vector2Pool.obtain(gravityX, gravityY);
				mPhysicsWorld.setGravity(gravity);
			}
		}
		
	}

	public void onControlChange(BaseOnScreenControl pBaseOnScreenControl,float pValueX, float pValueY) {
		mPhysicsWorld.setGravity(Vector2Pool.obtain(pValueX, pValueY));
	}

	public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {}
	
	private void createBall(int pX, int pY) {
		
		mBall.createBall(pX, pY, mScene);
		body = PhysicsFactory.createCircleBody(mPhysicsWorld, mBall.getSprite(), BodyType.DynamicBody, FIXTURE_DEF);
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mBall.getSprite(), body));
		
	}
	
	private void addGold(int pX, int pY) {
		
		mGold = new Gold(activity);
		mGold.addGold(pX, pY, mScene);
		mScene.getChild(LAYER_GOLD).attachChild(mGold);
		goldList.add(mGold);
		
	}
	
	private void removeGold() {
		
		for(int i = 0; i < goldList.size(); i++) {
			if(goldList.get(i).getSprite().collidesWith(mBall.getSprite())){
				mKoinSound.play();
				mScene.getChild(LAYER_GOLD).detachChild(goldList.get(i));
				goldList.remove(i);
				score+=10;
				mTxt.changeScore().setText("Score : " +score);
			}
		}
		
	}
	
	private void addLive(int live) {
		
		int x = -14;
		for(int i = 0; i < live; i++) {
			x+=17;
			mBall = new Ball(activity);
			mBall.addLive(x, 3);
			mScene.getChild(LAYER_BALL).attachChild(mBall);
			Log.e("livelist", "livelist = "+liveList.size());
			liveList.add(mBall);
			Log.e("livelist", "livelist = "+liveList.size());
		}
		
	}
	
	private void reborn() {
		for(int i = 0; i < holeList.size(); i++)
		{
			if(holeList.get(i).getSprite().collidesWith(mBall.getSprite()))
			{
				if(liveList.size() > 0)
				{
					mScene.getChild(LAYER_BALL).detachChild(liveList.get(liveList.size() - 1));
					liveList.remove(liveList.size() - 1);
					mGagalSound.play();
					if(liveList.size() <= 0) {
						Sprite sprite = new Sprite(130, 110, 60, 60, mReload) {
							@Override
							public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
									float pTouchAreaLocalX, float pTouchAreaLocalY) {
								activity.getEngine().setScene(createNewGame(levelId));
								return true;
							}
						};
						mScene.registerTouchArea(sprite);
						mScene.getChild(LAYER_COUNT).attachChild(sprite);
	//					angle = body.getAngle();
	//					v2 = Vector2Pool.obtain(0,0);
	//					body.setTransform(v2, angle);
	//					Vector2Pool.recycle(v2);
						body.setType(BodyType.StaticBody);
						score = 0;
					} 
					else 
					{
						body.setType(BodyType.StaticBody);
						final float width = mBall.getSprite().getWidthScaled() / 2;
						final float height = mBall.getSprite().getHeightScaled() / 2;
						float angle = body.getAngle();
						Vector2 v2 = Vector2Pool.obtain((startX + width) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, (startY + height) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
						body.setTransform(v2, angle);
						Vector2Pool.recycle(v2);
						stopEnable = true;
					}
					live = liveList.size();
					Log.e("livelist", "livelist = "+liveList.size());
				}
			}
		}
	}
	
	private void stopSementara()
	{
		if(stopEnable)
		{
			activity.getEngine().registerUpdateHandler(new TimerHandler(3, new ITimerCallback()
			{
				public void onTimePassed(TimerHandler pTimerHandler)
				{	
					body.setType(BodyType.DynamicBody);
					stopEnable = false;
				}
			}));
		}
	}
	
	private void onFinish() {
		
		if(mFinish.getSprite().collidesWith(mBall.getSprite()) && goldList.size() == 0) {
			if(!mSuksesEnable) {
				mSuksesSound.play();
				mSuksesEnable = true;
			}
			body.setType(BodyType.StaticBody);
//			Path path = new Path(3).to(203, 286).to(205, 2).to(203, 286);
			
			
//			Path path = new Path(2).to(mBall.getSprite().getX(), mBall.getSprite().getY()).to(finishX + 5, finishY + 5);
//			mBall.addPath(1, path);
//			Path path = new Path(2).to(finishX + 5, finishY + 5).to(finishX + 5, finishY + 5);
//			mBall.addPath(1, path);
			
//			body.setTransform(finishX - mBall.getSprite().getX(), mBall.getSprite().getY(), 0);
			
			if(levelId < 6) {
				Sprite sprite = new Sprite(130, 110, 60, 60, mNextLevel) {
					@Override
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						activity.getEngine().setScene(createNewGame(levelId+1));
						return true;
					}
				};
				mScene.registerTouchArea(sprite);
				mScene.getChild(LAYER_COUNT).attachChild(sprite);
			}
			
			if(levelId == 6) {
				mTxt.addTextWin(30, 107, mScene);
			}
		}
		
	}
	
	private void addHole(int pX, int pY) 
	{
		mHole = new Hole(activity);
		mHole.addHole(pX, pY, mScene);
		holeList.add(mHole);
	}
	
	private void addWall(int pX, int pY)
	{
		mWall.addWall(pX, pY, mScene, mPhysicsWorld, FIXTURE_DEF);
	}
	
	private void setLive(int x) 
	{
		live = x;
	}

	private void loadLevel(int levelId) {
		switch (levelId) {
		case 1:
			loadLevel1();
			break;
		case 2:
			loadLevel2();
			break;
		case 3:
			loadLevel3();
			break;
		case 4:
			loadLevel4();
			break;
		case 5:
			loadLevel5();
			break;
		case 6:
			loadLevel6();
			break;
		case 7:
			loadLevel7();
			break;
		case 8:
			loadLevel8();
			break;

		default:
			break;
		}		
	}

	private void loadLevel1() {
		
		mBackground.createBackground(0, 0, mScene);
		finishX = 289;
		finishY = 209;
		mFinish.createFinish(finishX, finishY, mScene);
		
		mWall.addWall(80, 60, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(80, 80, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(80, 100, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(80, 120, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(80, 140, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(80, 160, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(80, 180, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(220, 60, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(220, 80, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(220, 100, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(220, 120, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(220, 140, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(220, 160, mScene, mPhysicsWorld, FIXTURE_DEF);
		mWall.addWall(220, 180, mScene, mPhysicsWorld, FIXTURE_DEF);

		addGold(150, 90);
		addGold(150, 150);
		addGold(120, 120);
		addGold(180, 120);

		addHole(10, 205);
		addHole(280, 30);
		
		startX = 1;
		startY = 21;
		createBall(startX, startY);
	}
	
	private void loadLevel2() {
		mBackground.createBackground(0, 0, mScene);
		finishX = 145;
		finishY = 125;
		mFinish.createFinish(finishX, finishY, mScene);

		addWall(100, 80);
		addWall(100, 100);
		addWall(100, 120);
		addWall(100, 140);
		addWall(100, 160);
		addWall(120, 80);
		addWall(140, 80);
		addWall(160, 80);
		addWall(180, 80);
		addWall(200, 80);
		addWall(200, 100);
		addWall(200, 120);
		addWall(200, 140);
		addWall(200, 160);

		addGold(200, 30);
		addGold(230, 30);
		addGold(260, 30);
		addGold(290, 30);

		addHole(10, 205);
		addHole(280, 205);
		

		startX = 1;
		startY = 21;
		createBall(startX, startY);		
	}

	private void loadLevel3() {
		mBackground.createBackground(0, 0, mScene);
		finishX = 1;
		finishY = 21;
		mFinish.createFinish(finishX, finishY, mScene);

		addWall(80, 20);
		addWall(80, 40);
		addWall(80, 60);
		addWall(80, 80);
		addWall(80, 100);
		addWall(80, 120);
		addWall(80, 140);
		addWall(220, 220);
		addWall(220, 200);
		addWall(220, 180);
		addWall(220, 160);
		addWall(220, 140);
		addWall(220, 120);
		addWall(220, 100);

		addGold(270, 100);
		addGold(270, 130);
		addGold(270, 160);
		
		addHole(10, 205);
		addHole(265, 205);
		

		startX = 150;
		startY = 120;
		createBall(startX, startY);
	}

	private void loadLevel4() {
		mBackground.createBackground(0, 0, mScene);
		finishX = 125;
		finishY = 105;
		mFinish.createFinish(finishX, finishY, mScene);

		addWall(80, 20);
		addWall(80, 40);
		addWall(80, 60);
		addWall(80, 80);
		addWall(80, 100);
		addWall(80, 120);
		addWall(80, 140);
		addWall(80, 160);
		addWall(100, 160);
		addWall(120, 160);
		addWall(140, 160);
		addWall(160, 160);
		addWall(180, 160);
		addWall(180, 140);
		addWall(180, 120);
		addWall(180, 100);
		addWall(180, 80);
		addWall(200, 80);
		addWall(220, 80);
		addWall(240, 80);

		addGold(110, 30);
		addGold(140, 30);
		addGold(170, 30);
		addGold(200, 30);
		addGold(230, 30);
		addGold(30, 95);
		addGold(30, 125);
		
		addHole(220, 120);
		addHole(25, 30);
		
		startX = 299;
		startY = 219;
		createBall(startX, startY);
	}

	private void loadLevel5() {
		mBackground.createBackground(0, 0, mScene);
		mFinish.createFinish(1, 209, mScene);
		finishX = 1;
		finishY = 209;
		mFinish.createFinish(finishX, finishY, mScene);

		addWall(0, 80);
		addWall(20, 80);
		addWall(40, 80);
		addWall(60, 80);
		addWall(80, 80);
		addWall(100, 80);
		addWall(120, 80);
		addWall(140, 80);
		addWall(160, 80);
		addWall(180, 80);
		addWall(300, 160);
		addWall(280, 160);
		addWall(260, 160);
		addWall(240, 160);
		addWall(220, 160);

		addGold(70, 145);
		addGold(100, 145);
		addGold(130, 145);
		addGold(100, 175);
		addGold(70, 175);
		addGold(280, 200);
		addGold(240, 200);

		addHole(20, 120);
		addHole(280, 120);
		addHole(280, 30);

		startX = 1;
		startY = 21;
		createBall(startX, startY);
	}

	private void loadLevel6() {
		mBackground.createBackground(0, 0, mScene);
		mFinish.createFinish(1, 209, mScene);
		finishX = 1;
		finishY = 209;
		mFinish.createFinish(finishX, finishY, mScene);

		addWall(0, 80);
		addWall(20, 80);
		addWall(40, 80);
		addWall(60, 80);
		addWall(80, 80);
		addWall(100, 80);
		addWall(120, 80);
		addWall(140, 80);
		addWall(160, 80);
		addWall(180, 80);
		addWall(200, 80);
		addWall(220, 80);
		addWall(220, 100);
		addWall(220, 120);
		addWall(220, 140);
		addWall(220, 160);
		addWall(200, 160);
		addWall(180, 160);
		addWall(160, 160);
		addWall(80, 220);
		addWall(80, 200);
		addWall(80, 180);
		addWall(80, 160);

		addGold(105, 40);
		addGold(135, 40);
		addGold(165, 40);
		addGold(195, 40);
		
		addHole(289, 20);

		startX = 1;
		startY = 21;
		createBall(startX, startY);
	}

	private void loadLevel7() {
		mBackground.createBackground(0, 0, mScene);
		mFinish.createFinish(145, 220, mScene);
		
		addWall(48, 20);
		addWall(48, 40);
		addWall(48, 60);
		addWall(48, 80);
		addWall(48, 100);
		addWall(48, 120);
		addWall(48, 140);
		addWall(116, 220);
		addWall(116, 200);
		addWall(116, 180);
		addWall(116, 160);
		addWall(116, 140);
		addWall(116, 120);
		addWall(116, 100);
		addWall(136, 100);
		addWall(156, 100);
		addWall(176, 100);
		addWall(196, 100);
		addWall(216, 100);
		addWall(236, 100);
		addWall(256, 100);
		
		addGold(23, 23);
		
		addHole(230, 230);
		
		startX = 1;
		startY = 21;
		createBall(startX, startY);
	}

	private void loadLevel8() {
		
	}
	
}
