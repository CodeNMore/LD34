package development.codenmore.ld34.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

import development.codenmore.ld34.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = Main.TITLE;
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		// Texture packer
		Settings s = new Settings();
		s.edgePadding = false;
		s.paddingX = 0;
		s.paddingY = 0;
		s.fast = true;
		s.pot = true;
		s.combineSubdirectories = true;
		TexturePacker.process(s, "../android/assets/raw", "../android/assets/textures", "atlas");
		
		new LwjglApplication(new Main(), config);
	}
}
