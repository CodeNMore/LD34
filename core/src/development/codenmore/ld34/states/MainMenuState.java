package development.codenmore.ld34.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;

public class MainMenuState extends State implements InputProcessor {
	
	private TextureRegion  bg = Assets.getRegion("introScreen");
	private Rectangle bounds;
	
	public MainMenuState(){
		bounds = new Rectangle(107, 50, 530, 250);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void tick(float delta) {
		
	}

	@Override
	public void render() {
		updateRendering();
		batch.begin();
		{
			batch.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
		}
		batch.end();
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 tmp = new Vector3();
		tmp.x = screenX;
		tmp.y = screenY;
		cam.unproject(tmp, viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		if(bounds.contains(tmp.x, tmp.y)){
			State.pop();
			State.push(new GameState());
			return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
