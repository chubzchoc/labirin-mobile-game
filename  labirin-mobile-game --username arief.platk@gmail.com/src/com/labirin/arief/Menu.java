package com.labirin.arief;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;



public class Menu extends BaseGameActivity{
private Camera mCamera;
private Scene mScene;
private Texture mTexture;
private TextureRegion mTextureRegion;
private TextureRegion mTextureRegionMenuPlay;
private TextureRegion mTextureRegionMenuAbout;
private final int CAMERA_WIDTH=240;
private final int CAMERA_HEIGHT=320;

public void onLoadComplete() {
	// TODO Auto-generated method stub
	
}

public Engine onLoadEngine() {
	mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
}

public void onLoadResources() {
	// TODO Auto-generated method stub
this.mTexture = new Texture(256,256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
this.mTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "gambar/back_menu.png", 0, 0);
this.mTextureRegionMenuPlay = TextureRegionFactory.createFromAsset(this.mTexture, this, "gambar/button_play.png", 0, 50);
this.mTextureRegionMenuAbout = TextureRegionFactory.createFromAsset(this.mTexture, this, "gambar/button_about.png", 0, 70);

}

public Scene onLoadScene() {
	// TODO Auto-generated method stub
	 mScene = new Scene (1); 
	 Sprite sprite = new Sprite (0,0,mTextureRegion);
	 mScene.getLastChild().attachChild(sprite);	
	return mScene;		
	
	

}
}
