package development.codenmore.ld34.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld34.GameInputListener;
import development.codenmore.ld34.Main;
import development.codenmore.ld34.worlds.tiles.Tile;

public class World {
	
	private static final float cameraSpeed = 160.0f;
	private int width, height;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private byte[] tiles;
	
	public World(int width, int height){
		this.width = width;
		this.height = height;
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		cam.setToOrtho(false);
		tiles = new byte[width * height];
		
		TerrainGenerator.generateTerrain(width, height, tiles, 40, 4, 200, 300, 10);
	}
	
	public void tick(float delta){
		cam.update();
		if(GameInputListener.isKeyDown(Keys.W)){
			cam.translate(0, cameraSpeed * delta);
		}else if(GameInputListener.isKeyDown(Keys.S)){
			cam.translate(0, -cameraSpeed * delta);
		}
		
		if(GameInputListener.isKeyDown(Keys.A)){
			cam.translate(-cameraSpeed * delta, 0);
		}else if(GameInputListener.isKeyDown(Keys.D)){
			cam.translate(cameraSpeed * delta, 0);
		}
		
		//TODO: THIS IS FOR REGENERATION, DEBUG ONLY
		if(Gdx.input.isKeyJustPressed(Keys.SPACE))
			TerrainGenerator.generateTerrain(width, height, tiles, 40, 4, 200, 300, 10);
	}
	
	public void render(){
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		{
			for(int y = 0;y < height;++y){
				for(int x = 0;x < width;++x){
					getTile(x, y).render(x * Tile.TILESIZE, y * Tile.TILESIZE, this, batch);
				}
			}
		}
		batch.end();
	}
	
	public void dispose(){
		batch.dispose();
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.wallTile;
		return Tile.tiles[tiles[x + y * width]];
	}
	
	public Tile getTile(float x, float y){
		return getTile((int) x, (int) y);
	}
	
	public void setTile(int x, int y, Tile tile){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return;
		tiles[x + y * width] = tile.getId();
	}

}
