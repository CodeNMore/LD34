package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.World;

public class ButtonTile extends Tile {
	
	private TextureRegion texture, texturePressed;
	private boolean pressed = false;

	public ButtonTile() {
		super(Tile.MAX_UNUSE_ID, 1);
		texture = Assets.getRegion("buttonTile");
		texturePressed = Assets.getRegion("buttonTilePressed");
	}

	@Override
	public void render(float x, float y, World world, SpriteBatch batch) {
		if(!pressed)
			batch.draw(texture, x, y, Tile.TILESIZE, Tile.TILESIZE);
		else
			batch.draw(texturePressed, x, y, Tile.TILESIZE, Tile.TILESIZE);
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

}
