package development.codenmore.ld34.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;

public class LoadingState extends State {
	
	private static final float MIN_TIME = 2.0f;
	private Texture bg;
	private float timer = 0f;
	
	public LoadingState(){
		bg = new Texture(Gdx.files.internal("textures/loadingScreen.png"));
	}

	@Override
	public void tick(float delta) {
		timer += delta;
		if(Assets.step() && timer >= MIN_TIME){
			State.pop();
			State.push(new MainMenuState());
		}
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
	public void onPop(){
		super.onPop();
		bg.dispose();
	}

}
