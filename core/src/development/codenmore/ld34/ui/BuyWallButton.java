package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.tiles.DirtTile;
import development.codenmore.ld34.worlds.tiles.GrassTile;
import development.codenmore.ld34.worlds.tiles.StoneTile;
import development.codenmore.ld34.worlds.tiles.Tile;

public class BuyWallButton extends BuyButton {

	private static TextureRegion texture;

	public BuyWallButton(float x, float y) {
		super(x, y, 64, 64, 125);
		texture = Assets.getRegion("wallF");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		Assets.getFont().getData().setScale(0.7f);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont().draw(batch, "Wall", x + 8, y - 4);
		Assets.getFont().draw(batch, "" + cost, x + 8, y - 24);
		batch.draw(Assets.getRegion("resourceIcon"), x + 48, y - 38, 16, 16);
	}

	@Override
	public void onPlace(int x, int y, HUD hud) {
		if (hud.getAmountOfResources() < cost
				|| (!(hud.getWorld().getTile(x, y) instanceof DirtTile)
						&& !(hud.getWorld().getTile(x, y) instanceof GrassTile) && !(hud
						.getWorld().getTile(x, y) instanceof StoneTile)))
			return;
		if(!checkPlacement(x, y, hud.getWorld().getEntityManager()))
			return;
		hud.incResources(-cost);
		Assets.playSound("place");
		hud.getWorld().setTile(x, y, Tile.wallTile);
	}

	@Override
	public void click() {
	}
	
	@Override
	public TextureRegion getTexture() {
		return texture;
	}

}
