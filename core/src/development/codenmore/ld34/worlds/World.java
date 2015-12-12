package development.codenmore.ld34.worlds;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import development.codenmore.ld34.GameInputListener;
import development.codenmore.ld34.Main;
import development.codenmore.ld34.states.GameState;
import development.codenmore.ld34.worlds.tiles.Tile;

public class World {

	private static final float cameraSpeed = 260.0f;
	private int width, height;
	private Vector2 translation;
	private OrthographicCamera cam;
	private GameState gameState;
	private SpriteBatch batch;
	private Tile[] tiles;

	public World(GameState gameState, int width, int height) {
		this.gameState = gameState;
		this.width = width;
		this.height = height;
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		cam.setToOrtho(false);
		translation = new Vector2();
		tiles = new Tile[width * height];

		TerrainGenerator.generateTerrain(width, height, tiles, 40, 4, 200, 300,
				10);
	}

	public void tick(float delta) {
		cam.update();
		cam.position.x = translation.x + Main.WIDTH / 2;
		cam.position.y = translation.y + Main.HEIGHT / 2;

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
		
		if(translation.x < 0)
			translation.x = 0;
		if(translation.y < 0)
			translation.y = 0;
		if(translation.x > width * Tile.TILESIZE - Main.WIDTH)
			translation.x = width * Tile.TILESIZE - Main.WIDTH;
		if(translation.y > height * Tile.TILESIZE - Main.HEIGHT)
			translation.y = height * Tile.TILESIZE - Main.HEIGHT;

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				getTile(x, y).tick(delta, this);
			}
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
		}
		batch.end();
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
		tiles[x + y * width] = tile;
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

}
