package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld34.worlds.World;

public abstract class Tile {

	// Manager

	public static Tile[] tiles = new Tile[6];
	public static Tile dirtTile = new DirtTile(0);
	public static Tile grassTile = new GrassTile(1);
	public static Tile wallTile = new WallTile(2);
	public static Tile stoneTile = new StoneTile(3);
	public static Tile woodWallTile = new WoodWallTile(4);
	public static final int MAX_UNUSE_ID = tiles.length - 1;

	// CLASS

	public static final int TILESIZE = 44, SOLID_COST = 60;
	private byte id;
	private int movementCost;
	private float health = 0f, startHealth;

	public Tile(int id, int movementCost, float health) {
		this.id = (byte) id;
		this.movementCost = movementCost;
		this.health = health;
		this.startHealth = health;
		tiles[id] = this;
	}

	public void tick(float delta, World world) {
	}

	public abstract void render(float x, float y, World world, SpriteBatch batch);

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public int getMovementCost() {
		return movementCost;
	}

	public void setMovementCost(int movementCost) {
		this.movementCost = movementCost;
	}

	public float getHealth() {
		return health;
	}

	public float getStartHealth() {
		return startHealth;
	}

	public void setStartHealth(float startHealth) {
		this.startHealth = startHealth;
		this.health = startHealth;
	}

	public void setHealth(float health) {
		this.health = health;
	}

}
