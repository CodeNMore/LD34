package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class DrillTile extends Tile {
	
	public static final float TIMEINTERVAL = 5.0f;
	private static int minRate = 5, maxRate = 10;
	private float animTimer = 0f;
	private float timer = 0f;
	private Animation anim;

	public DrillTile() {
		super(Tile.MAX_UNUSE_ID);
		anim = new Animation(0.3f, Assets.getRegion("drill.1"),
				Assets.getRegion("drill.2"), Assets.getRegion("drill.3"));
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
	}
	
	@Override
	public void tick(float delta, World world){
		animTimer += delta;
		timer += delta;
		if(timer > TIMEINTERVAL){
			timer = 0f;
			world.getGameState().getHud().incResources(MathUtils.random(minRate, maxRate));
		}
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(anim.getKeyFrame(animTimer), x, y, Tile.TILESIZE, Tile.TILESIZE);
	}

}
