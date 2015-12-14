package development.codenmore.ld34.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld34.Main;
import development.codenmore.ld34.assets.Assets;

public class Tutorial {
	
	private TextureRegion texture = Assets.getRegion("tutOverlay");
	private boolean on = true;
	private int stage = 0;
	private String text = "";
	private boolean newStage = true;
	private InputProcessor toSet;

	public Tutorial(InputProcessor toSet){
		Gdx.input.setInputProcessor(null);
		this.toSet = toSet;
	}
	
	public void tick(float delta){
		if(!on)
			return;
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			stage++;
			newStage = true;
		}
		
		if(!newStage)
			return;
		newStage = false;
		
		switch(stage){
		case 0:
			text = "Hey, you! Yeah, you! I have a job"
					+ "\noffer for you. How would you like"
					+ "\nto be the protector of something"
					+ "\nvery, very important?";
			break;
		case 1:
			text = "Move the world around by using"
					+ "\nthe WASD keys, and select and"
					+ "\nplace items with the mouse and"
					+ "\nleft mouse button.";
			break;
		case 2:
			text = "Do you see those 2 red buttons that"
					+ "\nare on the ground behind me? If"
					+ "\nthose 2 buttons are both pressed,"
					+ "\nthen this place will explode!";
			break;
		case 3:
			text = "Your job is to protect these 2"
					+ "\nbuttons from the evil enemies of"
					+ "\nthe world. These enemies are smart,"
					+ "\nand they wish to destroy us!";
			break;
		case 4:
			text = "Do you see that green thing in the"
					+ "\nupper left corner of the screen?"
					+ "\nThat is how many resources you"
					+ "\nhave to buy stuff.";
			break;
		case 5:
			text = "And that yellow thing, that is how"
					+ "\nmuch energy you have to buy defense"
					+ "\nitems. That brown thing is how much"
					+ "\nfood you have for your citizens.";
			break;
		case 6:
			text = "You must not let your citizens"
					+ "\nstarve to death, and you must never"
					+ "\nlet an enemy near those buttons!";
			break;
		case 7:
			text = "Save resources and energy to buy"
					+ "\nitems from the menu below, and place"
					+ "\nthe items on the world to purchase"
					+ "\nthem.";
			break;
		case 8:
			text = "Some items attack enemies, while"
					+ "\nothers produce resources over time."
					+ "\nYou can use the 'X' item to delete"
					+ "\nplaced items and stone walls.";
			break;
		case 9:
			text = "I think I am dying..please, protect"
					+ "\nthe buttons with your life!"
					+ "\nI trust you to build and grow your"
					+ "\nempire as large as you can!";
			break;
		default:
			on = false;
			Gdx.input.setInputProcessor(toSet);
			break;
		}
	}
	
	public void render(SpriteBatch batch){
		if(!on)
			return;
		
		batch.draw(texture, 0, Main.WIDTH / 2 - Main.HEIGHT / 4, Main.WIDTH, Main.HEIGHT / 4);
		
		Assets.getFont().setColor(Color.WHITE);
		Assets.getFont().getData().setScale(0.5f);
		Assets.getFont().draw(batch, "PRESS [SPACE]", Main.WIDTH - 120, Main.HEIGHT / 2 - 24);
		
		Assets.getFont().setColor(Color.YELLOW);
		Assets.getFont().getData().setScale(0.8f);
		Assets.getFont().draw(batch, text, 120, 316);
	}
	
	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

}
