package development.codenmore.ld34.states;

import com.badlogic.gdx.Gdx;

import development.codenmore.ld34.GameInputListener;
import development.codenmore.ld34.worlds.World;

public class GameState extends State {
	
	private World world;
	private GameInputListener inputListener;

	public GameState(){
		world = new World(25, 25);
		inputListener = new GameInputListener();
		Gdx.input.setInputProcessor(inputListener);
	}
	
	@Override
	public void tick(float delta) {
		world.tick(delta);
	}

	@Override
	public void render() {
		world.render();
		
		batch.begin();
		{
			// UI stuff
		}
		batch.end();
	}
	
	@Override
	public void onPop(){
		super.onPop();
		world.dispose();
	}

}
