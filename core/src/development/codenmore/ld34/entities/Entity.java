package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.ui.Bar;
import development.codenmore.ld34.utils.Vec2;
import development.codenmore.ld34.worlds.tiles.ButtonTile;
import development.codenmore.ld34.worlds.tiles.FarmTile;
import development.codenmore.ld34.worlds.tiles.Tile;

public abstract class Entity {

	protected Rectangle bounds;
	protected EntityManager manager;
	protected TextureRegion texture;
	protected boolean alive = true;
	protected float startHealth = 2.0f;
	protected float health = startHealth;
	protected Array<Vec2> path;
	private boolean frozen = false;
	protected float frozenTimer = 0f, freezeTime = 0f;
	protected float thingDamage = 0.5f, damageTime = 1.0f,
			damageTimer = damageTime;
	private boolean damaging = false;
	protected Bar healthBar;

	public Entity(EntityManager manager, TextureRegion texture, float x,
			float y, float width, float height, float thingDamage) {
		this.texture = texture;
		this.manager = manager;
		bounds = new Rectangle(x, y, width, height);
		path = new Array<Vec2>();
		genPath();
		this.thingDamage = thingDamage;
		healthBar = new Bar(Color.RED, Color.GREEN, 0, 0, Tile.TILESIZE - 8, 4,
				startHealth);
	}

	public void tick(float delta) {
		if (frozen) {
			frozenTimer += delta;
			if (frozenTimer > freezeTime) {
				frozen = false;
				genPath();
			}
		}
		if (damaging) {
			damageTimer += delta;
		}
		checkButtonCollisions();
		checkHealth();
		healthBar.setLevel(health);
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
		healthBar.render(batch, bounds.x, bounds.y + Tile.TILESIZE - 2);
	}

	public void freeze(float time) {
		frozen = true;
		freezeTime = time;
		frozenTimer = 0f;
	}

	protected void followPath(float speedDelta) {
		if (path.size == 0) {
			genPath();
			return;
		}
		if (frozen)
			return;

		Vec2 v = path.get(0);
		if (manager.getWorld().getHealth(v.x, v.y) != -1f) {
			damaging = true;
			// Must break through tile before entering
			if (damageTimer >= damageTime) {
				damageTimer = 0f;
				if (manager.getWorld().incHealth(v.x, v.y, -thingDamage) <= 0) {
					damaging = false;
					Assets.playSound("wallHit");
					manager.getWorld().setTile(v.x, v.y, Tile.dirtTile);
				}
			}
			return;
		} else if (manager.getWorld().getTile(v.x, v.y) instanceof FarmTile) {
			manager.getWorld().setTile(v.x, v.y, Tile.dirtTile);
		}
		float vxs = v.x * Tile.TILESIZE;
		float vys = v.y * Tile.TILESIZE;

		if (bounds.x < vxs) {
			bounds.x += speedDelta;
			if (bounds.x > vxs)
				bounds.x = vxs;
		} else if (bounds.x > vxs) {
			bounds.x -= speedDelta;
			if (bounds.x < vxs)
				bounds.x = vxs;
		} else if (bounds.y < vys) {
			bounds.y += speedDelta;
			if (bounds.y > vys)
				bounds.y = vys;
		} else if (bounds.y > vys) {
			bounds.y -= speedDelta;
			if (bounds.y < vys)
				bounds.y = vys;
		} else {
			path.removeIndex(0);
		}

	}

	protected void checkButtonCollisions() {
		for (ButtonTile b : manager.getWorld().getButtons()) {
			if (!b.isPressed() && b.getBounds().overlaps(bounds)) {
				b.setPressed(true);
				alive = false;
				return;
			}
		}
	}

	protected void genPath() {
		ButtonTile b = manager.getWorld().getAvailableButtonTile();
		AStar.generatPath((int) (bounds.x / Tile.TILESIZE),
				(int) (bounds.y / Tile.TILESIZE),
				(int) (b.getX() / Tile.TILESIZE),
				(int) (b.getY() / Tile.TILESIZE), path);
	}

	protected void checkHealth() {
		if (health <= 0) {
			alive = false;
			manager.getWorld()
					.getGameState()
					.getHud()
					.incEnergy(
							(int) MathUtils.random(startHealth * 5,
									startHealth * 9));
			manager.getWorld()
					.getGameState()
					.getHud()
					.incResources(
							(int) MathUtils.random(startHealth * 5,
									startHealth * 10));
			if (MathUtils.randomBoolean(0.3f))
				manager.getWorld().getGameState().getHud()
						.incFood(MathUtils.random(8, 16));
		}
	}

	public void damage(float amt) {
		health -= amt;
		checkHealth();
	}

	// GETTERS SETTERS

	public void setX(float x) {
		bounds.x = x;
	}

	public void setY(float y) {
		bounds.y = y;
	}

	public void incX(float x) {
		bounds.x += x;
	}

	public void incY(float y) {
		bounds.y += y;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public float getStartHealth() {
		return startHealth;
	}

	public void setStartHealth(float startHealth) {
		this.startHealth = startHealth;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getThingDamage() {
		return thingDamage;
	}

	public void setThingDamage(float thingDamage) {
		this.thingDamage = thingDamage;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
