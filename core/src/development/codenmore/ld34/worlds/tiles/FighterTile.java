package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld34.ui.Bar;
import development.codenmore.ld34.worlds.World;


public abstract class FighterTile extends Tile {

	protected int radius;
	protected float waitTime;
	protected float timer;
	protected Bar bar;
	
	public FighterTile(int radius, float waitTime, float health){
		super(Tile.MAX_UNUSE_ID, Tile.SOLID_COST, health);
		this.radius = radius;
		this.waitTime = waitTime;
		this.timer = waitTime;
		bar = new Bar(Color.RED, Color.GREEN, 0, 0, Tile.TILESIZE, 4, health);
	}
	
	@Override
	public void tick(float delta, World world){
		timer += delta;
		if(timer >= waitTime){
			if(onReadyFire(world))
				timer = 0f;
		}
		bar.setLevel(world.getHealth(world.getCurrentTileIndex()));
	}
	
	public abstract boolean onReadyFire(World world);
	
	protected void renderBar(SpriteBatch batch, float x, float y){
		bar.render(batch, x, y);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public float getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(float waitTime) {
		this.waitTime = waitTime;
	}

}
