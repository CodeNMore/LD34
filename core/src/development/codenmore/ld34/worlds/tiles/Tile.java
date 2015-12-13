package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld34.worlds.World;

public abstract class Tile {
	
	// Manager
	
	public static Tile[] tiles = new Tile[5];
	public static Tile dirtTile = new DirtTile(0);
	public static Tile grassTile = new GrassTile(1);
	public static Tile wallTile = new WallTile(2);
	public static Tile stoneTile = new StoneTile(3);
	public static final int MAX_UNUSE_ID = tiles.length - 1;
	
	// CLASS
	
	public static final int TILESIZE = 48, SOLID_COST = 10;
	private byte id;
	private int movementCost;

	public Tile(int id, int movementCost){
		this.id = (byte) id;
		this.movementCost = movementCost;
		tiles[id] = this;
	}
	
	public void tick(float delta, World world){}
	
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
	
}
