package development.codenmore.ld34.worlds;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import development.codenmore.ld34.GameInputListener;
import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.entities.EntityManager;
import development.codenmore.ld34.entities.Node;
import development.codenmore.ld34.states.GameState;
import development.codenmore.ld34.states.LoseState;
import development.codenmore.ld34.states.State;
import development.codenmore.ld34.worlds.tiles.ButtonTile;
import development.codenmore.ld34.worlds.tiles.Tile;

public class World {

	private static final float cameraSpeed = 260.0f;
	private static TextureRegion cursorRadiusTex = Assets.getRegion("radius");
	private int width, height;
	private Vector2 translation;
	private OrthographicCamera cam;
	private GameState gameState;
	private SpriteBatch batch;
	private TextureRegion cursor;
	private float cursorRadius = 0f;
	private Vector2 cursorPos = new Vector2();
	private Tile[] tiles;
	private ButtonTile[] buttons;
	private float[] tileHealth;
	private EntityManager entityManager;
	private int currentTileIndex = 0;

	public World(GameState gameState, int width, int height) {
		this.gameState = gameState;
		this.width = width;
		this.height = height;
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		cam.setToOrtho(false);
		translation = new Vector2(width / 2 * Tile.TILESIZE - Main.WIDTH / 2,
				height / 2 * Tile.TILESIZE - Main.HEIGHT / 2);
		tiles = new Tile[width * height];
		tileHealth = new float[width * height];
		Node.world = this;
		entityManager = new EntityManager(this);

		TerrainGenerator.generateTerrain(width, height, this, 40, 4, 200, 300,
				10);

		buttons = new ButtonTile[2];
		buttons[0] = new ButtonTile();
		buttons[1] = new ButtonTile();
		setTile(width / 2 + MathUtils.random(-3, 3),
				height / 2 + MathUtils.random(1, 3), buttons[0]);
		setTile(width / 2 + MathUtils.random(-3, 3),
				height / 2 + MathUtils.random(-3, -1), buttons[1]);
	}

	public void tick(float delta) {

		cam.update();
		cam.position.x = translation.x + Main.WIDTH / 2;
		cam.position.y = translation.y + Main.HEIGHT / 2;

		if (gameState.getTutorial().isOn())
			return;

		if (!GameInputListener.isKeyDown(Keys.F)) {
			if (GameInputListener.isKeyDown(Keys.W)) {
				translation.y += cameraSpeed * delta;
			} else if (GameInputListener.isKeyDown(Keys.S)) {
				translation.y -= cameraSpeed * delta;
			}

			if (GameInputListener.isKeyDown(Keys.A)) {
				translation.x -= cameraSpeed * delta;
			} else if (GameInputListener.isKeyDown(Keys.D)) {
				translation.x += cameraSpeed * delta;
			}
		}

		if (translation.x < 0)
			translation.x = 0;
		if (translation.y < 0)
			translation.y = 0;
		if (translation.x > width * Tile.TILESIZE - Main.WIDTH)
			translation.x = width * Tile.TILESIZE - Main.WIDTH;
		if (translation.y > height * Tile.TILESIZE - Main.HEIGHT)
			translation.y = height * Tile.TILESIZE - Main.HEIGHT;

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				currentTileIndex = x + y * width;
				getTile(x, y).tick(delta, this);
			}
		}

		entityManager.tick(delta);

		boolean not = true;
		for (ButtonTile b : buttons) {
			if (!b.isPressed())
				not = false;
		}
		if (not) {
			// LOST DUE TO BUTTONS!!!
			State.pop();
			State.push(new LoseState(entityManager.getWaveManager()
					.getWaveNum(), false));
		}
	}

	public void render() {
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		{
			int x0 = (int) Math.max(0, translation.x / Tile.TILESIZE - 1);
			int x1 = (int) Math.min((translation.x + Main.WIDTH)
					/ Tile.TILESIZE + 2, width);
			int y0 = (int) Math.max(0, translation.y / Tile.TILESIZE - 1);
			int y1 = (int) Math.min((translation.y + Main.HEIGHT)
					/ Tile.TILESIZE + 2, height);
			for (int y = y0; y < y1; ++y) {
				for (int x = x0; x < x1; ++x) {
					getTile(x, y).render(x * Tile.TILESIZE, y * Tile.TILESIZE,
							this, batch);
				}
			}
			// CURSOR
			if (cursor != null) {
				batch.setColor(1.0f, 1.0f, 1.0f, 0.4f);
				batch.draw(cursor, cursorPos.x, cursorPos.y, Tile.TILESIZE,
						Tile.TILESIZE);
				batch.setColor(Color.WHITE);
				if (cursorRadius != 0f) {
					batch.draw(cursorRadiusTex, cursorPos.x + Tile.TILESIZE / 2
							- cursorRadius, cursorPos.y + Tile.TILESIZE / 2
							- cursorRadius, cursorRadius * 2, cursorRadius * 2);
				}
			}
			// Entities
			entityManager.render(batch);
		}
		batch.end();
	}

	public ButtonTile getAvailableButtonTile() {
		int index = MathUtils.random(0, 1);
		if (buttons[index].isPressed()) {
			index++;
			if (index >= buttons.length)
				index = 0;
			return buttons[index];
		} else {
			return buttons[index];
		}
	}

	public void dispose() {
		batch.dispose();
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.stoneTile;
		return tiles[x + y * width];
	}

	public Tile getTile(float x, float y) {
		return getTile((int) x, (int) y);
	}

	public void setTile(int x, int y, Tile tile) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return;
		setTile(x + y * width, tile);
	}

	public void setTile(int i, Tile tile) {
		tiles[i] = tile;
		tileHealth[i] = tile.getHealth();
	}

	public float getHealth(int x, int y) {
		return getHealth(x + y * width);
	}

	public float getHealth(int i) {
		if (i < 0 || i >= tiles.length)
			return -1f;
		return tileHealth[i];
	}

	public float incHealth(int x, int y, float amt) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return -1f;
		tileHealth[x + y * width] += amt;
		return getHealth(x, y);
	}

	public void reHealTiles() {
		for (int i = 0; i < tileHealth.length; ++i) {
			tileHealth[i] = tiles[i].getStartHealth();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Vector2 getTranslation() {
		return translation;
	}

	public void setTranslation(Vector2 translation) {
		this.translation = translation;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public TextureRegion getCursor() {
		return cursor;
	}

	public void setCursor(TextureRegion cursor) {
		this.cursor = cursor;
	}

	public Vector2 getCursorPos() {
		return cursorPos;
	}

	public float getCursorRadius() {
		return cursorRadius;
	}

	public void setCursorRadius(float cursorRadius) {
		this.cursorRadius = cursorRadius;
	}

	public void setCursorPos(Vector2 cursorPos) {
		this.cursorPos = cursorPos;
	}

	public ButtonTile[] getButtons() {
		return buttons;
	}

	public void setButtons(ButtonTile[] buttons) {
		this.buttons = buttons;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public int getCurrentTileIndex() {
		return currentTileIndex;
	}

	public void setCurrentTileIndex(int currentTileIndex) {
		this.currentTileIndex = currentTileIndex;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

}
