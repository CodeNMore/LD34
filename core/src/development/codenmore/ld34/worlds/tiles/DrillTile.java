package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.ui.Bar;
import development.codenmore.ld34.worlds.World;

public class DrillTile extends Tile {
	
	public static final float TIMEINTERVAL = 20.0f;
	private static int minRate = 5, maxRate = 10;
	private float animTimer = 0f;
	private float timer = 0f;
	private Animation anim;
	private Bar bar;

	public DrillTile() {
		super(Tile.MAX_UNUSE_ID, Tile.SOLID_COST, 1.0f);
		anim = new Animation(0.3f, Assets.getRegion("drill.1"),
				Assets.getRegion("drill.2"), Assets.getRegion("drill.3"));
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		bar = new Bar(Color.BLUE, Color.ORANGE, 0, 0, Tile.TILESIZE - 2, 4, TIMEINTERVAL);
	}
	
	@Override
	public void tick(float delta, World world){
		animTimer += delta;
		timer += delta;
		if(timer > TIMEINTERVAL){
			timer = 0f;
			world.getGameState().getHud().incResources(MathUtils.random(minRate, maxRate));
		}
		bar.setLevel(timer);
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(anim.getKeyFrame(animTimer), x, y, Tile.TILESIZE, Tile.TILESIZE);
		bar.render(batch, x, y + Tile.TILESIZE - 4);
	}

}
