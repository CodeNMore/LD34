package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class CannonTile extends Tile {
	
//	private float timer = 0f;
	private TextureRegion texture;

	public CannonTile() {
		super(Tile.MAX_UNUSE_ID);
		texture = Assets.getRegion("cannon");
	}
	
	@Override
	public void tick(float delta, World world){
//		timer += delta;
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
	}

}
