package development.codenmore.ld34.worlds;

import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld34.worlds.tiles.Tile;


public class TerrainGenerator {

	private TerrainGenerator(){}
	
	public static void generateTerrain(int width, int height, World world, int drops, int passes,
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
		
		for(int i = 0;i < world.getTiles().length;++i){
			if(heightmap[i] > 10){
				world.setTile(i, Tile.wallTile);
			}else if(heightmap[i] > 8){
				world.setTile(i, Tile.stoneTile);
			}else if(heightmap[i] > 3){
				world.setTile(i, Tile.grassTile);
			}else{
				world.setTile(i, Tile.dirtTile);
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
