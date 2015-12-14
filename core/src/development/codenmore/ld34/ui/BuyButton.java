package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld34.entities.Entity;
import development.codenmore.ld34.entities.EntityManager;
import development.codenmore.ld34.worlds.tiles.Tile;



public abstract class BuyButton extends Button {

	private static Rectangle tmp = new Rectangle(0, 0, Tile.TILESIZE, Tile.TILESIZE);
	protected int cost;
	
	public BuyButton(float x, float y, int width, int height, int cost){
		super(x, y, width, height);
		this.cost = cost;
	}
	
	public abstract void onPlace(int x, int y, HUD hud);
	
	public abstract TextureRegion getTexture();
	
	public boolean checkPlacement(int x, int y, EntityManager manager){
		tmp.x = x * Tile.TILESIZE;
		tmp.y = y * Tile.TILESIZE;
		for(Entity e : manager.getEntities()){
			if(e.getBounds().overlaps(tmp))
				return false;
		}
		return true;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
