package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class StoneTile extends Tile {
	
	private static TextureRegion texture;

	public StoneTile(int id) {
		super(id, 2);
		texture = Assets.getRegion("stoneTile");
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
	}

}
