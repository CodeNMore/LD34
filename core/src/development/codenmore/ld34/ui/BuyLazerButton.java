package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.tiles.DirtTile;
import development.codenmore.ld34.worlds.tiles.GrassTile;
import development.codenmore.ld34.worlds.tiles.LazerTile;
import development.codenmore.ld34.worlds.tiles.StoneTile;

public class BuyLazerButton extends BuyButton {

	private static TextureRegion texture;

	public BuyLazerButton(float x, float y) {
		super(x, y, 64, 64, 1000);
		texture = Assets.getRegion("lazer");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		Assets.getFont().getData().setScale(0.7f);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont().draw(batch, "Laser", x + 2, y - 4);
		Assets.getFont().draw(batch, "" + cost, x - 2, y - 24);
		batch.draw(Assets.getRegion("energyIcon"), x + 52, y - 38, 16, 16);
	}

	@Override
	public void onPlace(int x, int y, HUD hud) {
		if (hud.getAmountOfEnergy() < cost
				|| (!(hud.getWorld().getTile(x, y) instanceof DirtTile)
				&& !(hud.getWorld().getTile(x, y) instanceof GrassTile)
				&& !(hud.getWorld().getTile(x, y) instanceof StoneTile)))
			return;
		hud.incEnergy(-cost);
		hud.addFoodTaker();
		hud.getWorld().setTile(x, y, new LazerTile());
	}

	@Override
	public void click() {
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}

}
