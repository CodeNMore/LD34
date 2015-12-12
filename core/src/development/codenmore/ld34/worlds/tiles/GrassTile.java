package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class GrassTile extends Tile {
	
	private static TextureRegion grass, grassL, grassR, grassT, grassD;
	private static Array<TextureRegion> edges;

	public GrassTile(int id) {
		super(id);
		edges = new Array<TextureRegion>(4);
		grass = Assets.getRegion("grass");
		grassL = Assets.getRegion("grassL");
		grassR = Assets.getRegion("grassR");
		grassT = Assets.getRegion("grassT");
		grassD = Assets.getRegion("grassD");
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		calculateEdges((int)(x / Tile.TILESIZE), (int)(y / Tile.TILESIZE), world);
		batch.draw(grass, x, y, Tile.TILESIZE, Tile.TILESIZE);
		for(TextureRegion t : edges){
			batch.draw(t, x, y, Tile.TILESIZE, Tile.TILESIZE);
		}
	}
	
	private void calculateEdges(int x, int y, World world){
		edges.clear();
		if(world.getTile(x, y + 1) instanceof DirtTile){
			edges.add(grassT);
		}
		if(world.getTile(x, y - 1) instanceof DirtTile){
			edges.add(grassD);
		}
		if(world.getTile(x + 1, y) instanceof DirtTile){
			edges.add(grassR);
		}
		if(world.getTile(x - 1, y) instanceof DirtTile){
			edges.add(grassL);
		}
	}

}
