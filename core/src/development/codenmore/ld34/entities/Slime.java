package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import development.codenmore.ld34.assets.Assets;


public class Slime extends Entity {
	
	private Animation anim;
	private float animTimer = 0f;

	public Slime(EntityManager manager, float x, float y) {
		super(manager, Assets.getRegion("slime.1"), x, y, 32, 32);
		anim = new Animation(0.8f, Assets.getRegion("slime.1"), Assets.getRegion("slime.2"),
				Assets.getRegion("slime.3"), Assets.getRegion("slime.4"), Assets.getRegion("slime.5"));
		anim.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void tick(float delta) {
		animTimer += delta;
		setTexture(anim.getKeyFrame(animTimer));
		
		
		
		checkHealth();
	}
	
}
