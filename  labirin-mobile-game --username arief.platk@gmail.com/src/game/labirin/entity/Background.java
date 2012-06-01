package game.labirin.entity;

import game.labirin.util.LabirinConstanta;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Background implements LabirinConstanta{
	private TextureRegion mRegion;
	
	public Background(BaseGameActivity pActivity) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		BitmapTextureAtlas texture = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texture, pActivity, "background_parket_big.png", 0, 0);
		pActivity.getEngine().getTextureManager().loadTexture(texture);
	}
	
	public void createBackground(int x, int y, Scene scene) {
		Sprite sprite = new Sprite(x, y, CAMERA_WIDTH, CAMERA_HEIGHT, mRegion);
		scene.getChild(LAYER_BACKGROUND).attachChild(sprite);
	}
}