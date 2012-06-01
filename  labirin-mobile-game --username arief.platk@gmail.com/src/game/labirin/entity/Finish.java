package game.labirin.entity;

import game.labirin.util.LabirinConstanta;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Finish implements LabirinConstanta{
	private AnimatedSprite mSprite;
	private TiledTextureRegion mRegion;
	private Rectangle mRectangle;
	
	public Finish(BaseGameActivity pActivity) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		BitmapTextureAtlas texture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texture, pActivity, "sprite_finish.png", 0, 0, 2, 1);
		pActivity.getEngine().getTextureManager().loadTexture(texture);
	}
	
	public void createFinish(float x, float y, Scene scene) {
		mSprite = new AnimatedSprite(x, y, 30, 30, mRegion);
		mRectangle = new Rectangle(x + 10, y + 10, 10, 10);
		mSprite.animate(500);
		scene.getChild(LAYER_WALL).attachChild(mRectangle);
		scene.getChild(LAYER_WALL).attachChild(mSprite);
	}
	
	public Rectangle getSprite() {
		return mRectangle;
	}
}