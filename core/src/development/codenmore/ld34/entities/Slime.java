package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.utils.Vec2;
import development.codenmore.ld34.worlds.tiles.Tile;


public class Slime extends Entity {
	
	private Animation anim;
	private float animTimer = 0f;
	
	private float tt = 0f;
	private int ind=  0;

	public Slime(EntityManager manager, float x, float y) {
		super(manager, Assets.getRegion("slime.1"), x, y, 32, 32);
		anim = new Animation(0.1f, Assets.getRegion("slime.1"), Assets.getRegion("slime.2"),
				Assets.getRegion("slime.3"), Assets.getRegion("slime.4"), Assets.getRegion("slime.5"));
		anim.setPlayMode(PlayMode.LOOP);
		
		AStar.generatPath((int) (x / Tile.TILESIZE), (int) (y / Tile.TILESIZE), 0, 0, path);
	}

	@Override
	public void tick(float delta) {
		animTimer += delta;
		setTexture(anim.getKeyFrame(animTimer));
		
		tt += delta;
		if(tt > 0.1f && path.size != ind){
			tt = 0f;
			Vec2 v = path.get(ind++);
			bounds.x = v.x * Tile.TILESIZE;
			bounds.y = v.y * Tile.TILESIZE;
			System.out.println(v.x + "   P    " + v.y);
		}
		
		checkHealth();
	}
	
}
