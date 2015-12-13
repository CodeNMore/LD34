package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class FarmTile extends Tile {
	
	public static final float TIMEINTERVAL = 8.0f;
	private static int minRate = 3, maxRate = 7;
	private float timer = 0f;
	private TextureRegion texture;

	public FarmTile() {
		super(Tile.MAX_UNUSE_ID, 2);
		texture = Assets.getRegion("farmTile");
	}
	
	@Override
	public void tick(float delta, World world){
		timer += delta;
		if(timer > TIMEINTERVAL){
			timer = 0f;
			world.getGameState().getHud().incFood(MathUtils.random(minRate, maxRate));
		}
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
	}

}
