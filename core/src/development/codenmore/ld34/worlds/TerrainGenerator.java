package development.codenmore.ld34.worlds;

import com.badlogic.gdx.math.MathUtils;

public class TerrainGenerator {

	private TerrainGenerator(){}
	
	public static void generateTerrain(int width, int height, byte[] tiles, int drops, int min, int max){
		byte[] heightmap = new byte[tiles.length];
		for(int i = 0;i < drops;++i){
			performDrop(width, height, heightmap, min, max);
		}
		for(int i = 0;i < tiles.length;++i){
			if(heightmap[i] > 25){
				tiles[i] = 1;
			}else{
				tiles[i] = 0;
			}
		}
	}
	
	private static void performDrop(int width, int height, byte[] heightmap, int min, int max){
		int times = MathUtils.random(min, max);
		int x = MathUtils.random(0, width - 1);
		int y = MathUtils.random(0, height - 1);
		
	}
	
}
