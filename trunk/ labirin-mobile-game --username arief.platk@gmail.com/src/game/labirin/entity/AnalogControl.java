package game.labirin.entity;

import game.labirin.util.LabirinConstanta;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class AnalogControl extends Entity implements LabirinConstanta{
	private BitmapTextureAtlas mTexture;
	private TextureRegion mBaseTextureRegion;
	private TextureRegion mKnobTextureRegion;
	private Camera mCamera;
	private AnalogOnScreenControl mAnalogOnScreenControl;
	private IAnalogOnScreenControlListener mListener;

	public AnalogControl(BaseGameActivity pActivity, Camera pCamera, IAnalogOnScreenControlListener pListener) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gambar/");
		mTexture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, pActivity, "onscreen_control_base.png", 0, 0);
		mKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mTexture, pActivity, "onscreen_control_knob.png", 128, 0);
		pActivity.getEngine().getTextureManager().loadTexture(mTexture);
		
		mCamera = pCamera;
		mListener = pListener;
	}
	
	public AnalogOnScreenControl createControl() {
		mAnalogOnScreenControl = new AnalogOnScreenControl(0, CAMERA_HEIGHT - mBaseTextureRegion.getHeight(), mCamera, mBaseTextureRegion, mKnobTextureRegion, 0.1f, 200, mListener);
		mAnalogOnScreenControl.getControlBase().setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		mAnalogOnScreenControl.getControlBase().setAlpha(0.7f);
		mAnalogOnScreenControl.getControlBase().setScale(0.6f);
		mAnalogOnScreenControl.getControlKnob().setScale(0.6f);
		return mAnalogOnScreenControl;
	}
}
