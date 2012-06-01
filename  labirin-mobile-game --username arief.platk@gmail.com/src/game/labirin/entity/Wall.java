package game.labirin.entity;

import game.labirin.util.LabirinConstanta;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Wall implements LabirinConstanta{
	private Sprite mSprite;
	private TextureRegion mRegion;
	private Rectangle mRectangle;
	
	public Wall(BaseGameActivity pActivity) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		BitmapTextureAtlas texture = new BitmapTextureAtlas(32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texture, pActivity, "box.png", 0, 0);
		pActivity.getEngine().getTextureManager().loadTexture(texture);
	}

	public void addWall(int x, int y, Scene scene, PhysicsWorld physicsWorld, FixtureDef fixtureDef) {
		mSprite = new Sprite(x, y, 20, 20, mRegion);
		mRectangle = new Rectangle(x + 10, y + 10, 10, 10);
		PhysicsFactory.createBoxBody(physicsWorld, mSprite, BodyType.StaticBody, fixtureDef);
		scene.getChild(LAYER_WALL).attachChild(mRectangle);
		scene.getChild(LAYER_WALL).attachChild(mSprite);
	}
	
	public Sprite sprite() {
		return mSprite;
	}
}
