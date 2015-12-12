package development.codenmore.ld34.worlds;

import com.badlogic.gdx.math.MathUtils;


public class TerrainGenerator {

	private TerrainGenerator(){}
	
	public static void generateTerrain(int width, int height, byte[] tiles, int drops, int passes,
			int min, int max, int offInc){
		byte[] heightmap = new byte[width * height];
		int offset = 0;
		for(int p = 0;p < passes;++p){
			for(int i = 0;i < drops;++i){
				performDrop(width, height, heightmap, offset, MathUtils.random(min, max));
			}
			drops /= 3;
			offset += offInc;
		}
		
		for(int i = 0;i < tiles.length;++i){
			if(heightmap[i] > 10){
				tiles[i] = 2;
			}else if(heightmap[i] > 3){
				tiles[i] = 1;
			}else{
				tiles[i] = 0;
			}
		}
	}
	
	private static void performDrop(int width, int height, byte[] heightmap, int offset, int amt){
		int dropX = MathUtils.random(0 + offset, width - 1 - offset);
		int dropY = MathUtils.random(0 + offset, height - 1 - offset);
		
		for(int i = 0;i < amt;++i){
			moveParticle(dropX, dropY, width, height, heightmap);
		}
	}
	
	private static void moveParticle(int x, int y, int width, int height, byte[] heightmap){
		if(x - 1 >= 0){
			if(heightmap[(x - 1) + y * width] < heightmap[x + y * width]){
				moveParticle(x - 1, y, width, height, heightmap);
				return;
			}
		}
		if(x + 1 < width){
			if(heightmap[(x + 1) + y * width] < heightmap[x + y * width]){
				moveParticle(x + 1, y, width, height, heightmap);
				return;
			}
		}
		if(y - 1 >= 0){
			if(heightmap[x + (y - 1) * width] < heightmap[x + y * width]){
				moveParticle(x, y - 1, width, height, heightmap);
				return;
			}
		}
		if(y + 1 < height){
			if(heightmap[x + (y + 1) * width] < heightmap[x + y * width]){
				moveParticle(x, y + 1, width, height, heightmap);
				return;
			}
		}
		heightmap[x + y * width] += 1;
	}
	
}
