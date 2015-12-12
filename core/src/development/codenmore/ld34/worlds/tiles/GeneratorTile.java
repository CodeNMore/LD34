package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class GeneratorTile extends Tile {
	
	public static final float TIMEINTERVAL = 18.0f;
	private static int minRate = 8, maxRate = 20;
	private float animTimer = 0f;
	private float timer = 0f;
	private Animation anim;

	public GeneratorTile() {
		super(Tile.MAX_UNUSE_ID);
		anim = new Animation(0.1f, Assets.getRegion("generator.1"),
				Assets.getRegion("generator.2"), Assets.getRegion("generator.3"));
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
	}
	
	@Override
	public void tick(float delta, World world){
		animTimer += delta;
		timer += delta;
		if(timer > TIMEINTERVAL){
			timer = 0f;
			world.getGameState().getHud().incEnergy(MathUtils.random(minRate, maxRate));
		}
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		batch.draw(anim.getKeyFrame(animTimer), x, y, Tile.TILESIZE, Tile.TILESIZE);
	}

}
