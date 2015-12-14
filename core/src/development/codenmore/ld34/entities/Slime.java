package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.tiles.Tile;


public class Slime extends Entity {
	
	private Animation anim;
	private float animTimer = 0f;
	private float speedMultiplier = 1.0f;

	public Slime(EntityManager manager, float x, float y, float healthMultiplier, float speedMultiplier) {
		super(manager, Assets.getRegion("slime.1"), x, y, 32, 32, 0.25f);
		setStartHealth(3.0f * healthMultiplier);
		setHealth(getStartHealth());
		healthBar.setMaxLevel(startHealth);
		healthBar.getBounds().width = Tile.TILESIZE - 8;
		damageTime = 0.9f;
		this.speedMultiplier = speedMultiplier;
		anim = new Animation(0.1f, Assets.getRegion("slime.1"), Assets.getRegion("slime.2"),
				Assets.getRegion("slime.3"), Assets.getRegion("slime.4"), Assets.getRegion("slime.5"));
		anim.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		animTimer += delta;
		setTexture(anim.getKeyFrame(animTimer));
		
		followPath(90.0f * speedMultiplier * delta);
	}
	
}
