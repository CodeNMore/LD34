package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import development.codenmore.ld34.utils.Vec2;
import development.codenmore.ld34.worlds.tiles.ButtonTile;
import development.codenmore.ld34.worlds.tiles.Tile;

public abstract class Entity {
	
	protected Rectangle bounds;
	protected EntityManager manager;
	protected TextureRegion texture;
	protected boolean alive = true;
	protected float health = 5f;
	protected Array<Vec2> path;

	public Entity(EntityManager manager, TextureRegion texture, float x, float y, float width, float height){
		this.texture = texture;
		this.manager = manager;
		bounds = new Rectangle(x, y, width, height);
		path = new Array<Vec2>();
		genPath();
	}
	
	public abstract void tick(float delta);
	
	public void render(SpriteBatch batch){
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	protected void followPath(float speedDelta){
		if(path.size == 0){
			genPath();
			return;
		}
		
		Vec2 v = path.get(0);
		float vxs = v.x * Tile.TILESIZE;
		float vys = v.y * Tile.TILESIZE;
		
		if(bounds.x < vxs){
			bounds.x += speedDelta;
			if(bounds.x > vxs)
				bounds.x = vxs;
		}else if(bounds.x > vxs){
			bounds.x -= speedDelta;
			if(bounds.x < vxs)
				bounds.x = vxs;
		}else if(bounds.y < vys){
			bounds.y += speedDelta;
			if(bounds.y > vys)
				bounds.y = vys;
		}else if(bounds.y > vys){
			bounds.y -= speedDelta;
			if(bounds.y < vys)
				bounds.y = vys;
		}else{
			path.removeIndex(0);
		}
		
	}
	
	protected void checkButtonCollisions(){
		for(ButtonTile b : manager.getWorld().getButtons()){
			if(!b.isPressed() && b.getBounds().overlaps(bounds)){
				b.setPressed(true);
				alive = false;
				return;
			}
		}
	}
	
	protected void genPath(){
		ButtonTile b = manager.getWorld().getAvailableButtonTile();
		AStar.generatPath((int) (bounds.x / Tile.TILESIZE), (int) (bounds.y / Tile.TILESIZE),
				(int) (b.getX() / Tile.TILESIZE), (int) (b.getY() / Tile.TILESIZE), path);
	}
	
	protected void checkHealth(){
		if(health <= 0)
			alive = false;
	}
	
	public void damage(float amt){
		health -= amt;
		checkHealth();
	}
	
	
	// GETTERS SETTERS
	
	public void setX(float x){
		bounds.x = x;
	}
	
	public void setY(float y){
		bounds.y = y;
	}
	
	public void incX(float x){
		bounds.x += x;
	}
	
	public void incY(float y){
		bounds.y += y;
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
