package game.labirin;

import game.labirin.util.LabirinConstanta;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Base extends BaseGameActivity implements LabirinConstanta{
	
	public Camera mCamera;

	public Engine onLoadEngine() {
		
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera).setNeedsSound(true).setNeedsMusic(true));
		
	}

	public void onLoadResources() {}

	public Scene onLoadScene() {
		return null;
	}

	public void onLoadComplete() {}

}
