package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.utils.Vec2;
import development.codenmore.ld34.worlds.tiles.Tile;


public class TankSlime extends Entity {

	private float speedMultiplier = 1.0f;
	private float animTimer = 0f;
	private Animation anim;

	public TankSlime(EntityManager manager, float x, float y, float healthMultiplier, float speedMultiplier) {
		super(manager, Assets.getRegion("tank.up"), x, y, 40, 40, 1.75f);
		damageTime = 2.2f;
		this.speedMultiplier = speedMultiplier;
		setStartHealth(8.0f * healthMultiplier);
		setHealth(getStartHealth());
		healthBar.setMaxLevel(startHealth);
		if(MathUtils.randomBoolean())
			anim = new Animation(0.3f, Assets.getRegion("slime.1"), Assets.getRegion("slime.2"),
				Assets.getRegion("slime.3"));
		else
			anim = new Animation(0.3f, Assets.getRegion("slimer.1"), Assets.getRegion("slimer.2"),
					Assets.getRegion("slimer.3"));
		
		anim.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		animTimer += delta;
		
		followPath(60.0f * speedMultiplier * delta);
		
		if(path.size > 0){
			Vec2 v = path.get(0);
			float xp = v.x * Tile.TILESIZE;
			float yp = v.y * Tile.TILESIZE;
			if(xp < bounds.x){
				setTexture(Assets.getRegion("tank.left"));
			}else if(xp > bounds.x){
				setTexture(Assets.getRegion("tank.right"));
			}else if(yp < bounds.y){
				setTexture(Assets.getRegion("tank.down"));
			}else if(yp > bounds.y){
				setTexture(Assets.getRegion("tank.up"));
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		batch.draw(anim.getKeyFrame(animTimer), bounds.x + 8, bounds.y + 12, 24, 24);
	}
	
}
