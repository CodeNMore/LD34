package development.codenmore.ld34.entities;

import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.worlds.tiles.Tile;

public class Spawner {

	private EntityManager manager;
	private float x, y;
	private float timer = 0f, nextTime;
	private float toSpawn, eTimer, eSpawned, eTime = 0.9f;
	private float minTime, maxTime, minEnemies, maxEnemies;
	private SpawnProducer producer;
	
	public Spawner(EntityManager manager, float x, float y, float minTime, float maxTime, float minEnemies, float maxEnemies,
			SpawnProducer producer){
		this.manager = manager;
		this.x = x;
		this.y = y;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.minEnemies = minEnemies;
		this.maxEnemies = maxEnemies;
		this.producer = producer;
		
		reset();
	}
	
	public void tick(float delta){
		timer += delta;
		eTimer += delta;
		
		if(timer > nextTime){
			if(eTimer > eTime){
				manager.addEntity(producer.getNew(manager, x * Tile.TILESIZE, y * Tile.TILESIZE));
				eTimer = 0;
				eSpawned++;
				if(eSpawned >= toSpawn){
					reset();
				}
			}
		}
	}
	
	public void reset(){
		timer = 0f;
		eSpawned = 0;
		toSpawn = MathUtils.random(minEnemies, maxEnemies);
		nextTime = MathUtils.random(minTime, maxTime);
	}
	
	// GETTERS SETTERS

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public float getTimer() {
		return timer;
	}

	public void setTimer(float timer) {
		this.timer = timer;
	}

	public float getNextTime() {
		return nextTime;
	}

	public void setNextTime(float nextTime) {
		this.nextTime = nextTime;
	}

	public float getMinTime() {
		return minTime;
	}

	public void setMinTime(float minTime) {
		this.minTime = minTime;
	}

	public float getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(float maxTime) {
		this.maxTime = maxTime;
	}

	public float getMinEnemies() {
		return minEnemies;
	}

	public void setMinEnemies(float minEnemies) {
		this.minEnemies = minEnemies;
	}

	public float getMaxEnemies() {
		return maxEnemies;
	}

	public SpawnProducer getProducer() {
		return producer;
	}

	public void setProducer(SpawnProducer producer) {
		this.producer = producer;
	}

	public void setMaxEnemies(float maxEnemies) {
		this.maxEnemies = maxEnemies;
	}
	
}
