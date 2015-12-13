package development.codenmore.ld34.states;

import development.codenmore.ld34.GameInputListener;
import development.codenmore.ld34.ui.HUD;
import development.codenmore.ld34.worlds.World;

public class GameState extends State {

	private World world;
	private HUD hud;
	private Tutorial tutorial;
	private GameInputListener inputListener;

	public GameState() {
		inputListener = new GameInputListener();
		GameInputListener.cam = cam;
		GameInputListener.viewport = viewport;

		tutorial = new Tutorial(inputListener);
		world = new World(this, 64, 64);
		hud = new HUD(world);
	}

	@Override
	public void tick(float delta) {
		tutorial.tick(delta);
		world.tick(delta);
		hud.tick(delta);
	}

	@Override
	public void render() {
		updateRendering();
		world.render();

		batch.begin();
		{
			hud.render(batch);
			tutorial.render(batch);
		}
		batch.end();
	}

	@Override
	public void onPop() {
		super.onPop();
		world.dispose();
	}

	// GETTERS SETTERS

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public HUD getHud() {
		return hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

}
