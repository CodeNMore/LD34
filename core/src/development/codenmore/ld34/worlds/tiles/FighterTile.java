package development.codenmore.ld34.worlds.tiles;

import development.codenmore.ld34.worlds.World;


public abstract class FighterTile extends Tile {

	protected int radius;
	protected float waitTime;
	protected float timer;
	
	public FighterTile(int radius, float waitTime){
		super(Tile.MAX_UNUSE_ID, Tile.SOLID_COST);
		this.radius = radius;
		this.waitTime = waitTime;
		this.timer = waitTime;
	}
	
	@Override
	public void tick(float delta, World world){
		timer += delta;
		if(timer >= waitTime){
			if(onReadyFire(world))
				timer = 0f;
		}
	}
	
	public abstract boolean onReadyFire(World world);

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
