package game.labirin.scene;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class HelpScene {

	private BitmapTextureAtlas mHelpTexture;
	private TextureRegion mHelpTextureRegion;
	public boolean helpSceneEnable = false;

	public HelpScene(BaseGameActivity pActivity) {
		
		this.mHelpTexture = new BitmapTextureAtlas(512, 256, TextureOptions.BILINEAR);
		this.mHelpTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mHelpTexture, pActivity, "help_1.png", 0, 0);
		pActivity.getEngine().getTextureManager().loadTexture(mHelpTexture);
		
	}

	public Scene onLoadScene() {
		
		helpSceneEnable = true;
		Scene mHelpScene = new Scene();
		Sprite sprBackHelp = new Sprite(0, 0, this.mHelpTextureRegion);
		mHelpScene.attachChild(sprBackHelp);
		return mHelpScene;
		
	}

}
