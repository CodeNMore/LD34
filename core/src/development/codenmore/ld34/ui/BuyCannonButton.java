package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.tiles.CannonTile;
import development.codenmore.ld34.worlds.tiles.DirtTile;
import development.codenmore.ld34.worlds.tiles.GrassTile;
import development.codenmore.ld34.worlds.tiles.StoneTile;

public class BuyCannonButton extends BuyButton {

	private static TextureRegion texture;

	public BuyCannonButton(float x, float y) {
		super(x, y, 64, 64, 500);
		texture = Assets.getRegion("cannon");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		Assets.getFont().getData().setScale(0.7f);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont().draw(batch, "Cannon", x - 6, y - 4);
		Assets.getFont().draw(batch, "" + cost, x + 6, y - 24);
		batch.draw(Assets.getRegion("energyIcon"), x + 46, y - 38, 16, 16);
	}

	@Override
	public void onPlace(int x, int y, HUD hud) {
		if (hud.getAmountOfEnergy() < cost
				|| (!(hud.getWorld().getTile(x, y) instanceof DirtTile)
				&& !(hud.getWorld().getTile(x, y) instanceof GrassTile)
				&& !(hud.getWorld().getTile(x, y) instanceof StoneTile)))
			return;
		hud.incEnergy(-cost);
		
		hud.getWorld().setTile(x, y, new CannonTile());
	}

	@Override
	public void click() {
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}

}
