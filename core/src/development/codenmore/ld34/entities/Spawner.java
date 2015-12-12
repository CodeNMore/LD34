package development.codenmore.ld34.entities;

import com.badlogic.gdx.math.MathUtils;

public class Spawner {

	private EntityManager manager;
	private float x, y;
	private float timer = 0f, nextTime;
	private float minTime, maxTime, minEnemies, maxEnemies;
	
	public Spawner(EntityManager manager, float x, float y, float minTime, float maxTime, float minEnemies, float maxEnemies){
		this.manager = manager;
		this.x = x;
		this.y = y;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.minEnemies = minEnemies;
		this.maxEnemies = maxEnemies;
		nextTime = MathUtils.random(minTime, maxTime);
	}
	
	public void tick(float delta){
		timer += delta;
		if(timer > nextTime){
			timer = 0f;
			nextTime = MathUtils.random(minTime, maxTime);
			
			manager.addEntity(new Slime(manager, x, y));
		}
	}
	
}
