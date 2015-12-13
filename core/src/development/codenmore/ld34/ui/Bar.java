package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld34.assets.Assets;

public class Bar {
	
	private static TextureRegion tex = Assets.getRegion("color");
	private Color bg, fg;
	private Rectangle bounds;
	private float level = 0.0f, maxLevel = 1.0f;

	public Bar(Color bg, Color fg, float x, float y, float width, float height){
		this.bg = bg;
		this.fg = fg;
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public Bar(Color bg, Color fg, float x, float y, float width, float height, float maxLevel){
		this(bg, fg, x, y, width, height);
		setMaxLevel(maxLevel);
	}
	
	public boolean render(SpriteBatch batch){
		batch.setColor(bg);
		batch.draw(tex, bounds.x, bounds.y, bounds.width, bounds.height);
		batch.setColor(fg);
		batch.draw(tex, bounds.x, bounds.y, getRenderWidth(), bounds.height);
		batch.setColor(Color.WHITE);
		if(level >= maxLevel)
			return true;
		return false;
	}
	
	public boolean render(SpriteBatch batch, float x, float y){
		bounds.x = x;
		bounds.y = y;
		return render(batch);
	}
	
	private float getRenderWidth(){
		return bounds.width * (this.level / this.maxLevel);
	}
	
	public void incLevel(float amt){
		setLevel(level + amt);
	}

	public float getLevel() {
		return level;
	}

	public void setLevel(float level) {
		this.level = level;
		if(level > maxLevel)
			level = maxLevel;
	}

	public float getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(float maxLevel) {
		this.maxLevel = maxLevel;
	}
	
}
