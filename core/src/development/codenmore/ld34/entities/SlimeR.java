package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import development.codenmore.ld34.assets.Assets;


public class SlimeR extends Entity {
	
	private Animation anim;
	private float animTimer = 0f;
	private float speedMultiplier = 1.0f;

	public SlimeR(EntityManager manager, float x, float y, float healthMultiplier, float speedMultiplier) {
		super(manager, Assets.getRegion("slimer.1"), x, y, 32, 32, 0.5f);
		setStartHealth(4.5f * healthMultiplier);
		damageTime = 1.25f;
		this.speedMultiplier = speedMultiplier;
		setHealth(getStartHealth());
		anim = new Animation(0.1f, Assets.getRegion("slimer.1"), Assets.getRegion("slimer.2"),
				Assets.getRegion("slimer.3"), Assets.getRegion("slimer.4"), Assets.getRegion("slimer.5"));
		anim.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		animTimer += delta;
		setTexture(anim.getKeyFrame(animTimer));
		
		followPath(110.0f * speedMultiplier * delta);
	}
	
}
