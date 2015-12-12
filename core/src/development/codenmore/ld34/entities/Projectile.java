package development.codenmore.ld34.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {

	private TextureRegion texture;
	private Entity target;
	private float dmg, speed = 250.0f;
	private Rectangle bounds;
	private boolean alive = true;

	public Projectile(TextureRegion texture, Entity target, float x, float y,
			float width, float height, float dmg, float speed) {
		this.texture = texture;
		this.target = target;
		this.bounds = new Rectangle(x, y, width, height);
		this.dmg = dmg;
		this.speed = speed;
	}
	
	public void tick(float delta){
		float angle = MathUtils.atan2(bounds.y - target.getBounds().y, bounds.x - target.getBounds().x);
		bounds.x -= MathUtils.cos(angle) * speed * delta;
		bounds.y -= MathUtils.sin(angle) * speed * delta;
		
		if(target.getBounds().overlaps(bounds)){
			target.damage(dmg);
			alive = false;
		}
	}
	
	public void render(SpriteBatch batch){
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
	}

	// GETTERS SETTERS

	public TextureRegion getTexture() {
		return texture;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public Entity getTarget() {
		return target;
	}

	public float getDmg() {
		return dmg;
	}

	public void setDmg(float dmg) {
		this.dmg = dmg;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

}
