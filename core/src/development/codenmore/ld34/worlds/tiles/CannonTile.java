package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.entities.Entity;
import development.codenmore.ld34.entities.Projectile;
import development.codenmore.ld34.worlds.World;

public class CannonTile extends FighterTile {

	private TextureRegion texture;
	public static final int START_RADIUS = 4;
	private float x, y;

	public CannonTile() {
		super(START_RADIUS, MathUtils.random(3f, 4.5f), 2.0f);
		texture = Assets.getRegion("cannon");
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		this.x = x;
		this.y = y;
		batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
		renderBar(batch, x, y + Tile.TILESIZE - 4);
	}

	@Override
	public boolean onReadyFire(World world) {
		Entity e = world.getEntityManager().getFirstEntityInRange(x, y,
				radius * Tile.TILESIZE);
		if (e == null)
			return false;

		world.getEntityManager().addProjectile(
				new Projectile(Assets.getRegion("cannonball"), e, x, y,
						Tile.TILESIZE / 2, Tile.TILESIZE / 2, 1.5f, 340.0f));

		return true;
	}

}
