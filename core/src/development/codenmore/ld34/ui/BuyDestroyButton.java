package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.tiles.ButtonTile;
import development.codenmore.ld34.worlds.tiles.Tile;

public class BuyDestroyButton extends BuyButton {
	
	private static TextureRegion texture;
	
	public BuyDestroyButton(float x, float y){
		super(x, y, 64, 64, 0);
		texture = Assets.getRegion("destroyIcon");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		Assets.getFont().getData().setScale(0.7f);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont().draw(batch, "Destroy", x - 8, y - 4);
		Assets.getFont().draw(batch, "" + cost, x + 20, y - 24);
		batch.draw(Assets.getRegion("resourceIcon"), x + 36, y - 38, 16, 16);
	}
	
	@Override
	public void onPlace(int x, int y, HUD hud){
		if(hud.getAmountOfResources() < cost
				|| hud.getWorld().getTile(x, y) instanceof ButtonTile)
			return;
		hud.incResources(-cost);
		
		hud.getWorld().setTile(x, y, Tile.dirtTile);
	}

	@Override
	public void click() {
	}
	
	@Override
	public TextureRegion getTexture() {
		return texture;
	}

}
