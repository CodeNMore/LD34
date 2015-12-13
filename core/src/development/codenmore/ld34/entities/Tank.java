package development.codenmore.ld34.entities;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.utils.Vec2;
import development.codenmore.ld34.worlds.tiles.Tile;


public class Tank extends Entity {

	private float speedMultiplier = 1.0f;

	public Tank(EntityManager manager, float x, float y, float healthMultiplier, float speedMultiplier) {
		super(manager, Assets.getRegion("tank.up"), x, y, 40, 40, 1.25f);
		damageTime = 2.0f;
		this.speedMultiplier = speedMultiplier;
		setStartHealth(7.0f * healthMultiplier);
		setHealth(getStartHealth());
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		
		followPath(70.0f * speedMultiplier * delta);
		
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
	
}
