package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld34.worlds.World;

public class EntityManager {

	private World world;
	private WaveManager waveManager;
	private Array<Spawner> spawners;
	private Array<Entity> entities;
	private Array<Projectile> projectiles;

	public EntityManager(World world) {
		this.world = world;
		entities = new Array<Entity>();
		spawners = new Array<Spawner>();
		projectiles = new Array<Projectile>();

		waveManager = new WaveManager(this);
	}

	public void tick(float delta) {
		waveManager.tick(delta);

		for (Spawner s : spawners)
			s.tick(delta);

		for (Entity e : entities) {
			e.tick(delta);
			if (!e.isAlive())
				entities.removeValue(e, true);
		}

		for (Projectile p : projectiles) {
			p.tick(delta);
			if (!p.isAlive())
				projectiles.removeValue(p, true);
		}
		
	}

	public void render(SpriteBatch batch) {
		for (Entity e : entities) {
			e.render(batch);
		}

		for (Projectile p : projectiles) {
			p.render(batch);
		}
	}

	public Entity getFirstEntityInRange(float x, float y, float radius) {
		Circle c = new Circle(x, y, radius);
		for (Entity e : entities) {
			if (Intersector.overlaps(c, e.getBounds()))
				return e;
		}
		return null;
	}

	public void addEntity(Entity e) {
		entities.add(e);
		waveManager.addedEntity();
	}

	public void addSpawner(Spawner s) {
		spawners.add(s);
	}

	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Array<Spawner> getSpawners() {
		return spawners;
	}

	public void setSpawners(Array<Spawner> spawners) {
		this.spawners = spawners;
	}

	public Array<Entity> getEntities() {
		return entities;
	}

	public WaveManager getWaveManager() {
		return waveManager;
	}

	public void setWaveManager(WaveManager waveManager) {
		this.waveManager = waveManager;
	}

	public void setEntities(Array<Entity> entities) {
		this.entities = entities;
	}

}
