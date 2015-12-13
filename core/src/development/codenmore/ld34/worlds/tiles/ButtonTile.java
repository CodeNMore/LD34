package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class ButtonTile extends Tile {
	
	private TextureRegion texture, texturePressed;
	private Rectangle bounds;
	private boolean pressed = false;

	public ButtonTile() {
		super(Tile.MAX_UNUSE_ID, 1, -1);
		texture = Assets.getRegion("buttonTile");
		texturePressed = Assets.getRegion("buttonTilePressed");
		bounds = new Rectangle(0, 0, Tile.TILESIZE, Tile.TILESIZE);
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		bounds.x = x;
		bounds.y = y;
		if(!pressed)
			batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
		else
			batch.draw(texturePressed, x, y, Tile.TILESIZE, Tile.TILESIZE);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public float getX() {
		return bounds.x;
	}

	public float getY() {
		return bounds.y;
	}

}
