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
import development.codenmore.ld34.worlds.World;
import development.codenmore.ld34.worlds.tiles.Tile;

public class HUD extends InputAdapter {

	private static final int POS_Y = 48;
	private World world;
	private BuyButton currentSelection = null;
	private int amountOfResources = 5000;
	private int amountOfEnergy = 200;
	private Rectangle hudBounds;
	private Array<Button> buttons;

	public HUD(World world) {
		this.world = world;
		buttons = new Array<Button>();
		hudBounds = new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT / 4);

		buttons.add(new BuyDestroyButton(16, POS_Y));
		buttons.add(new BuyWallButton(128, POS_Y));
		buttons.add(new BuyDrillButton(256 - 16, POS_Y));
		buttons.add(new BuyGeneratorButton(512 - 156, POS_Y));
		buttons.add(new BuyCannonButton(512 - 38, POS_Y));

		GameInputListener.addProcessor(this);
	}

	public void tick(float delta) {
		Vector3 coords = GameInputListener.unprojectCoords(Gdx.input.getX(),
				Gdx.input.getY());
		if (hudBounds.contains(coords.x, coords.y) || currentSelection == null) {
			world.setCursor(null);
			return;
		}
		world.getCursorPos().x = ((int) ((coords.x + world.getTranslation().x) / Tile.TILESIZE) * Tile.TILESIZE);
		world.getCursorPos().y = ((int) ((coords.y + world.getTranslation().y) / Tile.TILESIZE) * Tile.TILESIZE);
		world.setCursor(currentSelection.getTexture());
	}

	public void render(SpriteBatch batch) {
		batch.setColor(0.4f, 0.4f, 0.4f, 0.7f);
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
		Assets.getFont().getData().setScale(0.8f);
		Assets.getFont()
				.draw(batch, ":" + amountOfEnergy, 32, Main.HEIGHT - 36);

		for (Button b : buttons)
			b.render(batch);
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
		for (Button b : buttons) {
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

}
