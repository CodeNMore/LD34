package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;



public abstract class BuyButton extends Button {

	protected int cost;
	
	public BuyButton(float x, float y, int width, int height, int cost){
		super(x, y, width, height);
		this.cost = cost;
	}
	
	public abstract void onPlace(int x, int y, HUD hud);
	
	public abstract TextureRegion getTexture();

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
