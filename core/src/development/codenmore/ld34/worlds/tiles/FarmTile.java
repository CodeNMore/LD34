package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.ui.Bar;
import development.codenmore.ld34.worlds.World;

public class FarmTile extends Tile {
	
	public static float TIMEINTERVAL = 16.0f;
	private static int minRate = 12, maxRate = 18;
	private float timer = 0f;
	private TextureRegion texture;
	private Bar bar;

	public FarmTile() {
		super(Tile.MAX_UNUSE_ID, 2, -1);
		texture = Assets.getRegion("farmTile");
		bar = new Bar(Color.BLUE, Color.ORANGE, 0, 0, Tile.TILESIZE - 2, 4, TIMEINTERVAL);
	}
	
	@Override
	public void tick(float delta, World world){
		timer += delta;
		if(timer > TIMEINTERVAL){
			timer = 0f;
			world.getGameState().getHud().incFood(MathUtils.random(minRate, maxRate));
		}
		bar.setLevel(timer);
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
		bar.render(batch, x, y + Tile.TILESIZE - 4);
	}

}
