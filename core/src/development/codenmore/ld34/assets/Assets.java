package development.codenmore.ld34.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets {
	
	private static AssetManager manager;
	private static ObjectMap<String, TextureRegion> regions;
	private static boolean pre = false, post = false;
	private static TextureAtlas atlas;
	private static BitmapFont font;
	private static String PTH_FONT = "font/font_con.fnt",
							PTH_ATLAS = "textures/atlas.atlas";
	
	private Assets(){}
	
	private static void preInit(){
		manager = new AssetManager();
		regions = new ObjectMap<String, TextureRegion>();
		manager.load(PTH_FONT, BitmapFont.class);
		manager.load(PTH_ATLAS, TextureAtlas.class);
	}
	
	private static void postInit(){
		atlas = manager.get(PTH_ATLAS, TextureAtlas.class);
		font = manager.get(PTH_FONT, BitmapFont.class);
	}
	
	public static TextureRegion getRegion(String name){
		if(regions.containsKey(name))
			return regions.get(name);
		regions.put(name, atlas.findRegion(name));
		return regions.get(name);
	}
	
	public static BitmapFont getFont(){
		return font;
	}
	
	public static void dispose(){
		atlas = null;
		font = null;
		regions.clear();
		manager.dispose();
	}
	
	public static boolean step(){
		if(!pre){
			preInit();
			pre = true;
		}
		
		if(manager.update()){
			if(!post){
				postInit();
				post = true;
			}
			return true;
		}
		return false;
	}
	
	public static float getProgress(){
		return (int) manager.getProgress() * 100;
	}

}
