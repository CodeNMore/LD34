package development.codenmore.ld34.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.worlds.tiles.DirtTile;
import development.codenmore.ld34.worlds.tiles.DrillTile;
import development.codenmore.ld34.worlds.tiles.GrassTile;
import development.codenmore.ld34.worlds.tiles.StoneTile;

public class BuyDrillButton extends BuyButton {

	private static TextureRegion texture;

	public BuyDrillButton(float x, float y) {
		super(x, y, 64, 64, 500);
		texture = Assets.getRegion("drill.1");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		Assets.getFont().getData().setScale(0.7f);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont().draw(batch, "Drill", x + 2, y - 4);
		Assets.getFont().draw(batch, "" + cost, x + 4, y - 24);
		Assets.getFont().setColor(Color.GREEN);
		Assets.getFont().getData().setScale(0.5f);
		Assets.getFont().draw(batch, "+  /" + (int) DrillTile.TIMEINTERVAL + "s", x + 2, y + 56);
		batch.draw(Assets.getRegion("resourceIcon"), x + 44, y - 38, 16, 16);
		batch.draw(Assets.getRegion("resourceIcon"), x + 14, y + 42, 16, 16);
	}

	@Override
	public void onPlace(int x, int y, HUD hud) {
		if (hud.getAmountOfResources() < cost
				|| (!(hud.getWorld().getTile(x, y) instanceof DirtTile)
				&& !(hud.getWorld().getTile(x, y) instanceof GrassTile)
				&& !(hud.getWorld().getTile(x, y) instanceof StoneTile)))
			return;
		hud.incResources(-cost);
		System.out.println("do");
		
		hud.getWorld().setTile(x, y, new DrillTile());
	}

	@Override
	public void click() {
	}

}
