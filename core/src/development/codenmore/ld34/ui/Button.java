package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Button {
	
	protected float x, y;
	protected int width, height;
	private Rectangle bounds;

	public Button(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void click();
	
	public boolean checkClick(float x, float y){
		if(bounds.contains(x, y)){
			click();
			return true;
		}
		return false;
	}
	
}
