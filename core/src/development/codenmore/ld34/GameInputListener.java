package development.codenmore.ld34;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameInputListener implements InputProcessor {
	
	private static boolean[] keys = new boolean[256];
	private static Array<InputProcessor> others = new Array<InputProcessor>();
	private static Vector3 tmp = new Vector3();
	public static Viewport viewport;
	public static OrthographicCamera cam;
	
	public static Vector3 unprojectCoords(float x, float y){
		tmp.x = x;
		tmp.y = y;
		cam.unproject(tmp, viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		return tmp;
	}
	
	public static boolean isKeyDown(int keycode){
		return keys[keycode];
	}
	
	public static void addProcessor(InputProcessor input){
		others.add(input);
	}
	
	public GameInputListener(){}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode < 0 || keycode > keys.length)
			return false;
		keys[keycode] = true;
		for(InputProcessor p : others)
			p.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode < 0 || keycode > keys.length)
			return false;
		keys[keycode] = false;
		for(InputProcessor p : others)
			p.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for(InputProcessor p : others)
			p.keyTyped(character);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor p : others)
			p.touchDown(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor p : others)
			p.touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
