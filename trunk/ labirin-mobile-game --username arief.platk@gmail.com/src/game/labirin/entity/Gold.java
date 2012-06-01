package game.labirin.entity;

import game.labirin.util.LabirinConstanta;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Gold extends Entity implements LabirinConstanta{
	private AnimatedSprite mSprite;
	private TiledTextureRegion mRegion;
	
	
	public Gold() {}
	
	public Gold(BaseGameActivity pActivity) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		BitmapTextureAtlas texture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texture, pActivity, "sprite_koin.png", 0, 0, 5, 1);
		pActivity.getEngine().getTextureManager().loadTexture(texture);
	}

	public void addGold(int x, int y, Scene scene) {
		mSprite = new AnimatedSprite(x, y, 15, 20, mRegion);
		mSprite.animate(500);
//		scene.getChild(LAYER_COLLECTION).attachChild(mSprite);
		attachChild(mSprite);
	}
	
	public AnimatedSprite getSprite() {
		return mSprite;
	}
}
