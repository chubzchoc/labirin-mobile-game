package game.labirin.scene;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class CreditsScene{

	private BitmapTextureAtlas mTextureAbout;
	private TextureRegion mTextureRegionAbout;
	public boolean creditsSceneEnable = false;

	public CreditsScene(BaseGameActivity pActivity)
	{
		this.mTextureAbout = new BitmapTextureAtlas(512, 256, TextureOptions.BILINEAR);
		this.mTextureRegionAbout = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTextureAbout, pActivity, "credits.png", 0, 0);
		pActivity.getEngine().getTextureManager().loadTexture(mTextureAbout);
	}

	public Scene onLoadScene()
	{
		creditsSceneEnable  = true;
		Scene mCreditsScene = new Scene();
		Sprite sprBackAbout = new Sprite(0, 0, this.mTextureRegionAbout);
		mCreditsScene.attachChild(sprBackAbout);
		return mCreditsScene;
	}

}
