package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class DirtTile extends Tile {
	
	private static TextureRegion texture;

	public DirtTile(int id) {
		super(id);
		texture = Assets.getRegion("dirt");
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
	}

}
