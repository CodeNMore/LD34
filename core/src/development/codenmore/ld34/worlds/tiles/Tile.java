package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld34.worlds.World;

public abstract class Tile {
	
	// Manager
	
	public static Tile[] tiles = new Tile[3];
	public static Tile dirtTile = new DirtTile(0);
	public static Tile grassTile = new GrassTile(1);
	public static Tile wallTile = new WallTile(2);
	
	// CLASS
	
	public static final int TILESIZE = 48;
	private byte id;

	public Tile(int id){
		if(tiles[id] != null)
			throw new IllegalArgumentException("Duplicate tile ids " + id);
		this.id = (byte) id;
		tiles[id] = this;
	}
	
	public abstract void render(float x, float y, World world, SpriteBatch batch);

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}
	
}
