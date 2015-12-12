package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
	
	protected Rectangle bounds;
	protected EntityManager manager;
	protected TextureRegion texture;
	protected boolean alive = true;
	protected float health = 5f;

	public Entity(EntityManager manager, TextureRegion texture, float x, float y, float width, float height){
		this.texture = texture;
		this.manager = manager;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public abstract void tick(float delta);
	
	public void render(SpriteBatch batch){
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
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
