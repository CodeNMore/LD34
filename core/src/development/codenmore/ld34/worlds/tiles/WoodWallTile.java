package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class WoodWallTile extends Tile {

	private static TextureRegion wallFront, wallTop;
	
	public WoodWallTile(int id) {
		super(id, Tile.SOLID_COST - 1, 0.75f);
		wallFront = Assets.getRegion("wallFwood");
		wallTop= Assets.getRegion("wallTwood");
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		if(world.getTile(x / Tile.TILESIZE, y / Tile.TILESIZE - 1) instanceof WoodWallTile
				|| world.getTile(x / Tile.TILESIZE, y / Tile.TILESIZE - 1) instanceof WallTile){
			batch.draw(wallTop, x, y, Tile.TILESIZE, Tile.TILESIZE);
		}else{
			batch.draw(wallFront, x, y, Tile.TILESIZE, Tile.TILESIZE);
		}
	}

}
