package development.codenmore.ld34;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.states.LoadingState;
import development.codenmore.ld34.states.State;

public class Main extends ApplicationAdapter {
	
	// Globals
	public static final String TITLE = "Don't Press Them";
	public static final int WIDTH = 640, HEIGHT = 480;
	
	public void create(){
		// Render setup
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Initial state
		State.push(new LoadingState());
	}
	
	public void render(){
		// Clear
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//MUTE
		if(Gdx.input.isKeyJustPressed(Keys.M))
			Assets.toggleMute();
		// Render
		if(State.peek() != null){
			State.peek().tick(Gdx.graphics.getDeltaTime());
			State.peek().render();
		}
	}
	
	public void dispose(){
		State.popAll();
		Assets.dispose();
	}
	
	public void resize(int width, int height){
		if(State.peek() != null)
			State.peek().resize(width, height);
	}
	
}
