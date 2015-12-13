package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class WallTile extends Tile {

	private static TextureRegion wallFront, wallTop;
	
	public WallTile(int id) {
		super(id, Tile.SOLID_COST, 2.0f);
		wallFront = Assets.getRegion("wallF");
		wallTop= Assets.getRegion("wallT");
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		if(world.getTile(x / Tile.TILESIZE, y / Tile.TILESIZE - 1) instanceof WallTile){
			batch.draw(wallTop, x, y, Tile.TILESIZE, Tile.TILESIZE);
		}else{
			batch.draw(wallFront, x, y, Tile.TILESIZE, Tile.TILESIZE);
		}
	}

}
