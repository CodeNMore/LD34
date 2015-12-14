package development.codenmore.ld34.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets {
	
	private static AssetManager manager;
	private static ObjectMap<String, TextureRegion> regions;
	private static ObjectMap<String, Sound> sounds;
	private static ObjectMap<String, Music> musics;
	private static boolean pre = false, post = false;
	private static TextureAtlas atlas;
	private static BitmapFont font;
	private static String PTH_FONT = "font/font_con.fnt",
							PTH_ATLAS = "textures/atlas.atlas";
	private static boolean mute = false;
	private static String[] soundsPath = {
		"award",
		"buttonPress",
		"dead",
		"place",
		"select",
		"wallHit",
	};
	private static String[] musicsPath = {
		"game1", "menus"
	};
	
	private Assets(){}
	
	private static void preInit(){
		manager = new AssetManager();
		regions = new ObjectMap<String, TextureRegion>();
		sounds = new ObjectMap<String, Sound>();
		musics = new ObjectMap<String, Music>();
		manager.load(PTH_FONT, BitmapFont.class);
		manager.load(PTH_ATLAS, TextureAtlas.class);
		for(int i = 0;i < soundsPath.length;++i){
			manager.load("audio/" + soundsPath[i] + ".ogg", Sound.class);
		}
		for(int i = 0;i < musicsPath.length;++i){
			manager.load("audio/" + musicsPath[i] + ".ogg", Music.class);
		}
	}
	
	private static void postInit(){
		atlas = manager.get(PTH_ATLAS, TextureAtlas.class);
		font = manager.get(PTH_FONT, BitmapFont.class);
		for(int i = 0;i < soundsPath.length;++i){
			sounds.put(soundsPath[i], manager.get("audio/" + soundsPath[i] + ".ogg", Sound.class));
		}
		for(int i = 0;i < musicsPath.length;++i){
			musics.put(musicsPath[i], manager.get("audio/" + musicsPath[i] + ".ogg", Music.class));
		}
	}
	
	public static TextureRegion getRegion(String name){
		if(regions.containsKey(name))
			return regions.get(name);
		regions.put(name, atlas.findRegion(name));
		return regions.get(name);
	}
	
	public static void playSound(String name){
		if(mute)
			return;
		sounds.get(name).play();
	}
	
	public static void playMusic(String name){
		for(int i = 0;i < musicsPath.length;++i)
			musics.get(musicsPath[i]).stop();
		if(mute)
			return;
		musics.get(name).setLooping(true);
		musics.get(name).play();
	}
	
	public static void toggleMute(){
		mute = !mute;
		if(mute){
			playMusic("menus");//STOP ALL MUSIC!!!
		}else{
			playMusic("game1");
		}
	}
	
	public static BitmapFont getFont(){
		return font;
	}
	
	public static void dispose(){
		atlas = null;
		font = null;
		regions.clear();
		sounds.clear();
		musics.clear();
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
