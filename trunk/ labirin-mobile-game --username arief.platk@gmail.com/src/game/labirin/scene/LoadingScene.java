package game.labirin.scene;

import game.labirin.Base;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Intent;

public class LoadingScene extends Base
{
	private Scene Scene_Loading;
	
	private BitmapTextureAtlas Tex_Loading;
	private TextureRegion Txtr_Loading_Bg;
	private TiledTextureRegion Txtr_Loading_Ani;
	
	public void onLoadResources()
	{
		Tex_Loading = new BitmapTextureAtlas(1024, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Txtr_Loading_Bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(Tex_Loading, this, "gambar/agd_logo.png", 0, 0);
		Txtr_Loading_Ani = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(Tex_Loading, this, "gambar/sprite_bola_loading.png", 0, 150, 8, 1);
		
		mEngine.getTextureManager().loadTexture(Tex_Loading);
	}

	public Scene onLoadScene()
	{
		Scene_Loading = new Scene();
		Scene_Loading.setBackground(new ColorBackground(1, 1, 1));
		
		int Center_X = (CAMERA_WIDTH - Txtr_Loading_Bg.getWidth()) / 2;
		int Center_Y = (CAMERA_HEIGHT - Txtr_Loading_Bg.getHeight()) / 2;
		Sprite Spr_Bg_Loading = new Sprite(Center_X, Center_Y, Txtr_Loading_Bg);
		Spr_Bg_Loading.setScale(0.7f);
		AnimatedSprite Spr_Loading_Ani = new AnimatedSprite(280, 200, 30, 30, Txtr_Loading_Ani);
		Spr_Loading_Ani.animate(100);
		
		Scene_Loading.attachChild(Spr_Bg_Loading);
//		Scene_Loading.attachChild(Spr_Loading_Ani);
		
		mEngine.registerUpdateHandler(new TimerHandler(4, new ITimerCallback() {
			
			public void onTimePassed(TimerHandler pTimerHandler) {
				System.out.print(pTimerHandler.getTimerSecondsElapsed());
				startActivity(new Intent(LoadingScene.this, HomeScene.class));
				finish();
			}
		}));
		
		return Scene_Loading;
	}

	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

}
