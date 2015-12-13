package development.codenmore.ld34.worlds.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.assets.Assets;
import development.codenmore.ld34.ui.Button;
import development.codenmore.ld34.ui.HUD;

public class NextPageButton extends Button {
	
	private HUD hud;
	private TextureRegion texture;

	public NextPageButton(float x, float y, HUD hud) {
		super(x, y, 32, 64);
		this.hud = hud;
		texture = Assets.getRegion("nextArrow");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		Assets.getFont().getData().setScale(0.7f);
		Assets.getFont().setColor(Color.BLACK);
		Assets.getFont().draw(batch, "Next", x - 12, y - 4);
	}

	@Override
	public void click() {
		hud.page++;
	}

}
