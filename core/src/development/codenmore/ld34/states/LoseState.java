package development.codenmore.ld34.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;

public class LoseState extends State implements InputProcessor {
	
	private static TextureRegion bg = Assets.getRegion("lostScreen");
	private int wave;
	private boolean fromStarve;
	private Rectangle bounds;
	private float timer = 0f, wait = 0.8f;
	
	public LoseState(int wave, boolean fromStarve){
		this.wave = wave - 1;
		this.fromStarve = fromStarve;
		bounds = new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT / 6);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void tick(float delta) {
		timer += delta;
	}

	@Override
	public void render() {
		updateRendering();
		
		batch.begin();
		{
			batch.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
			Assets.getFont().getData().setScale(5.0f);
			Assets.getFont().setColor(Color.ORANGE);
			Assets.getFont().draw(batch, "" + wave, Main.WIDTH / 2 - 32, Main.WIDTH - Main.WIDTH / 2.8f);
			Assets.getFont().getData().setScale(1.0f);
			Assets.getFont().setColor(Color.BLUE);
			if(fromStarve){
				Assets.getFont().draw(batch, "DEFENDED AGAINST THE ENEMIES\nAND PROTECTED THE BUTTONS,\nBUT STARVED YOUR CITIZENS!", 64, 200);
			}else{
				Assets.getFont().draw(batch, "LET THE ENEMIES PRESS THE 2\nDETONATION BUTTONS AND BLEW UP\nYOUR OWN CITIZENS!", 64, 200);
			}
		}
		batch.end();
	}
	
	@Override
	public void onPush(){
		super.onPush();
		Assets.playMusic("menus");
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(timer < wait)
			return false;
		Vector3 tmp = new Vector3();
		tmp.x = screenX;
		tmp.y = screenY;
		cam.unproject(tmp, viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		if(bounds.contains(tmp.x, tmp.y)){
			State.pop();
			State.push(new MainMenuState());
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
