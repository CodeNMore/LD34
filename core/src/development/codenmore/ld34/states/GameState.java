package development.codenmore.ld34.states;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

import development.codenmore.ld34.GameInputListener;
import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.ui.HUD;
import development.codenmore.ld34.worlds.World;
import development.codenmore.ld34.worlds.tiles.DrillTile;
import development.codenmore.ld34.worlds.tiles.FarmTile;
import development.codenmore.ld34.worlds.tiles.GeneratorTile;

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
		GeneratorTile.TIMEINTERVAL = 7f;
		DrillTile.TIMEINTERVAL = 7f;
		FarmTile.TIMEINTERVAL = 16f;
	}

	@Override
	public void tick(float delta) {
		if(GameInputListener.isKeyDown(Keys.F))
			delta *= 4;
			
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
			Assets.getFont().getData().setScale(0.6f);
			Assets.getFont().setColor(Color.BLACK);
			Assets.getFont().draw(batch, "Hold 'F' to\nFast Forward!", Main.WIDTH - 140, Main.HEIGHT - 8);
		}
		batch.end();
	}

	@Override
	public void onPop() {
		super.onPop();
		world.dispose();
	}
	
	@Override
	public void onPush(){
		super.onPush();
		Assets.playMusic("game1");
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
