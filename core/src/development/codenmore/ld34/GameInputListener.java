package development.codenmore.ld34;

import com.badlogic.gdx.InputProcessor;

public class GameInputListener implements InputProcessor {
	
	private static boolean[] keys = new boolean[256];
	
	public static boolean isKeyDown(int keycode){
		return keys[keycode];
	}
	
	public GameInputListener(){}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode < 0 || keycode > keys.length)
			return false;
		keys[keycode] = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode < 0 || keycode > keys.length)
			return false;
		keys[keycode] = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
