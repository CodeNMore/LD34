package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld34.worlds.World;
import development.codenmore.ld34.worlds.tiles.Tile;

public class EntityManager {
	
	private World world;
	private Array<Spawner> spawners;
	private Array<Entity> entities;
	private Array<Projectile> projectiles;
	
	public EntityManager(World world){
		this.world = world;
		entities = new Array<Entity>();
		spawners = new Array<Spawner>();
		projectiles = new Array<Projectile>();
		
		addSpawner(new Spawner(this, world.getWidth() / 2 * Tile.TILESIZE - 64, world.getHeight() / 2 * Tile.TILESIZE, 2, 9, 1, 3));
	}
	
	public void tick(float delta){
		for(Spawner s : spawners)
			s.tick(delta);
		
		for(Entity e : entities){
			e.tick(delta);
			if(!e.isAlive())
				entities.removeValue(e, true);
		}
		
		for(Projectile p : projectiles){
			p.tick(delta);
			if(!p.isAlive())
				projectiles.removeValue(p, true);
		}
	}
	
	public void render(SpriteBatch batch){
		for(Entity e : entities){
			e.render(batch);
		}
		
		for(Projectile p : projectiles){
			p.render(batch);
		}
	}
	
	public Entity getFirstEntityInRange(float x, float y, float radius){
		Circle c = new Circle(x, y, radius);
		for(Entity e : entities){
			if(Intersector.overlaps(c, e.getBounds()))
				return e;
		}
		return null;
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void addSpawner(Spawner s){
		spawners.add(s);
	}
	
	public void addProjectile(Projectile p){
		projectiles.add(p);
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
