package development.codenmore.ld34.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld34.GameInputListener;
import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.states.LoseState;
import development.codenmore.ld34.states.State;
import development.codenmore.ld34.worlds.World;
import development.codenmore.ld34.worlds.tiles.CannonTile;
import development.codenmore.ld34.worlds.tiles.FreezerTile;
import development.codenmore.ld34.worlds.tiles.LazerTile;
import development.codenmore.ld34.worlds.tiles.NextPageButton;
import development.codenmore.ld34.worlds.tiles.PrevPageButton;
import development.codenmore.ld34.worlds.tiles.Tile;

public class HUD extends InputAdapter {

	private static final int POS_Y = 48;
	private World world;
	private BuyButton currentSelection = null;
	private int amountOfResources = 2000;//
	private int amountOfEnergy = 2000;//
	private int amountOfFood = 30;
	private int initialFoodSub = 3, foodTakers = 0;
	private float foodTimer = 0f, foodTime = 30.0f;
	private Rectangle hudBounds;
	private Array<Button> buttonsA, buttonsB;
	public int page = 1;
	private float warnTime = 7.0f, warningTimer = warnTime;

	public HUD(World world) {
		this.world = world;
		buttonsA = new Array<Button>();
		buttonsB = new Array<Button>();
		hudBounds = new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT / 4);

		buttonsA.add(new BuyDestroyButton(16, POS_Y));
		buttonsA.add(new BuyWoodenWallButton(128, POS_Y));
		buttonsA.add(new BuyWallButton(256 - 16, POS_Y));
		buttonsA.add(new BuyDrillButton(256 + 100, POS_Y));
		buttonsA.add(new BuyCannonButton(512 - 38, POS_Y));
		buttonsA.add(new NextPageButton(580, POS_Y - 16, this));
		
		buttonsB.add(new PrevPageButton(16, POS_Y - 16, this));
		buttonsB.add(new BuyGeneratorButton(80, POS_Y));
		buttonsB.add(new BuyLazerButton(256 + 28, POS_Y));
		buttonsB.add(new BuyFarmButton(156+26, POS_Y));
		buttonsB.add(new BuyFreezerButton(256 + 134, POS_Y));

		GameInputListener.addProcessor(this);
	}

	public void tick(float delta) {
		if(world.getGameState().getTutorial().isOn())
			return;
		Vector3 coords = GameInputListener.unprojectCoords(Gdx.input.getX(),
				Gdx.input.getY());
		
		foodTimer += delta;
		warningTimer += delta;
		
		if(foodTimer > foodTime){
			foodTimer = 0f;
			amountOfFood -= initialFoodSub * foodTakers;
			if(amountOfFood <= initialFoodSub * foodTakers + 15){
				warningTimer = 0f;
			}
			if(amountOfFood <= 0){
				State.pop();
				State.push(new LoseState(world.getEntityManager().getWaveManager().getWaveNum(), true));
			}
		}
		
		if (hudBounds.contains(coords.x, coords.y) || currentSelection == null) {
			world.setCursor(null);
			return;
		}
		world.getCursorPos().x = ((int) ((coords.x + world.getTranslation().x) / Tile.TILESIZE) * Tile.TILESIZE);
		world.getCursorPos().y = ((int) ((coords.y + world.getTranslation().y) / Tile.TILESIZE) * Tile.TILESIZE);
		world.setCursor(currentSelection.getTexture());
		if(currentSelection instanceof BuyCannonButton){
			world.setCursorRadius(CannonTile.START_RADIUS * Tile.TILESIZE);
		}else if(currentSelection instanceof BuyLazerButton){
			world.setCursorRadius(LazerTile.START_RADIUS * Tile.TILESIZE);
		}else if(currentSelection instanceof BuyFreezerButton){
			world.setCursorRadius(FreezerTile.START_RADIUS * Tile.TILESIZE);
		}else{
			world.setCursorRadius(0f);
		}
	}

	public void render(SpriteBatch batch) {
		batch.setColor(0.4f, 0.4f, 0.4f, 0.85f);
		batch.draw(Assets.getRegion("color"), 0, 0, Main.WIDTH, Main.HEIGHT / 4);
		batch.setColor(Color.WHITE);

		batch.draw(Assets.getRegion("resourceIcon"), 2, Main.HEIGHT - 26, 24,
				24);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont().getData().setScale(0.8f);
		Assets.getFont().draw(batch, ":" + amountOfResources, 32,
				Main.HEIGHT - 4);
		batch.draw(Assets.getRegion("energyIcon"), 2, Main.HEIGHT - 56, 24, 24);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont()
				.draw(batch, ":" + amountOfEnergy, 32, Main.HEIGHT - 36);
		batch.draw(Assets.getRegion("foodIcon"), 2, Main.HEIGHT - 86, 24, 24);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont()
				.draw(batch, ":" + amountOfFood, 32, Main.HEIGHT - 68);

		for (Button b : (page == 1) ? buttonsA : buttonsB)
			b.render(batch);
		
		world.getEntityManager().getWaveManager().render(batch);
		
		if(warningTimer < warnTime){
			Assets.getFont().setColor(Color.ORANGE);
			Assets.getFont().getData().setScale(0.6f);
			Assets.getFont().draw(batch, "WATCH YOUR FOOD!", 80, Main.HEIGHT - 70);
		}
	}

	public void addFoodTaker(){
		foodTakers++;
	}
	
	public void removeFoodTaker(){
		foodTakers--;
		if(foodTakers < 0)
			foodTakers = 0;
	}
	
	public boolean checkPlacement(float x, float y, int pointer, int button) {
		int tileX = (int) (x + world.getTranslation().x) / Tile.TILESIZE;
		int tileY = (int) (y + world.getTranslation().y) / Tile.TILESIZE;
		if (tileX < 0 || tileY < 0 || tileX >= world.getWidth()
				|| tileY >= world.getHeight())
			return false;

		// PLACE AND CHECK PRICE OF CURRENT TILE
		if (currentSelection == null)
			return false;
		currentSelection.onPlace(tileX, tileY, this);

		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 coords = GameInputListener.unprojectCoords(screenX, screenY);
		for (Button b : (page == 1) ? buttonsA : buttonsB) {
			if (b.checkClick(coords.x, coords.y)) {
				if (b instanceof BuyButton)
					currentSelection = (BuyButton) b;
				return true;
			}
		}

		if (!hudBounds.contains(coords.x, coords.y))
			checkPlacement(coords.x, coords.y, pointer, button);

		return false;
	}

	// GETTERS SETTERS

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public void incResources(int amt) {
		amountOfResources += amt;
	}

	public void incEnergy(int amt) {
		amountOfEnergy += amt;
	}
	
	public void incFood(int amt){
		amountOfFood += amt;
	}

	public BuyButton getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(BuyButton currentSelection) {
		this.currentSelection = currentSelection;
	}

	public int getAmountOfResources() {
		return amountOfResources;
	}

	public void setAmountOfResources(int amountOfResources) {
		this.amountOfResources = amountOfResources;
	}

	public int getAmountOfEnergy() {
		return amountOfEnergy;
	}

	public void setAmountOfEnergy(int amountOfEnergy) {
		this.amountOfEnergy = amountOfEnergy;
	}

	public int getAmountOfFood() {
		return amountOfFood;
	}

	public void setAmountOfFood(int amountOfFood) {
		this.amountOfFood = amountOfFood;
	}

}
