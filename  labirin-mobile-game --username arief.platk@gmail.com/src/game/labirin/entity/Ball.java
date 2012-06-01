package game.labirin.entity;

import game.labirin.util.LabirinConstanta;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Ball extends Entity implements LabirinConstanta{
	
	private AnimatedSprite mSprite;
	private BitmapTextureAtlas mTexture;
	private TiledTextureRegion mRegion;
	public PhysicsHandler mHandler;

	public Ball(BaseGameActivity pActivity) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		mTexture = new BitmapTextureAtlas(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mTexture, pActivity, "sprite_bola.png", 0, 0, 2, 1);
		pActivity.getEngine().getTextureManager().loadTexture(mTexture);
	}

	public void createBall(int x, int y, Scene scene) {
		mSprite = new AnimatedSprite(x, y, 20, 20, mRegion);
		mHandler = new PhysicsHandler(mSprite);
		mSprite.registerUpdateHandler(mHandler);
		mSprite.animate(200);
		scene.getChild(LAYER_BALL).attachChild(mSprite);
	}
	
	public void setPosition(float x, float y) {
		mSprite.setPosition(x, y);
	}
	
	public void addLive(int x, int y) {
		AnimatedSprite live = new AnimatedSprite(x, y, 14, 14, mRegion);
		live.animate(200);
		attachChild(live);
	}
	
	public AnimatedSprite getSprite() {
		return mSprite;
	}
	
	public void addPath (float pDuration, Path pPath) {
		registerEntityModifier(new LoopEntityModifier(new PathModifier(pDuration, pPath)));
	}
}
