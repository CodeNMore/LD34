package development.codenmore.ld34.states;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import development.codenmore.ld34.Main;

public abstract class State {
	
	// Manager
	
	private static Stack<State> states = new Stack<State>();
	
	public static void push(State state){
		states.push(state);
	}
	
	public static State peek(){
		if(states.isEmpty())
			return null;
		return states.peek();
	}
	
	public static void pop(){
		if(!states.isEmpty())
			states.pop();
	}
	
	public static void popAll(){
		while(!states.isEmpty())
			states.pop();
	}
	
	// Class
	
	protected SpriteBatch batch;
	protected OrthographicCamera cam;
	protected Viewport viewport;
	
	public State(){
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		cam.setToOrtho(false);
		viewport = new FitViewport(Main.WIDTH, Main.HEIGHT, cam);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public abstract void tick(float delta);
	
	public abstract void render();
	
	public void onPush(){}
	
	public void onPop(){
		batch.dispose();
	}
	
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	protected void updateRendering(){
		cam.update();
		batch.setProjectionMatrix(cam.combined);
	}
	
}
